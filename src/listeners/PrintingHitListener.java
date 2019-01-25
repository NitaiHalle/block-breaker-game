package listeners;

import sprites.Ball;
import sprites.Block;
/**
 *
 * @author nitai
 *
 */
public class PrintingHitListener implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }

}
