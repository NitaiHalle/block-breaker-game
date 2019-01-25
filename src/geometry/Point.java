package geometry;
/**
*This class does point.
*@author Nitai Halle.
*@version 1
*@return point Point.
*/
public class Point {
    //members
    private double x;
    private double y;
    //constructor
    /**
    *Point constructor function.
    *@param x get x  and set x parameter point.
    **@param y get y  and set y parameter point.
    */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     *
     * @param p copy.
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }
     /**
     *distance function.
     *@param other get point and calculate the distance from point.
     *@return double distance.
     */
    public double distance(Point other) {
        double distance, dx, dy;
        dx = this.x - other.getX();
        dy = this.y - other.getY();
        distance = Math.sqrt((dx * dx) + (dy * dy));
        return distance;
    }
    /**
    *equals function.
    *@param other get point and check if it equal to point.
    *@return boolean true if it equal else false.
    */
    public boolean equals(Point other) {
        if (this.x == other.x) {
            if (this.y == other.y) {
                return true;
            }
        }
        return false;
    }
    /**
    *getX function.
    *@return double x parameter from point.
    */
    public double getX() {
        return this.x;
    }
    /**
    *getY function.
    *@return double y parameter from point.
    */
    public double getY() {
        return this.y;
    }
    /**
    *setX function.
    *@param x1 set x parameter from point
    */
    public void setX(double x1) {
        this.x = x1;
    }
    /**
    *setY function.
    *@param y1 set y parameter from point
    */
    public void setY(double y1) {
        this.y = y1;
    }
}
