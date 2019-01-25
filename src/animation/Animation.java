package animation;

import biuoop.DrawSurface;
/**
 *
 * @author nitai
 *
 */
public interface Animation {
    /**
     *
     * @param d d.
     * @param dt t.
     */
    void doOneFrame(DrawSurface d, double dt);
    /**
     *
     * @return stop.
     */
    boolean shouldStop();

}
