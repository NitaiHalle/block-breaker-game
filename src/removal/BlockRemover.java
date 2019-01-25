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
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlock;
    /**
     *
     * @param game game.
     * @param removedBlocks counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlock = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlock.decrease(1);
        }
        //if (this.remainingBlock.getValue() == 0) {
            //System.out.println("over");
          //  }
    }
    /**
     *
     * @return counter.
     */
    public Counter getCounter() {
        return this.remainingBlock;
    }
}
