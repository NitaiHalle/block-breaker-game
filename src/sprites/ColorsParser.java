package sprites;

import java.awt.Color;
/**
 *
 * @author nitai
 *
 */
public class ColorsParser {
    // parse color definition and return the specified color.
       /**
        *
        * @param s s.
        * @return c.
        */
       public static java.awt.Color colorFromString(String s) {
           Color c = null;
           if (s.contains("RGB")) {
               s = s.substring(4, s.indexOf(")"));
               String[] rgb = s.split(",");
               c = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
               return c;
           } else {
               s = s.replace("(", "");
               s = s.replace(")", "");
               switch (s) {
               case "red" :
                   c = Color.RED;
                   break;
               case "blue" :
                   c = Color.blue;
                   break;
               case "black" :
                   c = Color.black;
                   break;
               case "cyan" :
                   c = Color.cyan;
                   break;
               case "gray" :
                   c = Color.gray;
                   break;
               case "lightGray" :
                   c = Color.lightGray;
                   break;
               case "green" :
                   c = Color.green;
                   break;
               case "orange" :
                   c = Color.orange;
                   break;
               case "pink" :
                   c = Color.pink;
                   break;
               case "white" :
                   c = Color.white;
                   break;
               case "yellow" :
                   c = Color.yellow;
                   break;
               default:
                   break;
               }
               return c;
           }
       }

}
