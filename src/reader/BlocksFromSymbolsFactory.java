package reader;

import java.util.Map;

import sprites.Block;
/**
 *
 * @author nitai
 *
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    /**
     *
     * @param spacerWidths s.
     * @param blockCreators b.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths,
            Map<String, BlockCreator> blockCreators) {
        this.blockCreators = blockCreators;
        this.spacerWidths = spacerWidths;
    }
    // returns true if 's' is a valid space symbol.
    /**
     *
     * @param s s.
     * @return b.
     */
    public boolean isSpaceSymbol(String s) {
        boolean b;
        if (this.spacerWidths.containsKey(s)) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }
    // returns true if 's' is a valid block symbol.
    /**
     *
     * @param s s.
     * @return b.
     */
    public boolean isBlockSymbol(String s) {
        boolean b;
        if (this.blockCreators.containsKey(s)) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }
    /**
     *
     * @param s s.
     * @return s.
     */
    public int getSpaceWidth(String s) {
           return this.spacerWidths.get(s);
        }
    /**
     *
     * @param s s.
     * @param x x.
     * @param y y.
     * @return block.
     */
    public Block getBlock(String s, int x, int y) {
       return ((BlockFactory) this.blockCreators.get(s)).create(x, y);
    }
}
