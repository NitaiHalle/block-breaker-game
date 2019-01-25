package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public class BackgroundLevelOne implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(d.getWidth() / 2, d.getHeight() / 3, 50);
        d.drawCircle(d.getWidth() / 2, d.getHeight() / 3, 75);
        d.drawCircle(d.getWidth() / 2, d.getHeight() / 3, 100);
        d.drawLine(430, d.getHeight() / 3, 520, d.getHeight() / 3);
        d.drawLine(280, d.getHeight() / 3, 370, d.getHeight() / 3);
        d.drawLine(d.getWidth() / 2, 80, d.getWidth() / 2, 170);
        d.drawLine(d.getWidth() / 2, 320, d.getWidth() / 2, 230);
    }

    @Override
    public void timePassed(double dt) {
    }
}
