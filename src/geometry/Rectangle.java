package geometry;
import java.util.ArrayList;
/**
 *
 * @author nitai
 *
 */
public class Rectangle {
    //members
    private Point upperLeft;
    private double width;
    private double height;
    /**
     *
     * @param upperLeft set upperLeft.
     * @param width set width.
     * @param height set height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     *
     * @param rect set rectangle.
     */
    public Rectangle(Rectangle rect) {
        this.upperLeft = new Point(rect.upperLeft);
        this.width = rect.width;
        this.height = rect.height;
    }
    /**
     *
     * @return  upperLeft.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     *
     * @return width.
     */
    public double getWidth() {
        return width;
    }
    /**
     *
     * @return height.
     */
    public double getHeight() {
        return height;
    }
    /**
     *
     * @return upperRight.
     */
    public Point getUpperRight() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return upperRight;
    }
    /**
     *
     * @return downLeft.
     */
    public Point getDownLeft() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return downLeft;
    }
    /**
     *
     * @return downRight.
     */
    public Point getDownRight() {
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return downRight;
    }
    /**
     *
     * @return up.
     */
    public Line getUp() {
        Line up = new Line(this.upperLeft, this.getUpperRight());
        return up;
    }
    /**
     *
     * @return down.
     */
    public Line getDown() {
        Line down = new Line(this.getDownLeft(), this.getDownRight());
        return down;
    }
    /**
     *
     * @return right.
     */
    public Line getRight() {
        Line right = new Line(this.getDownRight(), this.getUpperRight());
        return right;
    }
    /**
     *
     * @return left.
     */
    public Line getLeft() {
        Line left = new Line(this.getUpperLeft(), this.getDownLeft());
        return left;
    }
    /**
     *
     * @param line line.
     * @return list.
     */
    public java.util.List intersectionPoints(Line line) {
        java.util.List intersectionList = new ArrayList();
        Line [] lineOfRectangle = new Line[4];
        lineOfRectangle[0] = this.getUp();
        lineOfRectangle[1] = this.getDown();
        lineOfRectangle[2] = this.getLeft();
        lineOfRectangle[3] = this.getRight();
        for (int i = 0; i < 4; i++) {
            if (lineOfRectangle[i].isIntersecting(line)) {
                intersectionList.add(lineOfRectangle[i].intersectionWith(line));
            }
        }
        return intersectionList;
    }
    /**
     *
     * @param p point.
     * @return if point in.
     */
    public boolean isPointIn(Point p) {
        if ((p.getX() < this.getDownRight().getX())) {
            if (p.getX() > this.getDownLeft().getX()) {
                if (p.getY() < this.getDownLeft().getY()) {
                    if (p.getY() > this.getUpperLeft().getY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     *
     * @param p point.
     */
    public void setUpLeft(Point p) {
        this.upperLeft = p;
    }
}
