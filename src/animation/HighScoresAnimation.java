package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import genral.HighScoresTable;
/**
 *
 * @author nitai
 *
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private String endKey;
    private boolean stop;
    private KeyboardSensor k;
    /**
     *
     * @param scores s.
     * @param endKey e.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey) {
        this.scores = scores;
        this.endKey = endKey;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(270, 100, "high score", 50);
        d.setColor(Color.green);
        for (int i = 0; i < this.scores.size() && i < this.scores.getHighScores().size(); i++) {
            d.drawText(200, 200 + i * 35, this.scores.getHighScores().get(i).toString(), 30);
        }
        //if (this.k.isPressed(KeyboardSensor.SPACE_KEY)) {
            //this.stop = true;
        //}

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
