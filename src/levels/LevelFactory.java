package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import genral.LevelInformation;
import geometry.Velocity;
import reader.BlocksDefinitionReader;
import reader.BlocksFromSymbolsFactory;
import sprites.Block;
import sprites.ColorsParser;
import sprites.Sprite;
/**
 *
 * @author nitai
 *
 */
public class LevelFactory implements LevelInformation {
    private BlocksFromSymbolsFactory bfsf;
    private Map<String, String> levelInfo;
    private List<String> infoBlocks;
    /**
     *
     * @param info i.
     * @param infoBlocks b.
     */
    public LevelFactory(Map<String, String> info, List<String> infoBlocks) {
        this.infoBlocks = infoBlocks;
        this.levelInfo = info;

    }

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velo = new ArrayList<Velocity>();
        String voleInStr = this.levelInfo.get("ball_velocities");
        voleInStr = voleInStr.replace(" ", ",");
        String[] inArray = voleInStr.split(",");
        for (int i = 0; i < inArray.length; i += 2) {
            velo.add(Velocity.fromAngleAndSpeed(Integer.parseInt(inArray[i]),
                    Integer.parseInt(inArray[1 + i])));
        }
        return velo;
    }

    @Override
    public int paddleSpeed() {
        int speed = Integer.parseInt(this.levelInfo.get("paddle_speed"));
        return speed;
    }

    @Override
    public int paddleWidth() {
        int width = Integer.parseInt(this.levelInfo.get("paddle_width"));
        return width;
    }

    @Override
    public String levelName() {
        return this.levelInfo.get("level_name");
    }

    @Override
    public Sprite getBackground() {
        String bkg = this.levelInfo.get("background");
        bkg = bkg.substring(6, bkg.length() - 1);
        if (bkg.contains("image")) {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(bkg);
            Image image = null;
            try {
                image = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new BackgroundImg(image);
        } else {
            Color c = ColorsParser.colorFromString(bkg);
            return new Sprite() {

                @Override
                public void drawOn(DrawSurface d) {
                    d.setColor(c);
                    d.fillRectangle(20, 40, 760, 560);
                }

                @Override
                public void timePassed(double dt) {
                }
            };
        }
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        java.io.Reader r = null;
        //System.out.println(this.levelInfo.get("block_definitions"));


        //InputStreamReader reader = null;
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream
        //reader = new InputStreamReader(is);
        //is = ClassLoader.getSystemClassLoader().getResourceAsStream("image.png");
        //List<LevelInformation> l = LevelSpecificationReader.fromReader(reader);
        //System.out.println("sdjnjkdcnks");
        //InputStreamReader reader = null;
        String f = this.levelInfo.get("block_definitions");
        //System.out.println(f);
       InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(f);
        //reader = new InputStreamReader(is);
        //is = ClassLoader.getSystemClassLoader().getResourceAsStream("image.png");
        //List<LevelInformation> l = LevelSpecificationReader.fromReader(reader);
       // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStrea
        // is = ClassLoader.getSystemClassLoader().getResourceAsStream("defi
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this)
        r = new InputStreamReader(is);
        this.bfsf = BlocksDefinitionReader.fromReader(r);
        int rHeighet = Integer.parseInt(this.levelInfo.get("row_height"));
        int x = Integer.parseInt(this.levelInfo.get("blocks_start_x"));
        int y = Integer.parseInt(this.levelInfo.get("blocks_start_y"));
        int moveX = x;
        int moveY = y;
        this.infoBlocks.remove("START_BLOCKS");
        for (String s : this.infoBlocks) {
            s = s.replace(" ", "");
            for (int i = 0; i < s.length(); i++) {
                String sym = String.valueOf(s.charAt(i));
                if (this.bfsf.isBlockSymbol(sym)) {
                    Block block = this.bfsf.getBlock(sym, moveX, moveY);
                    blocks.add(block);
                    moveX += block.getCollisionRectangle().getWidth();
                }
                if (this.bfsf.isSpaceSymbol(sym)) {
                    moveX += this.bfsf.getSpaceWidth(sym);
                }
            }
            moveY += rHeighet;
            moveX = x;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        int num = Integer.parseInt(this.levelInfo.get("num_blocks"));
        return num;
    }

}
