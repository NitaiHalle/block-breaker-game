package genral;
import geometry.Point;
/**
 *
 * @author nitai
 *
 */
public class CollisionInfo {
    private Point collisionP;
    private Collidable collisionO;
    /**
     *
     * @param p point.
     * @param c collidable.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionO = c;
        this.collisionP = p;
    }
    /**
     *
     * @param c info
     */
    public CollisionInfo(CollisionInfo c) {
        this.collisionO = c.collisionO;
        this.collisionP = c.collisionP;
    }
    /**
     *
     * @return point
     */
    public Point collisionPoint() {
        return this.collisionP;
    }
    /**
     *
     * @return object.
     */
    public Collidable collisionObject() {
           return this.collisionO;
       }
}
