package general.Game;

import geometry.Point;

/**
 * generalGame.Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @version 1.0
 */
public class Velocity {

    public static final double MINIMUM_MOVE = 0.2;
    private final double epsilon = Math.pow(10, -10); // define epsilon const

    //Fields
    private double dx;
    private double dy;

    // constructor

    /**
     * constructor - generalGame.Velocity.
     *
     * @param dx double
     * @param dy double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    /**
     * applyToPoint - Take a point with position (x,y) and return a new point->
     * with position (x+dx, y+dy).
     *
     * @param p geometry.Point
     * @return geometry.Point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * fromAngleAndSpeed- calculate velocity from speed and angle.
     *
     * @param angle double
     * @param speed double
     * @return generalGame.Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -1 * speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * getSpeed - calculate the speed of the current velocity.
     *
     * @return speed double
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    //accessor

    /**
     * getDx - accesses method.
     *
     * @return dx double
     */
    public double getDx() {
        return dx;
    }

    /**
     * getDy - accesses method.
     *
     * @return dy double
     */
    public double getDy() {
        return dy;
    }

    /**
     * equals- compare between to velocities and check if they are equal.
     *
     * @param otherVelocity generalGame.Velocity
     * @return boolean value.
     */
    public boolean equals(Velocity otherVelocity) {
        // check if the velocities are equal
        if (((dx - otherVelocity.getDx()) <= epsilon) && ((dy - otherVelocity.getDy()) <= epsilon)) {
            return true;
        }
        return false;
    }
}