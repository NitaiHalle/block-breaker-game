package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import genral.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public class Level4 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vlo = new ArrayList<Velocity>();
        vlo.add(new Velocity(3, -3));
        vlo.add(new Velocity(-3, -3));
        vlo.add(new Velocity(0, -4));
        return vlo;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        BackgroundLevel4 background = new BackgroundLevel4();
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<Block>();
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point((20 + 51 * i), 100), 51, 20);
            l.add(new Block(rect, Color.GRAY, 1));
        }
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point((20 + 51 * i), 120), 51, 20);
            l.add(new Block(rect, Color.red, 1));
        }
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point((20 + 51 * i), 140), 51, 20);
            l.add(new Block(rect, Color.YELLOW, 1));
        }
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point((20 + 51 * i), 160), 51, 20);
            l.add(new Block(rect, Color.green, 1));
        }
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point((20 + 51 * i), 180), 51, 20);
            l.add(new Block(rect, Color.WHITE, 1));
        }
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point((20 + 51 * i), 200), 51, 20);
            l.add(new Block(rect, Color.pink, 1));
        }
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point((20 + 51 * i), 220), 51, 20);
            l.add(new Block(rect, Color.CYAN, 1));
        }
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

}
