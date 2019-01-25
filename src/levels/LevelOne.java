package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import genral.LevelInformation;
import geometry.Point;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public class LevelOne implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<Velocity>();
        l.add(new Velocity(0, -300));
        return l;
    }

    @Override
    public int paddleSpeed() {
        return 300;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        BackgroundLevelOne background = new BackgroundLevelOne();
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<Block>();
        l.add(new Block(new Point(380, 180), 40, 40, Color.RED, 1));
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
