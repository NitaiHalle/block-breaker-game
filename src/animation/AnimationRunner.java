package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 *
 * @author nitai
 *
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    /**
     *
     * @param g gui.
     */
    public AnimationRunner(GUI g) {
        this.framesPerSecond = 60;
        this.gui = g;
        this.sleeper = new Sleeper();
    }
    /**
     *
     * @param animation animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        double dt = 1.0 / 60;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, dt);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     *
     * @return g.
     */
    public GUI getGui() {
        return this.gui;
    }

}
