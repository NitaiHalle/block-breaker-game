package reader;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 *
 * @author nitai
 *
 */
public class BlocksDefinitionReader  {
    /**
     *
     * @param reader r
     * @return b.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        //System.out.println("BlocksFromSymbolsFactory");
        BufferedReader read = new BufferedReader(reader);
        String line = null;
        Map<String, BlockCreator> blockType = new TreeMap<String, BlockCreator>();
        Map<String, String> defaultBlock = new TreeMap<String, String>();
        Map<String, Integer> space = new TreeMap<String, Integer>();
        Map<String, String> bdef = new TreeMap<String, String>();
        //Map<String, String> sdef = new TreeMap<String, String>();
        List<String> info = new ArrayList<String>();
        info.add("symbol");
        info.add("height");
        info.add("width");
        info.add("hit_points");
        info.add("fill");
        info.add("stroke");
        boolean isCommentOrBlank;
        try {
            line = read.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            //System.out.println("BlocksFromSymbolsFactory");
            /**if (line.trim().length() == 0) {
                isCommentOrBlank = true;
            }
            **/
            isCommentOrBlank = false;
            if ((line.trim().length() == 0) || (String.valueOf(line.charAt(0)).equals("#"))) {
                isCommentOrBlank = true;
            }
            if (!isCommentOrBlank) {
                String [] lineInArray;
                String [] keyValue;
                if (line.startsWith("default")) {
                    line = line.substring(8);
                    lineInArray = line.split(" ");
                    for (String phrase : lineInArray) {
                        keyValue = phrase.split(":");
                        defaultBlock.put(keyValue[0], keyValue[1]);
                    }
                }
                if (line.startsWith("bdef")) {
                    line = line.substring(5);
                    lineInArray = line.split(" ");
                    for (String phrase : lineInArray) {
                        keyValue = phrase.split(":");
                        bdef.put(keyValue[0], keyValue[1]);
                    }
                    //need to check
                    for (Map.Entry<String, String> e : defaultBlock.entrySet()) {
                        if (!bdef.containsKey(e.getKey())) {
                            bdef.put(e.getKey(), e.getValue());
                        }
                    }
                    for (String s : info) {
                        if (!bdef.containsKey(s)) {
                            int d = 0;
                        }
                    }
                    BlockFactory b = new BlockFactory(bdef);
                    //System.out.println("blo");
                    //System.out.println(blockType);
                    blockType.put(bdef.get("symbol"), b);
                    bdef = new TreeMap<String, String>();
                }
                if (line.startsWith("sdef")) {
                    line = line.substring(5);
                    //lineInArray = line.split(" ");
                    //String symbolPart = lineInArray[0];
                    //String widthPart = lineInArray[1];
                    //String[] a1 = symbolPart.split(":");
                    //String[] a2 = widthPart.split(":");
                    //String symbol = a1[1];
                    //Integer width = Integer.parseInt(a2[1]);
                    line = line.replace(" ", ":");
                    lineInArray = line.split(":");
                    space.put(lineInArray[1], Integer.parseInt(lineInArray[3]));
                }
            }

            try {
                line = read.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new BlocksFromSymbolsFactory(space, blockType);
    }
}
