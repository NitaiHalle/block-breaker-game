package genral;
import java.awt.Color;


import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
//import biuoop.Sleeper;
import removal.BallRemover;
import removal.BlockRemover;
import removal.Counter;
//import genral.Collidable;
//import genral.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import listeners.PrintingHitListener;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.NameLevel;
/**
 *
 * @author nitai
 *
 */
public class GameLevel implements Animation {
     private SpriteCollection sprites;
     private GameEnvironment environment;
     private GUI gui;
     //private Sleeper sleeper;
     private Counter blockCounter;
     private Counter ballCounter;
     private Counter score = new Counter(0);
     private Counter lives;
     private Paddle paddle;
     private AnimationRunner runner;
     private boolean running;
     private KeyboardSensor keyboard;
     private LevelInformation info;
     private BlockRemover blockRemove;
     /**
      *@param information Level Information.
      */
     public GameLevel(LevelInformation information) {
         this.info = information;
         //this.ballCounter = new Counter(this.info.numberOfBalls());
         this.blockCounter = new Counter(this.info.numberOfBlocksToRemove());
         this.environment = new GameEnvironment();
         this.sprites = new SpriteCollection();
         this.blockRemove = new BlockRemover(this, this.blockCounter);
     }
     /**
      *
      * @param levelInfo Level Information.
      * @param animationRunner Animation Runner.
      * @param keyboardSensor keyboard Sensor.
      * @param score score.
      * @param lives lives.
      */
     public GameLevel(LevelInformation levelInfo, AnimationRunner animationRunner, KeyboardSensor keyboardSensor,
             Counter score, Counter lives) {
         this.info = levelInfo;
         this.ballCounter = new Counter(0);
         this.blockCounter = new Counter(this.info.numberOfBlocksToRemove());
         this.environment = new GameEnvironment();
         this.sprites = new SpriteCollection();
         this.blockRemove = new BlockRemover(this, this.blockCounter);
         this.keyboard = keyboardSensor;
         this.runner = animationRunner;
         this.score = score;
         this.lives = lives;
     }
     /**
      *
      * @param c collidable.
      */
     public void addCollidable(Collidable c) {
         this.environment.addCollidable(c);
     }
     /**
      *
      * @param s sprite/
      */
     public void addSprite(Sprite s) {
         this.sprites.addSprite(s);
     }
     /**
      *
      * @param c Collidable.
      */
     public void removeCollidable(Collidable c) {
         this.environment.removeCollidable(c);
     }
     /**
      *
      * @param s sprite.
      */
     public void removeSprite(Sprite s) {
         this.sprites.removeSprite(s);
     }
     /**Initialize a new game: create the Blocks and Ball (and Paddle)
      * and add them to the game.
      *
      */
     public void initialize() {
         this.sprites.addSprite(this.info.getBackground());
          PrintingHitListener print = new PrintingHitListener();
          Block up = new Block(new Point(0, 0), 800, 40, Color.gray, -1);
          //up.addHitListener(print);
          Block down = new Block(new Point(20, 599), 760, 20, Color.GRAY, -1);
          //down.addHitListener(print);
          Block left = new Block(new Point(0, 20), 20, 580, Color.GRAY, -1);
          //left.addHitListener(print);
          Block right = new Block(new Point(780, 20), 20, 580, Color.gray, -1);
          //right.addHitListener(print);
          BallRemover balls = new BallRemover(this, this.ballCounter);
          ScoreTrackingListener sc = new ScoreTrackingListener(this.score);
          Block[] blocks = new Block[this.info.numberOfBlocksToRemove()];
          for (int i = 0; i < this.info.blocks().size(); i++) {
              blocks[i] = this.info.blocks().get(i);
              blocks[i].addToGame(this);
              blocks[i].addHitListener(sc);
              blocks[i].addHitListener(this.blockRemove);
              blocks[i].addHitListener(print);
          }
          //this.gui = new GUI("game", 800, 600);
          Rectangle rect = new Rectangle(new Point(200, 560), this.info.paddleWidth(), 20);
          this.paddle = new Paddle(rect, Color.CYAN, 20, this.keyboard, this.info.paddleSpeed());
          this.paddle.addToGame(this);
          ScoreIndicator s = new ScoreIndicator(this);
          up.addToGame(this);
          right.addToGame(this);
          down.addToGame(this);
          left.addToGame(this);
          //ScoreIndicator s = new ScoreIndicator(this);
          LivesIndicator l = new LivesIndicator(this);
          NameLevel name = new NameLevel(this);
          this.sprites.addSprite(name);
          this.sprites.addSprite(l);
          this.sprites.addSprite(s);
          down.addHitListener(balls);
          this.sprites.addSprite(s);
          //this.sleeper = new Sleeper();
          //this.keyboard = gui.getKeyboardSensor();
     }
     /**
      * Run the game -- start the animation loop.
      */
     public void run() {
         while (this.lives.getValue() > 0) {
             if (this.blockCounter.getValue() == 0) {
                 break;
             }
             this.playOneTurn();
             //this.ballCounter.increase(this.info.numberOfBalls());
             this.lives.decrease(1);
         }
         this.gui.close();
         return;
     }
     /**
      *
      */
     public void playOneTurn() {
         Ball[] ballOfLevel = new Ball[this.info.numberOfBalls()];
         for (int i = 0; i < this.info.numberOfBalls(); i++) {
             ballOfLevel[i] = new Ball(400, 400, 5, Color.WHITE);
             ballOfLevel[i].setVelocity(this.info.initialBallVelocities().get(i));
             ballOfLevel[i].setGameEnvironment(this.environment);
             ballOfLevel[i].addToGame(this);
             this.ballCounter.increase(1);
         }
         //this.ballCounter = new Counter(this.info.numberOfBalls());
         this.paddle.setLocation(new Point(400 - (this.info.paddleWidth() / 2), 560));
         this.running = true;
         //this.runner = new AnimationRunner(this.gui);
         this.runner.run(new CountdownAnimation(2, 3, this.sprites));
         this.runner.run(this);
     }
     /**
      *@return dont running.
      */
     public boolean shouldStop() {
        return !this.running;
     }
     /**
      *@param d drawsurface.
      *@param dt dt.
      */
     public void doOneFrame(DrawSurface d, double dt) {
         this.sprites.drawAllOn(d);
         this.sprites.notifyAllTimePassed(dt);
         if (this.keyboard.isPressed("p")) {
             this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                     new PauseScreen(this.keyboard)));
          }
         if (this.ballCounter.getValue() == 0) {
             this.running = false;
             this.lives.decrease(1);
         }
         if (this.blockCounter.getValue() == 0) {
             this.running = false;
             this.score.increase(100);
         }
     }
     /**
      *
      * @return score.
      */
     public Counter getScore() {
         return this.score;
     }
     /**
      *
      * @return lives.
      */
     public Counter getLives() {
         return this.lives;
     }
     /**
      *
      * @return name.
      */
     public String getName() {
         return this.info.levelName();
     }
     /**
      *
      * @return get Blocks.
      */
     public int getBlocks() {
         return this.blockCounter.getValue();
     }
}
