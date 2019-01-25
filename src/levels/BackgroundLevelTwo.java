package levels;

import java.awt.Color;
//import java.util.ArrayList;
//import java.util.List;

import biuoop.DrawSurface;
//import geometry.Line;
//import geometry.Point;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public class BackgroundLevelTwo implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.orange);
        d.fillCircle(150, 150, 60);
        int x = 0;
        while (x < 780) {
            d.drawLine(150, 150, x, 270);
            x = x + 10;
        }
        d.setColor(Color.yellow.brighter());
        d.fillCircle(150, 150, 50);
    }

    @Override
    public void timePassed(double dt) {
    }
}
