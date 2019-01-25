package genral;
import geometry.Point;

import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;
//import sprites.Block;
//import sprites.Paddle;
//import sprites.Sprite;
//import sprites.SpriteCollection;
/**
 *
 * @author nitai
 *
 */
public interface Collidable {
       // Return the "collision shape" of the object.
       /**
        *
        * @return size.
        */
       Rectangle getCollisionRectangle();
       // Notify the object that we collided with it at collisionPoint with
       // a given velocity.
       // The return is the new velocity expected after the hit (based on
       // the force the object inflicted on us).
       /**
        *
        *@param hitter ball.
        * @param collisionPoint p.
        * @param currentVelocity v.
        * @return velocity.
        */
       Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
