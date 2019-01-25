package sprites;
import java.util.ArrayList;
import biuoop.DrawSurface;
/**
 *
 * @author nitai
 *
 */
public class SpriteCollection {
    private java.util.List<Sprite> collection = new ArrayList<Sprite>();
    /**
     *
     * @param s sprite.
     */
     public void addSprite(Sprite s) {
         collection.add(s);
     }
     /**
      *
      * @param s sprite.
      */
     public void removeSprite(Sprite s) {
         this.collection.remove(s);
     }
     /**
      * @param dt d.
      *call timePassed() on all sprites.
      */
     public void notifyAllTimePassed(double dt) {
         for (int i = 0; i < this.collection.size(); i++) {
             ((Sprite) this.collection.get(i)).timePassed(dt);
         }
     }
     /**
      * call drawOn(d) on all sprites.
      * @param d Surface.
      */
     public void drawAllOn(DrawSurface d) {
         for (int i = 0; i < this.collection.size(); i++) {
             ((Sprite) this.collection.get(i)).drawOn(d);
         }
     }
}
