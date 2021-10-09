package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * geometry.Rectangle class - define rectangle as dimension of other objects.
 */
public class Rectangle {
    //fields
    private Point upperLeft;
    private double width;
    private double height;
    private List<Line> borders;

    /**
     * getBorders - accessor to borders List<geometry.Line>.
     *
     * @return borders List<geometry.Line>
     */
    public List<Line> getBorders() {
        return borders;
    }

    /**
     * constructor - Create a new rectangle with location and width/height.
     *
     * @param upperLeft geometry.Point
     * @param width     double
     * @param height    double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.borders = createBorders(upperLeft, width, height);
    }


    //
    //

    /**
     * intersectionPoints - Return a (possibly empty) List of ->
     * intersection points with the specified line.
     *
     * @param line line
     * @return java.util.List<geometry.Point> - lists of points
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        List<Point> list = new ArrayList<Point>();
        Point check;
        // check for every border if the line given is intersecting with him
        for (Line border : borders) {
            if (line.isIntersecting(border)) {
                check = ((line.intersectionWith(border)));
                if (!list.contains(check)) {
                    // add the intersecting point to list
                    list.add(check);
                }
            }
        }

        return list;
    }


    /**
     * getWidth - accessor the width.
     *
     * @return width double
     */
    public double getWidth() {
        return width;
    }

    /**
     * getHeight - accessor the height.
     *
     * @return height double
     */
    public double getHeight() {
        return height;
    }

    // Returns the upper-left point of the rectangle.

    /**
     * getUpperLeft - accessor the Upper Left point.
     *
     * @return upperLeft geometry.Point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * createBorders - this function initialize array of borders and ->
     * inser them from the upper border , clockwise.
     *
     * @param upperLeft geometry.Point
     * @param width     double
     * @param height    double
     * @return List<geometry.Line> list
     */
    public static List<Line> createBorders(Point upperLeft, double width, double height) {
        List<Line> list = new ArrayList<Line>();
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        list.add(new Line(upperLeft, upperRight)); // upper border
        list.add(new Line(upperRight, lowerRight)); // right border
        list.add(new Line(lowerLeft, lowerRight)); // lower border
        list.add(new Line(upperLeft, lowerLeft)); // left border
        return list;
    }

    /**
     * isItInRectangle - check if a given point is inside the rectangle.
     *
     * @param point geometry.Point
     * @return boolean value
     */
    public boolean isItInRectangle(Point point) {
        double x = point.getX();
        double y = point.getY();
        double minX = upperLeft.getX();
        double maxX = upperLeft.getX() + getWidth();
        double minY = upperLeft.getY();
        double maxY = upperLeft.getY() + getHeight();
        // check if the point is inside the range
        if ((x >= minX) && (x <= maxX) && (y >= minY) && (y <= maxY)) {
            return true;
        }
        return false;
    }

}

