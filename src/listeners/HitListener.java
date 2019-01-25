package listeners;
import sprites.Ball;
import sprites.Block;
/**
 *
 */
public interface HitListener {
     // This method is called whenever the beingHit object is hit.
     // The hitter parameter is the Ball that's doing the hitting.
    /**
     *
     * @param beingHit object is hit.
     * @param hitter is the Ball that's doing the hitting.
     */
     void hitEvent(Block beingHit, Ball hitter);
}
