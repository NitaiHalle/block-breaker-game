package genral;

import java.util.List;

import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public interface LevelInformation {
    /**
     *
     * @return number of balls.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     *
     * @return velocity of each ball.
     */
    List<Velocity> initialBallVelocities();
    /**
     *
     * @return speed of paddle
     */
    int paddleSpeed();
    /**
     *
     * @return paddle width.
     */
    int paddleWidth();
    // the level name will be displayed at the top of the screen.
    /**
     *
     * @return level name.
     */
    String levelName();
    // Returns a sprite with the background of the level
    /**
     *
     * @return Background.
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    /**
     *
     * @return The Blocks that make up this level.
     */
    List<Block> blocks();
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    /**
     *
     * @return number Of Blocks To Remove.
     */
    int numberOfBlocksToRemove();

}
