package sprites;
import biuoop.DrawSurface;
/**
 *
 * @author nitai
 *
 */
public interface Sprite {
    // draw the sprite to the screen
    /**
     *
     * @param d surface.
     */
       void drawOn(DrawSurface d);
       // notify the sprite that time has passed
       /**
        *@param dt d.
        */
       void timePassed(double dt);
}
