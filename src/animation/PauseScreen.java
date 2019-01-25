package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 * @author nitai
 *
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     *
     * @param k Keyboard Sensor.
     */
    public PauseScreen(KeyboardSensor k) {
          this.keyboard = k;
          this.stop = false;
       }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
