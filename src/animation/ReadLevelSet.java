package animation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import genral.GameFlow;
import genral.HighScoresTable;
import genral.LevelInformation;
import levels.LevelSpecificationReader;
/**
 *
 * @author nitai
 *
 */
public class ReadLevelSet {
/**
 *
 * @param read r.
 * @param f f.
 * @param ar a.
 * @param table t.
 * @return list.
 */
public List<MenuSelction<Task<Void>>> toRead(java.io.Reader read, File f,
        AnimationRunner ar, HighScoresTable table) {
    List<MenuSelction<Task<Void>>> selections = new ArrayList<MenuSelction<Task<Void>>>();
    BufferedReader reader = new BufferedReader(read);
    String line = null;
    try {
        line = reader.readLine();
        while (line != null) {
            String [] split = line.split(":");
            String fp = reader.readLine();
            Task<Void> t = new Task<Void>() {

                @Override
                public Void run() {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fp);
                    InputStreamReader in = new InputStreamReader(is);
                    //LevelSpecificationReader lev = new LevelSpecificationReader();
                    List<LevelInformation> levels = LevelSpecificationReader.fromReader(in);
                    GameFlow game = new GameFlow(ar, ar.getGui().getKeyboardSensor(), table, f);
                    game.runLevels(levels);
                    return null;
                }
            };
            selections.add(new MenuSelction<Task<Void>>(split[0], split[1], t));
            line = reader.readLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return selections;
    }
}
