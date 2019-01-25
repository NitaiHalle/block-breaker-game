package genral;
import java.util.ArrayList;
import geometry.Point;
import geometry.Line;
/**
 *
 * @author nitai
 *
 */
public class GameEnvironment {
    //members
    private ArrayList<Collidable> collidiable = new ArrayList<>();
    /**
     *
     */
    public GameEnvironment() {
        this.collidiable = new ArrayList<>();
    }
    /**
     *
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidiable.add(c);
    }
    /**
     *
     * @param c Collidable.
     */
    public void  removeCollidable(Collidable c) {
        this.collidiable.remove(c);
    }
    /**
     *
     * @param trajectory t.
     * @return closest CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint;
        Point currentPoint;
        boolean [] collidableFlag = new boolean[this.collidiable.size()];
           Collidable collidableCurrent;
           Collidable collidableClosest;
        double currentDistance;
        double minDistance;
        int j = 0;
        while ((j < this.collidiable.size())
                && (trajectory.closestIntersectionToStartOfLine(
                        ((Collidable) this.collidiable.get(j)).getCollisionRectangle()) == null)) {
            j++;
        }
        if (j == this.collidiable.size()) {
            return null;
        }
        for (int i = 0; i < this.collidiable.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(
                    ((Collidable) this.collidiable.get(i)).getCollisionRectangle()) == null) {
                collidableFlag[i] = false;
            } else {
                collidableFlag[i] = true;
            }
        }
        collidableClosest = (Collidable) this.collidiable.get(j);
        closestPoint = trajectory.closestIntersectionToStartOfLine(collidableClosest.getCollisionRectangle());
        minDistance = trajectory.start().distance(closestPoint);
        for (int i = 0; i < this.collidiable.size(); i++) {
            if (collidableFlag[i]) {
                collidableCurrent = (Collidable) this.collidiable.get(i);
                currentPoint = trajectory.closestIntersectionToStartOfLine(collidableCurrent.getCollisionRectangle());
                currentDistance = trajectory.start().distance(currentPoint);
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    closestPoint = currentPoint;
                    collidableClosest = collidableCurrent;
                }
            }
        }
        CollisionInfo closest = new CollisionInfo(closestPoint, collidableClosest);
        return closest;
    }
}