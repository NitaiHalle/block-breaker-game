package listeners;
import removal.Counter;
import sprites.Ball;
import sprites.Block;
/**
 *
 * @author nitai
 *
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     *
     * @param scoreCounter score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() > 0) {
            this.currentScore.increase(5);
        }
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(15);
        }
    }

}
