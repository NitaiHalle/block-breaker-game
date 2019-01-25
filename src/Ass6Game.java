//import java.io.BufferedReader;

import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;

//import animation.Animation;
import animation.AnimationRunner;
//import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import animation.MenuSelction;
import animation.ReadLevelSet;
import animation.Task;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import genral.GameFlow;
import genral.HighScoresTable;
import genral.LevelInformation;
//import genral.ScoreInfo;
import levels.Level3;
import levels.Level4;
import levels.LevelOne;
//import levels.LevelSpecificationReader;
import levels.LevelTwo;
/**
 *
 * @author nitai
 *
 */
public class Ass6Game  {
    /**
     *
     * @param args from user.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        //Animation a1 = new EndScreen(ks, false, 19);
        //Animation a2 = new EndScreen(ks, true, 45);
        //Animation a1k = new KeyPressStoppableAnimation(ks, "m", a1);
        //Animation a2k = new KeyPressStoppableAnimation(ks, "m", a2);
        //ar.run(a1k);
        //ar.run(a2k);
        InputStreamReader reader = null;
       // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        //reader = new InputStreamReader(is);
        //is = ClassLoader.getSystemClassLoader().getResourceAsStream("image.png");
       // List<LevelInformation> l = LevelSpecificationReader.fromReader(reader);
        /**try {
            l = LevelSpecificationReader.fromReader
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        **/
        if (args.length == 0) {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            reader = new InputStreamReader(is);
        } else {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
            reader = new InputStreamReader(is);
        }
        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>(ks, ar);
        File f = new File("highscores");
        HighScoresTable table = HighScoresTable.loadFromFile(f);
        if (f.exists()) {
            table = HighScoresTable.loadFromFile(f);
        } else {
            table = new HighScoresTable(4);
            try {
                System.out.println("main");
                table.save(f);
            } catch (IOException e) {
                System.out.println("main");
                e.printStackTrace();
            }
        }
        final HighScoresTable high = table;
        Task<Void> showHiScoresTask  = new Task<Void>() {

            @Override
            public Void run() {
                KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(high, KeyboardSensor.SPACE_KEY));
                ar.run(kpsa);
                return null;
            }

        };
        GameFlow game = new GameFlow(ar, ks, table, f);
        List<LevelInformation> regular = new ArrayList<LevelInformation>();
        regular.add(new LevelOne());
        regular.add(new LevelTwo());
        regular.add(new Level3());
        regular.add(new Level4());
        List<LevelInformation> fromUser = new ArrayList<LevelInformation>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
            case "1":
                fromUser.add(new LevelOne());
                break;
            case "2":
                fromUser.add(new LevelTwo());
                break;
            case "3":
                fromUser.add(new Level3());
                break;
            case "4":
                fromUser.add(new Level4());
                break;
            default:
                break;
            }
        }
     //   if (!l.isEmpty()) {
            //game.runLevels(fromUser);
       // } else {
        // regular = l;
          //  game.runLevels(regular);
       // }
        // game.initialize();
        // game.run();level4
     /**   Task<Void> runLevels  = new Task<Void>() {

            @Override
            public Void run() {
//                game.runLevels(l);
                return null;
            }
        };**/
        Task<Void> quit = new Task<Void>() {

            @Override
            public Void run() {
                ar.getGui().close();
                System.exit(0);
                return null;
            }

        };
        Menu<Task<Void>> sub = new MenuAnimation<Task<Void>>(ks, ar);
        ReadLevelSet set = new ReadLevelSet();
        List<MenuSelction<Task<Void>>> selections = set.toRead(reader, f, ar, table);
        for (MenuSelction<Task<Void>> select : selections) {
            sub.addSelection(select.getKey(), select.getMessage(), select.task());
        }

        menu.addSubMenu("s", "to start the game", sub);
        menu.addSelection("h", "to see the high scores ", showHiScoresTask);
        menu.addSelection("q", "to quit", quit);
        ar.run(menu);
        while (true) {
            ar.run(menu);
            menu.getStatus().run();
            menu.getBack();
        }
    }
}
