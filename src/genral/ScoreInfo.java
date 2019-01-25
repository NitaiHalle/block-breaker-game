package genral;

import java.io.Serializable;
/**
 *
 * @author nitai
 *
 */
public class ScoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;
    /**
     *
     * @param name n.
     * @param score s.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     *
     * @return n.
     */
    public String getName() {
        return this.name;
    }
    /**
     *
     * @return s.
     */
    public int getScore() {
        return this.score;
    }
    /**
     *@return s.
     */
    public String toString() {
        return (this.name + " - " + this.score);
    }
}
