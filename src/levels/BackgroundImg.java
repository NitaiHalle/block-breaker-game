package levels;

import java.awt.Image;

import biuoop.DrawSurface;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public class BackgroundImg implements Sprite {
    private Image image;
    /**
     *
     * @param i img.
     */
    public BackgroundImg(Image i) {
        this.image = i;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    @Override
    public void timePassed(double dt) {
    }
}
