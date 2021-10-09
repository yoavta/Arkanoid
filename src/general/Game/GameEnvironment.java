package general.Game;

import collidables.Collidable;
import collidables.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * generalGame.GameEnvironment - is the collection of all the things that the ball ->
 * may collide with.
 * The ball will know the game environment, and will use it to check for->
 * collisions and direct its movement.
 */
public class GameEnvironment {
    //fields
    private List<Collidable> collidables = new ArrayList<Collidable>();


    /**
     * addCollidable- add the given collidable to the environment.
     *
     * @param c collidables.Collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * removeCollidable- remove the given collidable to the environment.
     *
     * @param c collidables.Collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }


    /**
     * getClosestCollision-
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory geometry.Line
     * @return closestInfo  collidables.CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double closestDistance = trajectory.length();
        CollisionInfo closestInfo = null;

        for (Collidable c : collidables) {
            List<Point> checkIntersection =
                    c.getCollisionRectangle().intersectionPoints(trajectory);
            if (checkIntersection.isEmpty()) {
                continue;
            }
            Point closestPoint = trajectory.shortestDistance(checkIntersection);
            if (closestInfo == null) {
                closestInfo = new CollisionInfo(closestPoint, c);
                //check if this point closer than the current
            } else if (trajectory.start().isCloseThanCurrent(trajectory.start(),
                    closestInfo.collisionPoint(), closestPoint)) {
                closestInfo = new CollisionInfo(closestPoint, c);
            }
        }
        return closestInfo;
    }

    /**
     * getClosestCollision - find the closest collision by a given path.
     *
     * @param trajectory geometry.Line
     * @param collidable collidables.Collidable
     * @return closestInfo collidables.CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory, Collidable collidable) {
        double closestDistance = trajectory.length();
        CollisionInfo closestInfo = null;

        for (Collidable c : collidables) {
            if (c.equals(collidable)) {
                continue;
            }
            List<Point> checkIntersection =
                    c.getCollisionRectangle().intersectionPoints(trajectory);
            if (checkIntersection.isEmpty()) {
                continue;
            }
            Point closestPoint = trajectory.shortestDistance(checkIntersection);
            if (closestInfo == null) {
                closestInfo = new CollisionInfo(closestPoint, c);
                //check if this point closer than the current
            } else if (trajectory.start().isCloseThanCurrent(trajectory.start(),
                    closestInfo.collisionPoint(), closestPoint)) {
                closestInfo = new CollisionInfo(closestPoint, c);
            }
        }
        return closestInfo;
    }
}