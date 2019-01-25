package sprites;
import biuoop.DrawSurface;

import biuoop.KeyboardSensor;
import genral.Collidable;
import genral.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Line;
/**
 *
 * @author nitai
 *
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private java.awt.Color color;
    private int sizeOfLimit;
    private int speed;
    /**
     *
     * @param rect Rectangle.
     * @param color color.
     * @param sizeOfLimit limits.
     * @param k keyboard.
     * @param speed speed.
     */
    public Paddle(Rectangle rect, java.awt.Color color, int sizeOfLimit, KeyboardSensor k, int speed) {
        this.rect = new Rectangle(rect);
        this.color = color;
        this.sizeOfLimit = sizeOfLimit;
        this.keyboard = k;
        this.speed = speed;
    }
    /**
     *@param dt d.
     */
    public void moveLeft(double dt) {
        if ((keyboard.isPressed(KeyboardSensor.LEFT_KEY)) && (this.rect.getUpperLeft().getX() > this.sizeOfLimit)) {
            this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX()
                    - this.speed * dt, this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        }
    }
    /**
     *@param dt d.
     */
    public void moveRight(double dt) {
        if ((keyboard.isPressed(KeyboardSensor.RIGHT_KEY))
                && ((this.rect.getUpperLeft().getX() + this.rect.getWidth()) < (
                        800 - this.sizeOfLimit))) {
            this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX()
                    + this.speed * dt, this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        }
    }
    /**
     *
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     *@return rect.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     *@param d d.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(java.awt.Color.black);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }
    /**
     *@param dt d.
     */
    public void timePassed(double dt) {
        moveLeft(dt);
        moveRight(dt);
    }
    /**
     *@param hitter ball.
     *@param collisionPoint p.
     *@param currentVelocity v.
     *@return velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.rect.getUp().pointOnLine(collisionPoint)) {
            return this.hitPaddle(collisionPoint, currentVelocity);
        }
        if (this.rect.getLeft().pointOnLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (this.rect.getRight().pointOnLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        //if (this.rect.getDown().pointOnLine(collisionPoint)) {
          //  hitter.setCenter(hitter.getCenter().getX() + hitter.getVelocity().getDx()
            //        , hitter.getCenter().getY() + hitter.getVelocity().getDy());
        //}
            return currentVelocity;
    }
    /**
     *
     * @param collisionPoint point.
     * @param currentVelocity velocity.
     * @return velociy.
     */
    public Velocity hitPaddle(Point collisionPoint, Velocity currentVelocity) {
        double sizeOfSegment = this.rect.getWidth() / 5;
        Line l1, l2, l3, l4, l5;
        l1 = new Line(this.rect.getUpperLeft().getX(), this.rect.getUpperLeft().getY(),
                this.rect.getUpperLeft().getX() + sizeOfSegment, this.rect.getUpperLeft().getY());
        l2 =  new Line(this.rect.getUpperLeft().getX() + sizeOfSegment, this.rect.getUpperLeft().getY(),
                this.rect.getUpperLeft().getX() + (2 * sizeOfSegment), this.rect.getUpperLeft().getY());
        l3 =  new Line(this.rect.getUpperLeft().getX() + (2 * sizeOfSegment), this.rect.getUpperLeft().getY(),
                this.rect.getUpperLeft().getX() + (3 * sizeOfSegment), this.rect.getUpperLeft().getY());
        l4 =  new Line(this.rect.getUpperLeft().getX() + (3 * sizeOfSegment), this.rect.getUpperLeft().getY(),
                this.rect.getUpperLeft().getX() + (4 * sizeOfSegment), this.rect.getUpperLeft().getY());
        l5 =  new Line(this.rect.getUpperLeft().getX() + (4 * sizeOfSegment), this.rect.getUpperLeft().getY(),
                this.rect.getUpperRight().getX(), this.rect.getUpperLeft().getY());
        if (l1.pointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (l2.pointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (l3.pointOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (l4.pointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (l5.pointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }
    /**
     *
     * @param p point.
     */
    public void setLocation(Point p) {
        this.rect.setUpLeft(p);
    }
}