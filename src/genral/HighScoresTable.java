package genral;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author nitai
 *
 */
public class HighScoresTable implements Serializable {
    private int numberHigh;
    private List<ScoreInfo> scores;
    private static final long serialVersionUID = 1L;
    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     * @param size s.
     */
    public HighScoresTable(int size) {
        this.numberHigh = size;
        this.scores = new ArrayList<ScoreInfo>();
    }
    /**
     *Add a high-score.
     * @param score score.
     */
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());
        if (rank > this.size()) {
            return;
        }
        this.scores.add(rank - 1, score);
        while (this.scores.size() > this.size()) {
            this.scores.remove(this.scores.size() - 1);
        }
    }
    /**
     *
     * @return size of table.
     */
    public int size() {
        return this.numberHigh;
    }
    /**
     *
     * @return list of high score.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    /**
     *
     * @param score s.
     * @return r.
     */
    public int getRank(int score) {
        int rank;
        for (rank = 0; rank < this.scores.size(); rank++) {
            if (score > this.scores.get(rank).getScore()) {
                break;
            }
        }
        rank++;
        return rank;
    }

    // Clears the table
    /**
     *
     */
    public void clear() {
        this.scores.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    /**
     *
     * @param filename f.
     * @throws IOException e.
     * @throws ClassNotFoundException e.
     */
    public void load(File filename) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        HighScoresTable table;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            table = (HighScoresTable) in.readObject();
            this.scores = table.scores;
        } catch (FileNotFoundException e) {
            System.err.println("not find file: " + filename);
            table = new HighScoresTable(4);
            table.save(filename);
            this.scores = table.scores;
            this.numberHigh = table.numberHigh;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                System.err.println("failed closing file: " + filename);
            }
        }
    }

    // Save table data to the specified file.
    /**
     *
     * @param filename f.
     * @throws IOException e.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println("failed saveing file");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            }  catch (IOException e) {
                System.err.println("failed closing file: " + filename);
            }
        }
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    /**
     *
     * @param filename f.
     * @return t.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(4);
        try {
            if (!filename.exists()) {
                return table;
            }
            table.load(filename);
            return table;
        } catch (FileNotFoundException e) {
            System.err.println("not find file: " + filename);
            return table;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.err.println("failed read");
            return table;
        }
    }

}
