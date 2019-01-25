package genral;

import java.io.File;

import java.io.IOException;
import java.util.List;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
//import biuoop.GUI;
import biuoop.KeyboardSensor;
import removal.Counter;
/**
 *
 * @author nitai
 *
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;
    private HighScoresTable table;
    private File f;
    /**
     *
     * @param ar a.
     * @param ks k.
     * @param table t.
     * @param f f.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable table, File f) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.table = table;
        this.f = f;
    }
    /**
     *
     * @param levels list.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor,
                    this.score, this.lives);
            level.initialize();
            while (level.getLives().getValue() > 0 && level.getBlocks() > 0) {
                level.playOneTurn();
            }

            if (this.lives.getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.keyboardSensor, false, this.score.getValue())));
                break;
             }
            if ((level.getBlocks() == 0) && (levels.indexOf(levelInfo) == (levels.size() - 1))) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,  KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.keyboardSensor, true, this.score.getValue())));
            }

        }
        System.out.println(this.table.getRank(this.score.getValue()));
        System.out.println(this.table.size());
        if (this.table.getRank(this.score.getValue()) <= this.table.size()) {
            DialogManager dialog = this.animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "what is your name?", "");
            ScoreInfo scoreForTable = new ScoreInfo(name, this.score.getValue());
            this.table.add(scoreForTable);
            try {
                this.table.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(name);
        }
        this.score = new Counter(0);
        this.lives = new Counter(7);
        HighScoresAnimation scoresTable = new HighScoresAnimation(this.table, this.keyboardSensor.toString());
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, scoresTable));
    }

}
