package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 * @author nitai
 *
 */
public class EndScreen implements Animation {
    private KeyboardSensor ks;
    private boolean win;
    private boolean stop;
    private int score;
    /**
     *
     * @param k keyboardSensor.
     * @param win win.
     * @param score score.
     */
    public EndScreen(KeyboardSensor k, boolean win, int score) {
        this.ks = k;
        this.score = score;
        this.win = win;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.win) {
            d.setColor(Color.BLUE);
            d.drawText(d.getWidth() / 4, d.getHeight() / 2, "You Win! Your score is " + this.score, 35);
        } else {
            d.setColor(Color.red);
            d.drawText(d.getWidth() / 4, d.getHeight() / 2, "Game Over. Your score is " + this.score, 35);
        }
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 4, 400, "press space key to continue", 35);
       // if (this.ks.isPressed(KeyboardSensor.SPACE_KEY)) {
         //   this.stop = true;
        //}
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
