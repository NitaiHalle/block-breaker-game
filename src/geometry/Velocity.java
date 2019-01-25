package geometry;
/**
*This class does Velocity.
*@author Nitai Halle.
*@version 1
*@return velocity.
*/
public class Velocity {
    //members
    private double dx = 0;
    private double dy = 0;
    //constructor
    /**
     * Velocity function.
     * @param dx get advance on x.
     * @param dy get advance on y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     *
     * @param v set new velocity.
     */
    public Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    /**
     * applyToPoint function.
     * @param p get point
     * @param dt d.
     * @return point after velocity.
     */
    public Point applyToPoint(Point p, double dt) {
        double x = p.getX() + this.dx * dt;
        double y = p.getY() + this.dy * dt;
         Point point = new Point(x, y);
         return point;
    }
    /**
     * getDx function.
     * @return dx.
     */
    public double getDx() {
        return dx;
    }
    /**
     * setDx function.
     * @param dx1 get dx
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }
    /**
     * setDy function.
     * @param dy1 get dy
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }
    /**
     * getDy function.
     * @return dy.
     */
    public double getDy() {
        return dy;
    }
    /**
     * fromAngleAndSpeed function.
     * @param angle get angle.
     * @param speed get speed.
     * @return velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * (Math.sin(Math.toRadians(angle)));
        double dy = -speed * (Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }
    /**
     *
     * @return speed
     */
    public double getSpeed() {
         double speed = Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
         return speed;
    }
}