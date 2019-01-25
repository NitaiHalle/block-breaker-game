package reader;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
/**
 *
 * @author nitai
 *
 */
public class BlockFactory implements BlockCreator {
    private Map<String, String> blockInString;
    private int height;
    private int width;
    private Map<Integer, String> fill = new TreeMap<Integer, String>();
    private int hitPoint;
    private String stroke;
    private Map<Integer, Image> images = new TreeMap<Integer, Image>();
    /**
     *
     * @param block b.
     */
    public BlockFactory(Map<String, String> block) {
        //System.out.println("BlockFactory");
        this.blockInString = block;
        this.height = Integer.parseInt(this.blockInString.get("height"));
        this.width = Integer.parseInt(this.blockInString.get("width"));
        this.hitPoint = Integer.parseInt(this.blockInString.get("hit_points"));
        if (blockInString.containsKey("stroke")) {
            this.stroke = this.blockInString.get("stroke");
        } else {
            this.stroke = null;
        }
        Map<String, String> fills = new TreeMap<String, String>();
        /**for (String sfill : blockInString.keySet()) {
            if (sfill.startsWith("fill-")) {
                fills.put(sfill, blockInString.get("fill-"));
                System.out.println("dd");
            }
        }
        **/
        for (Map.Entry<String, String> e : blockInString.entrySet()) {
            if (e.getKey().startsWith("fill-")) {
                fills.put(e.getKey(), e.getValue());
                //System.out.println("dd");
            }
        }
        /**for (String s : fills.keySet()) {
            String s1 = s.substring(5);
            this.fill.put(Integer.parseInt(s1), blockInString.get(s));
        }**/
        Map<Integer, String> color = new TreeMap<Integer, String>();
        for (Map.Entry<String, String> e : fills.entrySet()) {
            String s1 = e.getKey();
            s1 = s1.substring(5);
            color.put(Integer.parseInt(s1), e.getValue());
        }
        if (this.blockInString.containsKey("fill")) {
            color.put(-1, this.blockInString.get("fill"));
        } else {
            color.put(-1, this.blockInString.get("fill-1"));
        }
        //System.out.println(fill);
        this.fill = color;
        for (int i : this.fill.keySet()) {
            if (this.fill.get(i).startsWith("image")) {
                String s = this.fill.get(i).substring(6, this.fill.get(i).length() - 1);
                this.images.put(i, readPath(s));
            }
        }
    }

    @Override
    public Block create(int xpos, int ypos) {
        return new Block(new Rectangle(new Point(xpos, ypos), this.width, this.height),
                this.hitPoint, this.fill, this.stroke, this.images);
    }
    /**
     *
     * @param s s.
     * @return img.
     */
    private Image readPath(String s) {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
        Image image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("problem with image");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

}
