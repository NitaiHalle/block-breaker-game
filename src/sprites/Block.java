package sprites;
//import java.awt.Color;

import java.awt.Image;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import biuoop.DrawSurface;
import genral.Collidable;
import genral.GameLevel;
import listeners.HitListener;
import genral.HitNotifier;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Line;
//import listeners.PrintingHitListener;
/**
 *
 * @author nitai
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //members
    private Rectangle sizeOfBlock;
    private java.awt.Color color;
    private int counter = -1;
    private Map<Integer, String> fill;
    private String stroke;
    private Map<Integer, Image> images;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    /**
     *
     * @param rect r.
     * @param numberHit h.
     * @param fills f.
     * @param stroke s.
     * @param image i.
     */
    public Block(Rectangle rect, int numberHit, Map<Integer, String> fills, String stroke,
            Map<Integer, Image> image) {
        this.sizeOfBlock = rect;
        this.fill = fills;
        this.stroke = stroke;
        this.images = image;
        this.counter = numberHit;
    }
    /**
     *
     * @param rect r.
     * @param color c.
     * @param numberHit hit.
     */
    public Block(Rectangle rect, java.awt.Color color, int numberHit) {
        this.sizeOfBlock = rect;
        this.color = color;
        this.counter = numberHit;
    }
    /**
     *
     * @param upperLeft location.
     * @param width size.
     * @param height size.
     * @param color c.
     * @param numberHit counter.
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color, int numberHit) {
        this.sizeOfBlock = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.counter = numberHit;
    }
    /**
     * @return size of block
     */
    public Rectangle getCollisionRectangle() {
        return this.sizeOfBlock;
    }
    /**
     * @param hitter ball.
     * @param currentVelocity c.
     * @param collisionPoint p.
     * @return velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
         Line up, down, right, left;
         up = this.sizeOfBlock.getUp();
         down = this.sizeOfBlock.getDown();
         right = this.sizeOfBlock.getRight();
         left = this.sizeOfBlock.getLeft();
         boolean changeX, changeY;
         changeX = false;
         changeY = false;
         if (up.pointOnLine(collisionPoint) || down.pointOnLine(collisionPoint)) {
             changeY = true;
         }
         if (right.pointOnLine(collisionPoint) || left.pointOnLine(collisionPoint)) {
             changeX = true;
         }
         if (changeX) {
             currentVelocity.setDx(-currentVelocity.getDx());
         }
         if (changeY) {
             currentVelocity.setDy(-currentVelocity.getDy());
         }
         if (changeY || changeX) {
             this.counter--;
         }
         this.notifyHit(hitter);
         return currentVelocity;
    }
    /**
     *
     * @param surface draw
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.sizeOfBlock.getUpperLeft().getX();
        int y = (int) this.sizeOfBlock.getUpperLeft().getY();
        int width = (int) this.getCollisionRectangle().getWidth();
        int highet = (int) this.getCollisionRectangle().getHeight();
        String color1;
         if (this.fill != null) {
            if (this.fill.containsKey(this.counter)) {
                if (this.fill.get(this.counter).startsWith("image")) {
                    surface.drawImage(x, y, this.images.get(this.counter));
                } else {
                    color1 = this.fill.get(this.counter);
                    color1 = color1.substring(6);
                    this.color = ColorsParser.colorFromString(color1);
                    surface.setColor(this.color);
                    surface.fillRectangle(x, y, width, highet);
                }
            } else {
                if (this.fill.get(-1).startsWith("image")) {
                    surface.drawImage(x, y, this.images.get(-1));
                } else {
                    color1 = this.fill.get(-1);
                    color1 = color1.substring(6);
                    this.color = ColorsParser.colorFromString(color1);
                    surface.setColor(this.color);
                    surface.fillRectangle(x, y, width, highet);
                }
            }
        } else {
            surface.setColor(this.color);
            surface.fillRectangle(x, y, width, highet);
        }
         if (this.stroke != null) {
             color1 = this.stroke.substring(6);
             this.color = ColorsParser.colorFromString(color1);
             surface.setColor(this.color);
             surface.drawRectangle(x, y, width, highet);

         }
        /**
        surface.setColor(this.color);
        int x = (int) this.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) this.getCollisionRectangle().getWidth();
        int highet = (int) this.getCollisionRectangle().getHeight();
        surface.fillRectangle(x, y, width, highet);
        surface.setColor(java.awt.Color.black);
        surface.drawRectangle(x, y, width, highet);
        //if (this.counter > 0) {
          //  surface.drawText(x + (width / 2) , y + (highet / 2)  , Integer.toString(this.counter), 15);
        //} // else {
           // surface.drawText(x1 + (width / 2) , y1 + (highet / 2) , "x", 15);
        //}**/
    }
    /**
     *@param dt d.
     */
    public void timePassed(double dt) {
    }
    /**
     *
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     *
     * @param game Game Level.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     *
     * @param hitter ball.
     */
    private void notifyHit(Ball hitter) {
          // Make a copy of the hitListeners before iterating over them.
          List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
          // Notify all listeners about a hit event:
          for (HitListener hl : listeners) {
             hl.hitEvent(this, hitter);
    }
}
    /**
     *
     * @return get Hit Points.
     */
    public int getHitPoints() {
        return this.counter;
    }
    /**
     *
     * @param c color.
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }
}