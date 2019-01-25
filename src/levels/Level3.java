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
public class Level3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vlo = new ArrayList<Velocity>();
        vlo.add(new Velocity(3 * 60, -3 * 60));
        vlo.add(new Velocity(-3 * 60, -3 * 60));
        return vlo;
    }

    @Override
    public int paddleSpeed() {
        return 5 * 60;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        BackgroundLevel3 background = new BackgroundLevel3();
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<Block>();
        Block []row1 = new Block[10];
        Block []row2 = new Block[9];
        Block []row3 = new Block[8];
        Block []row4 = new Block[7];
        Block []row5 = new Block[6];
        for (int i = 0; i < 10; i++) {
            row1[i] = new Block(new Point(280 + i * 50, 160), 50, 20, Color.GRAY, 1);
            l.add(row1[i]);
        }
        for (int i = 0; i < 9; i++) {
            row2[i] = new Block(new Point(330 + i * 50, 180), 50, 20, Color.RED, 1);
            l.add(row2[i]);
        }
        for (int i = 0; i < 8; i++) {
            row3[i] = new Block(new Point(380 + i * 50, 200), 50, 20, Color.yellow, 1);
            l.add(row3[i]);
        }
        for (int i = 0; i < 7; i++) {
            row4[i] = new Block(new Point(430 + i * 50, 220), 50, 20, Color.blue, 1);
            l.add(row4[i]);
        }
        for (int i = 0; i < 6; i++) {
            row5[i] = new Block(new Point(480 + i * 50, 240), 50, 20, Color.WHITE, 1);
            l.add(row5[i]);
        }
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

}
