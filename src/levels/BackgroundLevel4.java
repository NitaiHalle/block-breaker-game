package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public class BackgroundLevel4 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(150 + i * 10, 400, 90 + i * 10, 600);
            d.drawLine(660 + i * 10, 450, 600 + i * 10, 600);
        }
        d.setColor(Color.white.darker());
        d.fillCircle(170, 400, 30);
        d.fillCircle(180, 420, 25);
        d.fillCircle(670, 450, 30);
        d.fillCircle(680, 470, 25);
        d.setColor(Color.gray);
        d.fillCircle(190, 410, 30);
        d.fillCircle(200, 400, 25);
        d.fillCircle(690, 460, 30);
        d.fillCircle(700, 450, 25);
        d.setColor(Color.darkGray);
        d.fillCircle(220, 420, 30);
        d.fillCircle(230, 400, 30);
        d.fillCircle(720, 470, 30);
        d.fillCircle(730, 450, 30);
    }

    @Override
    public void timePassed(double dt) {
    }

}
