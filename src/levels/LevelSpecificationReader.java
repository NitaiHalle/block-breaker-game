package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import genral.LevelInformation;
/**
 *
 * @author nitai
 *
 */
public class LevelSpecificationReader {
    /**
     *
     * @param reader r.
     * @return l.
     */
    public static List<LevelInformation> fromReader(java.io.Reader reader) {
        //System.out.println("LevelSpecificationReader");
        List<LevelInformation> info = new ArrayList<LevelInformation>();
        List<List<String>> splitLevel = splitLevels(reader);
        //System.out.println(splitLevel);
        List<String> blocks = new ArrayList<String>();
        Map<String, String> map = new TreeMap<String, String>();
        boolean flagBlocks = false;
        for (int i = 0; i < splitLevel.size(); i++) {
            for (int j = 0; j < splitLevel.get(i).size(); j++) {
                //String current = splitLevel.get(i).get(j);
                if (splitLevel.get(i).get(j).equals("START_BLOCKS")) {
                    //current.substring("START_BLOCKS".length());
                    flagBlocks = true;
                    j++;
                }
                if (splitLevel.get(i).get(j).equals("END_BLOCKS")) {
                    break;
                }
                if (!flagBlocks) {
                    //blocks.add(current);
                    String [] splitString = splitLevel.get(i).get(j).split(":");
                    map.put(splitString[0], splitString[1]);
                } else {
                    //String [] splitString = current.split(":");
                    //map.put(splitString[0], splitString[1]);
                    blocks.add(splitLevel.get(i).get(j));
                }
            }
            LevelFactory l = new LevelFactory(map, blocks);
            //System.out.println(map);
            //System.out.println(blocks);
            info.add(l);
            flagBlocks = false;
            blocks = new ArrayList<String>();
            map = new TreeMap<String, String>();
        }
        return info;
    }


    /**
     *
     * @param reader r.
     * @return l.
     */
    public static List<List<String>> splitLevels(java.io.Reader reader) {
        List<List<String>> splitLevel = new ArrayList<List<String>>();
        //List<String> singleLevel;
        //System.out.println("splitLevels");
        BufferedReader read = new BufferedReader(reader);
        String line = null;
        boolean isComentOrBlank = false;
        try {
            line = read.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            //System.out.println(line);
            if ((line.trim().length() == 0) || (String.valueOf(line.charAt(0)).equals("#"))) {
                isComentOrBlank = true;
            }
            //if (line.startsWith("#")) {
                //isComentOrBlank = true;
            //}
            if (isComentOrBlank) {
                if (line.equals("START_LEVEL")) {
                    List<String> singleLevel = new ArrayList<String>();
                    while (!line.equals("END_LEVEL")) {

                        singleLevel.add(line);
                        try {
                            line = read.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    singleLevel.remove(0);
                    //System.out.println(singleLevel);
                    splitLevel.add(new ArrayList<String>(singleLevel));
                    try {
                        line = read.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        line = read.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                try {
                    line = read.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return splitLevel;
    }

}
