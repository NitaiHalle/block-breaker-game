package geometry;

import java.util.List;

/**
*This class does Line.
*@author Nitai Halle.
*@version 1
*@return line Line.
*/
public class Line {
    //members
    private Point start;
    private Point end;
    //constructors
    /**
    *Line constructor function.
    *@param start get point and set start.
    *@param end get  point and set end.
    */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     *Line constructor function.
     *@param x1 get x and set them in the accordingly point.
     *@param y1 get y and set them in the accordingly point.
     *@param x2 get x and set them in the accordingly point.
     *@param y2 get y and set them in the accordingly point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startLine = new Point(x1, y1);
        Point endLine = new Point(x2, y2);
        this.start = startLine;
        this.end = endLine;
    }
    /**
     *length function.
     *@return double length.
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     *middle function.
     *@return Point middle.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(x, y);
        return middle;
    }
    /**
     *start function.
     *@return Point start.
     */
    public Point start() {
        return this.start;
    }
    /**
     *end function.
     *@return Point end.
     */
    public Point end() {
        return this.end;
    }
    /**
    *intersectionBetweenLines function.
    *@param other get Line.
    *@return Point intersection between the lines
    */
    public Point intersectionBetweenLines(Line other) {
        double slope = 0;
        double slopeOther = 0;
        double x, y;
        //check if the slopes existing and calculate them
        if (((this.end.getX() - this.start.getX()) != 0)
                &&
                ((other.end.getX() - other.start.getX()) != 0)) {
            slope = (this.end.getY() - this.start.getY())
                    /
                    (this.end.getX() - this.start.getX());
            slopeOther = (other.end.getY() - other.start.getY())
                    /
                    (other.end.getX() - other.start.getX());
            if (slope == slopeOther) {
                return null;
            }
            x = (((slope * this.start.getX()) + other.start.getY())
                    -
                    ((slopeOther * other.start.getX()) + this.start.getY()))
                    /
                    (slope - slopeOther);
            y = (slope * (x - this.start.getX())) + this.start.getY();
        } else {
            if ((this.end.getX() - this.start.getX()) != 0) {
                x = other.end.getX();
                slope = (this.end.getY() - this.start.getY())
                        /
                        (this.end.getX() - this.start.getX());
                y = (slope * (x - this.start.getX())) + this.start.getY();
            } else if ((other.end.getX() - other.start.getX()) != 0) {
                x = this.end.getX();
                slopeOther = (other.end.getY() - other.start.getY())
                        /
                        (other.end.getX() - other.start.getX());
                y = (slopeOther * (x - other.start.getX())) + other.start.getY();
            } else {
                return null;
            }
        }
        Point intersection = new Point(x, y);
        return intersection;
    }
    /**
     *isIntersecting function.
     *@param other get Line.
     *@return boolean.true if is intersecting else false.
     */
    public boolean isIntersecting1(Line other) {
        double bigThisX, smallThisX, bigOtherX, smallOtherX;
        Point intersection = this.intersectionBetweenLines(other);
        if (intersection == null) {
            return false;
        }
        if (this.start.getX() < this.end.getX()) {
            smallThisX = this.start.getX();
            bigThisX = this.end.getX();
        } else {
            smallThisX = this.end.getX();
            bigThisX = this.start.getX();
        }
        if (other.start.getX() < other.end.getX()) {
            smallOtherX = other.start.getX();
            bigOtherX = other.end.getX();
        } else {
            smallOtherX = other.end.getX();
            bigOtherX = other.start.getX();
        }
        if (smallThisX == bigThisX) {
            if ((Math.max(other.start.getY(), other.end.getY()) > intersection.getY())
                    && (Math.min(other.start.getY(), other.end.getY()) < intersection.getY())) {
                return true;
            }
        }
        if (smallOtherX == bigOtherX) {
            if ((Math.max(this.start.getY(), this.end.getY()) > intersection.getY())
                    && (Math.min(this.start.getY(), this.end.getY()) < intersection.getY())) {
                return true;
            }
        }
        if (smallThisX <= intersection.getX()) {
            if (bigThisX >= intersection.getX()) {
                if (smallOtherX <= intersection.getX()) {
                    if (bigOtherX >= intersection.getX()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     *intersectionWith function.
     *@param other get Line.
     *@return Point intersection between the segments
     */
    public Point intersectionWith1(Line other) {
        if (this.isIntersecting(other)) {
            return this.intersectionBetweenLines(other);
        } else {
            return null;
        }
    }
    /**
     *equals function.
     *@param other get Line.
     *@return boolean true if the line it equal else false.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start)) {
            if (this.end.equals(other.end)) {
                return true;
            }
        }
        return false;
    }
    /**
     *
     * @param rect rectangle
     * @return close intersection point with the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints;
        intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closestPoint = new Point((Point) intersectionPoints.get(0));
        double minDistance = this.start.distance((Point) intersectionPoints.get(0));
        double currentDistance;
        for (int i = 0; i < intersectionPoints.size(); i++) {
            currentDistance = this.start.distance((Point) intersectionPoints.get(i));
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                closestPoint = new Point((Point) intersectionPoints.get(i));
            }
        }
        return closestPoint;
    }
    /**
     *
     * @return get Max X.
     */
    public double getMaxX() {
        return Math.max(this.start.getX(), this.end.getX());
    }
    /**
     *
     * @return get Min X.
     */
    public double getMinX() {
        return Math.min(this.start.getX(), this.end.getX());
    }
    /**
     *
     * @return get Max Y.
     */
    public double getMaxY() {
        return Math.max(this.start.getY(), this.end.getY());
    }
    /**
     *
     * @return get Min Y.
     */
    public double getMinY() {
        return Math.min(this.start.getY(), this.end.getY());
    }
    /**
     *
     * @param p point.
     * @return point On Line.
     */
    public boolean pointOnLine(Point p) {
        boolean b = true;
        if (p.getX() > this.getMaxX()) {
            b = false;
        }
        if (p.getX() < this.getMinX()) {
            b = false;
        }
        if (p.getY() > this.getMaxY()) {
            b = false;
        }
        if (p.getY() < this.getMinY()) {
            b = false;
        }
        return b;
    }
    /**
     *
     * @return get Distance X.
     */
    private double getDistanceX() {
        return (this.start.getX() - this.end.getX());
    }
    /**
     *
     * @return get Distance Y.
     */
    private double getDistanceY() {
        return (this.start.getY() - this.end.getY());
    }
    /**
     *
     * @return get Slope.
     */
    private double getSlope() {
        double slope = 0;
        slope = this.getDistanceY() / this.getDistanceX();
        return slope;
    }
    /**
     *
     * @param other line.
     * @return intersection point.
     */
    private Point intrsectionWithLineParallelToAxeY(Line other) {
        double x = this.start.getX();
        double y = other.getSlope() * (x - other.start.getX()) + other.start.getY();
        return new Point(x, y);
    }
    /**
     *
     * @param other line
     * @return intersection point.
     */
    public Point intersectionWith(Line other) {
        Point p = null;
        if ((this.getDistanceX() == 0) && (other.getDistanceX() == 0)) {
            p = null;
        } else if ((this.getDistanceX() == 0) && (other.getDistanceX() != 0)) {
            p = this.intrsectionWithLineParallelToAxeY(other);
        } else if ((this.getDistanceX() != 0) && (other.getDistanceX() == 0)) {
            p = other.intrsectionWithLineParallelToAxeY(this);
        } else if ((this.getDistanceX() != 0) && (other.getDistanceX() != 0)) {
            if (this.getSlope() == other.getSlope()) {
                p = null;
            } else {
                double x1 = this.start.getX();
                double y1 = this.start.getY();
                double m1 = this.getSlope();
                double x2 = other.start.getX();
                double y2 = other.start.getY();
                double m2 = other.getSlope();
                double x = ((m2 * x2) - (m1 * x1) + y1 - y2) / (m2 - m1);
                double y = m1 * (x - x1) + y1;
                p = new Point(x, y);
            }
        }
        if (p == null) {
            return null;
        } else if ((this.pointOnLine(p)) && (other.pointOnLine(p))) {
            p.setX((Math.round(p.getX()) * 1000) / 1000);
            p.setY((Math.round(p.getY()) * 1000) / 1000);
            return p;
        } else {
            return null;
        }
    }
    /**
     *
     * @param other line.
     * @return intersecting.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }
}
