package sprites;
import java.awt.Color;

import biuoop.DrawSurface;
import genral.Collidable;
import genral.CollisionInfo;
import genral.GameEnvironment;
import genral.GameLevel;
import geometry.Point;
import geometry.Velocity;
import geometry.Line;

/**
*This class does Ball.
*@author Nitai Halle.
*@version 1
*@return Ball.
*/
public class Ball implements Sprite {
    //members
    private java.awt.Color color;
    private Point center;
    private int radius;
    private Velocity velocity;
    private GameEnvironment environment;
    //constructors
    /**
        *Line constructor function.
        *@param center get center point.
        *@param r get radius
        *@param color get color
        */
    public Ball(Point center, int r, java.awt.Color color) {
        this((int) center.getX(), (int) center.getY(), r, color);
    }
    /**
        *Line constructor function.
        *@param x get parameter x center point.
        *@param y get parameter y center point.
        *@param r get radius.
        *@param color get color.
        */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.color = color;
        this.radius = r;
    }
    //accessors
    /**
     * getX function.
     * @return int x.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * getY function.
     * @return int y.
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * getSize function.
     * @return int radius.
     */
    public int getSize() {
        return this.radius;
    }
    /**
     *
     * @return center.
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     *
     * @param x location.
     * @param y location.
     */
    public void setCenter(double x, double y) {
        this.center.setX(x);
        this.center.setY(y);
    }
    /**
     * getColor function.
     * @return java.awt.Color color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * drawOn function.
     * @param surface get draw surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }
    /**
     * setVelocity function.
     * @param v get velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * setVelocity function.
     * @param dx get dx.
     * @param dy get dy.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * getVelocity function.
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     *
     * @param e environment.
     */
    public void setGameEnvironment(GameEnvironment e) {
        this.environment = e;
    }
    /**
     * @param dt d.
     * moveOneStep function.
     */
    public void moveOneStep(double dt) {
        CollisionInfo closest;
        closest = this.environment.getClosestCollision(this.trajectory(dt));
        Point collisionPoint;
        Collidable object;
        if (closest != null) {
            collisionPoint = closest.collisionPoint();
            object = closest.collisionObject();
            this.almost(collisionPoint);
            this.setVelocity(object.hit(this, collisionPoint, this.getVelocity()));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        }
    }
    /**
     *
     *@param dt d.
     * @return line trajectory.
     */
    public Line trajectory(double dt) {
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.velocity.getDx() * dt, this.center.getY() + this.velocity.getDy() * dt);
        return trajectory;
    }
    /**
     *@param dt d.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }
    /**
     *
     * @param collision point.
     */
    private void almost(Point collision) {
        if (this.velocity.getDx() > 0) {
            this.center.setX(collision.getX() - 1);
        }
        if (this.velocity.getDx() < 0) {
            this.center.setX(collision.getX() + 1);
        }
        if (this.velocity.getDy() > 0) {
            this.center.setY(collision.getY() - 1);
        }
        if (this.velocity.getDy() < 0) {
            this.center.setY(collision.getY() + 1);
        }
    }
    /**
     *
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     *
     * @param g game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}