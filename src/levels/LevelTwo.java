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
public class LevelTwo implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vlo = new ArrayList<Velocity>();
        for (int i = 0; i < 10; i++) {
            vlo.add(new Velocity((i - 5) * 60, -3 * 60));
        }
        return vlo;
    }

    @Override
    public int paddleSpeed() {
        return 5 * 60;
    }

    @Override
    public int paddleWidth() {
        return 650;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        BackgroundLevelTwo background = new BackgroundLevelTwo();
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<Block>();
        Color c = Color.red;
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point((20 + 51 * i), 250), 51, 20);
            if (i > 1) {
                c = Color.orange;
            }
            if (i > 3) {
                c = Color.yellow;
            }
            if (i > 5) {
                c = Color.GREEN;
            }
            if (i > 8) {
                c = Color.blue;
            }
            if (i > 10) {
                c = Color.PINK;
            }
            if (i > 12) {
                c = Color.cyan;
            }
            l.add(new Block(rect, c, 1));
        }
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

}
