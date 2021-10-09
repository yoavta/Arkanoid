package geometry;

/**
 * geometry.Point class - manage and hold data of 1 point and methods related.
 *
 * @author yoav tamir
 * @version 1.0
 **/
public class Point {
    // fields
    private double x;
    private double y;
    private final double epsilon = Math.pow(10, -10); // define epsilon const

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * constructor.
     *
     * @param x Double.
     * @param y Double.
     **/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other geometry.Point.
     * @return distance Double.
     **/
    public double distance(Point other) {
        double vertic1 = x - other.getX();
        double vertic2 = y - other.getY();
        return Math.sqrt((vertic1) * (vertic1) + (vertic2) * (vertic2));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other geometry.Point.
     * @return boolean value.
     **/
    public boolean equals(Point other) {
        if ((Math.abs(x - other.getX()) <= epsilon)
                & (Math.abs(y - other.getY()) <= epsilon)) {
            return true;
        }
        //if the points are not equal
        return false;
    }
    // Return the x and y values of this point

    /**
     * getX - Return the x value of this point.
     *
     * @return x value Double.
     **/
    public double getX() {
        return x;
    }

    /**
     * getY - Return the y value of this point.
     *
     * @return y value Double.
     **/
    public double getY() {
        return y;
    }

    /**
     * isCloseThanCurrent - compare between to points and return ->
     * the one closer to the third given point.
     *
     * @param target   geometry.Point
     * @param pCurrent geometry.Point
     * @param pNew     geometry.Point
     * @return boolean value
     */
    public boolean isCloseThanCurrent(Point target, Point pCurrent, Point pNew) {
        if (pCurrent.distance(target) < pNew.distance(target)) {
            return false;
        }
        return true;
    }


}
