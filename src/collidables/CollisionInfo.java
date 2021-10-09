package collidables;

import geometry.Point;

/**
 * collidables.CollisionInfo - holds the information about collision:
 * the collision point and the collision collide.
 */
public class CollisionInfo {

    //fields
    private Point collisionP;
    private Collidable collidable;
    //constructor

    /**
     * collidables.CollisionInfo - constructor to collidables.CollisionInfo.
     *
     * @param collisionP geometry.Point
     * @param collidable collidables.Collidable
     */
    public CollisionInfo(Point collisionP, Collidable collidable) {
        this.collisionP = collisionP;
        this.collidable = collidable;
    }

    /**
     * collisionPoint-the point at which the collision occurs.
     *
     * @return collisionP geometry.Point
     */
    public Point collisionPoint() {
        return collisionP;
    }


    /**
     * collisionObject - the collidable object involved in the collision.
     *
     * @return collidable collisionObject
     */
    public Collidable collisionObject() {
        return collidable;
    }

    /**
     * equals - comparator between to collidables.CollisionInfo.
     *
     * @param other collidables.CollisionInfo
     * @return boolean value
     */
    public boolean equals(CollisionInfo other) {
        if (collidable.equals(other.collidable) && collidable.equals(other.collidable)) {
            return true;
        }
        return false;
    }
}