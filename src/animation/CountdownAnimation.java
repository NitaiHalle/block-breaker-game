package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;
/**
 *
 * @author nitai
 *
 */
public class CountdownAnimation implements Animation {
    private  double wait;
    private int countFrom;
    private int counter;
    private SpriteCollection sprites;
    private Sleeper sleeper = new Sleeper();
    private boolean end;
    /**
     *
     * @param numOfSeconds sec.
     * @param countFrom counter.
     * @param gameScreen sprites.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.counter = this.countFrom;
        this.wait = numOfSeconds * 1000 / countFrom;
        this.sprites = gameScreen;
        this.end = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        String count;
        if (this.counter > 0) {
            count = String.valueOf(this.counter);
        } else {
            count = "GO";
        }
        d.setColor(Color.MAGENTA);
        d.drawText((d.getWidth() / 2) - 90 , d.getHeight() / 2, count, 150);
        if (this.countFrom != this.counter) {
            this.sleeper.sleepFor((int) this.wait);
        }
        if (this.counter >= 0) {
            this.counter--;
        } else {
            this.end = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.end;
    }

}
