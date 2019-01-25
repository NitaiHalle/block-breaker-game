package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public class BackgroundLevel3 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN.darker());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.fillRectangle(100, 430, 100, 300);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(105 + i * 20 , 450 + j * 30, 10, 25);
            }
        }
        d.setColor(Color.darkGray);
        d.fillRectangle(134, 380, 33, 50);
        d.fillRectangle(146, 220, 10, 160);
        d.setColor(Color.yellow.brighter());
        d.fillCircle(151, 220, 15);
        d.setColor(Color.RED.brighter());
        d.fillCircle(151, 220, 10);
        d.setColor(Color.white);
        d.fillCircle(151, 220, 5);
    }

    @Override
    public void timePassed(double dt) {
    }

}
