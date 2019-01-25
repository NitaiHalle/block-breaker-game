package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 * @author nitai
 *
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     *
     * @param sensor s.
     * @param key k.
     * @param animation a.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.ks = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.ks.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
         if (!this.ks.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
