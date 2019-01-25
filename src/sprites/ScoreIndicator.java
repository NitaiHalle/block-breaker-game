package sprites;

import java.awt.Color;

import biuoop.DrawSurface;
import genral.GameLevel;
/**
 *
 * @author nitai
 *
 */
public class ScoreIndicator implements Sprite {
    private GameLevel game;
    /**
     *
     * @param g Game Level.
     */
    public ScoreIndicator(GameLevel g) {
        this.game = g;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + Integer.toString(this.game.getScore().getValue()), 15);
    }

    @Override
    public void timePassed(double dt) {
    }

}
