package reader;

import sprites.Block;
/**
 *
 * @author nitai
 *
 */
public interface BlockCreator {
    // Create a block at the specified location.
    /**
     *
     * @param xpos x.
     * @param ypos y.
     * @return block.
     */
       Block create(int xpos, int ypos);
}
