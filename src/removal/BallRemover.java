package removal;

import genral.GameLevel;
import listeners.HitListener;
import sprites.Ball;
import sprites.Block;
/**
 *
 * @author nitai
 *
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     *
     * @param game Game Level.
     * @param remainingBalls Counter.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
        //if (this.remainingBalls.getValue() == 0) {
          //  }
    }
    /**
     *
     * @return Counter.
     */
    public Counter getCounter() {
        return this.remainingBalls;
    }
}
