package sprites;

import java.awt.Color;

import biuoop.DrawSurface;
import genral.GameLevel;
/**
 *
 * @author nitai
 *
 */
public class LivesIndicator implements Sprite {
    private GameLevel game;
    /**
     *
     * @param g Game Level.
     */
    public LivesIndicator(GameLevel g) {
        this.game = g;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, 15, "live : " + Integer.toString(this.game.getLives().getValue()), 15);
    }

    @Override
    public void timePassed(double dt) {
    }

}
