package collidables;
import geometry.Point;
import geometry.Rectangle;
import general.Game.Velocity;
/**
 * collidables.Collidable - this is the interface of all the collidable elements ->
 * in the game.
 */
public interface Collidable {
    /**
     * getCollisionRectangle - accessor to the "collision shape" of the object.
     *
     * @return geometry.Rectangle.
     */
    Rectangle getCollisionRectangle();


    /**
     * hit -  Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint geometry.Point
     * @param currentVelocity generalGame.Velocity
     * @param hitter Ball
     * @return generalGame.Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);


}