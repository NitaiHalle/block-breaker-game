package sprites;

import java.awt.Color;

import biuoop.DrawSurface;
import genral.GameLevel;
/**
 *
 * @author nitai
 *
 */
public class NameLevel implements Sprite {
    private GameLevel game;
    /**
     *
     * @param g Game Level.
     */
    public NameLevel(GameLevel g) {
        this.game = g;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(500, 15, "Level Name: " + this.game.getName(), 15);
    }

    @Override
    public void timePassed(double dt) {
    }

}
