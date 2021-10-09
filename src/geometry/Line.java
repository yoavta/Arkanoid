package geometry;

import java.util.List;

/**
 * geometry.Line class - manage and hold data of 1 geometry.Line and methods related.
 *
 * @author yoav tamir
 * @version 1.0
 **/
public class Line {
    // fields
    private final Point start;
    private final Point end;
    private final double epsilon = Math.pow(10, -5); // define epsilon const

    @Override
    public String toString() {
        return start().toString() + "  " + end().toString();
    }

    /**
     * constructor methods in case of given 2 Points.
     *
     * @param start geometry.Point
     * @param end   geometry.Point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor methods in case of given 2 points by x and y values.
     *
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return the line length Double.
     */
    public double length() {
        return start.distance(end);
    }


    /**
     * Returns the middle point of the line.
     *
     * @return middle of the line geometry.Point.
     */
    public Point middle() {
        //calculate the middle values
        double xMiddle = ((start.getX() + end.getX()) / 2);
        double yMiddle = ((start.getY() + end.getY()) / 2);
        return new Point(xMiddle, yMiddle);
    }

    /**
     * Returns the start point of the line.
     *
     * @return start geometry.Point.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end geometry.Point.
     */
    public Point end() {
        return end;
    }

    /**
     * calculate the line slope.
     *
     * @return the slope of the line Double.
     */
    public double lineSlope() {
        return ((start.getY() - end.getY()) / (start.getX() - end.getX()));
    }

    /**
     * calculate the y value in a given x from the line equation.
     *
     * @param x     value
     * @param other geometry.Line
     * @return y value of the the point
     */
    public double findY(double x, Line other) {
        double slope = lineSlope();

        // case when line is horizontal
        if (Math.abs(slope) == 0) {
            return start.getY();
        }

        // case when other line is vertical
        if (Double.isInfinite(other.lineSlope())) {
            return (lineSlope() * (x - start.getX()) + start.getY());
        }
        return (other.lineSlope() * (x - other.start.getX()) + other.start.getY());
    }

    /**
     * calculate the x value in a given y from the line equation.
     *
     * @param other geometry.Line
     * @param y     value
     * @return y value of the the point
     */
    public double findX(double y, Line other) {
        // TODO remove abs
        double slope = Math.abs(lineSlope());
        // case when the line is vertical
        if (Double.isInfinite(slope)) {
            return start.getX();
        }

        // case when other line is vertical
        if (Double.isInfinite(other.lineSlope())) {
            return other.start.getX();
        }

        if (Math.abs(other.lineSlope()) == 0) {
            return ((start.getX() + ((y - start().getY()) / lineSlope())));
        } else if (slope != 0) {
            return ((y - other.start().getY()) / slope + other.start.getX());
        } else {
            return ((y - other.start().getY()) / other.lineSlope()
                    + other.start.getX());
        }

    }

    // methods that calculate the range of the possible intersection.

    /**
     * leftBound- calculate the left bound of the range.
     *
     * @param line geometry.Line
     * @return left bound Double.
     */
    public double leftBound(Line line) {
        return Math.min(line.start.getX(), line.end.getX()); //calculate bound
    }

    /**
     * rightBound- calculate the right bound of the range.
     *
     * @param line geometry.Line
     * @return right bound Double.
     */
    public double rightBound(Line line) {
        return Math.max(line.start.getX(), line.end.getX()); //calculate bound
    }

    /**
     * lowerBound- calculate the lower bound of the range.
     *
     * @param line geometry.Line
     * @return lower bound Double.
     */
    public double lowerBound(Line line) {
        return Math.min(line.start.getY(), line.end.getY()); //calculate bound
    }

    /**
     * upperBound- calculate the upper bound of the range.
     *
     * @param line geometry.Line
     * @return upper bound Double.
     */
    public double upperBound(Line line) {
        return Math.max(line.start.getY(), line.end.getY()); //calculate bound
    }

    /**
     * check if the lines intersect.
     *
     * @param other geometry.Line
     * @return boolean value (true if the lines intersect, false otherwise)
     */
    public boolean isIntersecting(Line other) {
        // in case to line are point
        if (start.equals(end) && other.start.equals(other.end)
                && start.equals(other.start)) {
            return true;
        }

        //if this line is point
        if (start.equals(end)) {
            return other.isThisOnLine(start);
        }

        //if other line is point
        if (other.start.equals(other.end)) {
            return isThisOnLine(other.start);
        }

        //checking if the lines are equal
        if (equals(other)) {
            return false;
        }

        double lengthThis = length();
        double lengthOther = other.length();
        double distance = Math.max(Math.max((start.distance(other.start)),
                start.distance(other.end)),
                Math.max((end.distance(other.start)),
                        end.distance(other.end)));

        if (Math.abs(lineSlope()) == Math.abs(other.lineSlope())) {
            //checking if one lines is the continue of other
            if ((((isItContinue(other) != null)
                    && ((lengthOther + lengthThis) - distance) <= epsilon))) {
                return true;
            }
            //the case when the lines are parallel
            return false;

        } else if (isItContinue(other) != null) {
            return true;
        }


        double xInter = intersectionX(other);
        double yInter = findY(xInter, other); //find y value
        Point interP = new Point(xInter, yInter);


//        //checking if the x value is on the lines
//        if (!isItOnLine(other, interP)) {
//            return false;
//        }

        //checking that the intersection is in the bounds of the segments
        double leftBound = Math.max(leftBound(other), leftBound(this));
        double rightBound = Math.min(rightBound(other), rightBound(this));
        double upperBound = Math.min(upperBound(other), upperBound(this));
        double lowerBound = Math.max(lowerBound(other), lowerBound(this));
        if ((leftBound <= xInter) && (rightBound >= xInter)
                & (upperBound >= yInter) && (lowerBound <= yInter)) {
            return true;
        }
        return false;
    }

    /**
     * intersectionX - find intersection x without checking if its->
     * valid point on the lines.
     *
     * @param other geometry.Line
     * @return x value of the intersection point Double.
     */
    public double intersectionX(Line other) {
        // case that line is point
        if (start.equals(end)) {
            return start.getX();
        }

        // case that other line is point
        if (other.start.equals(other.end)) {
            return other.start.getX();
        }

        double m1 = lineSlope();
        double m2 = other.lineSlope();
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = other.start.getX();
        double y2 = other.start.getY();

        //line is vertical and other is horizontal
        if (Double.isInfinite(m1) & Math.abs(m2) == 0) {
            return x1;
        }

        //other is vertical and line is horizontal
        if (Double.isInfinite(m2) & Math.abs(m1) == 0) {
            return x2;
        }

        //this line is vertical
        if (Double.isInfinite(m1)) {
            return x1;
        }

        //other line is vertical
        if (Double.isInfinite(m2)) {
            return x2;
        }

        //this line is horizontal
        if (Math.abs(m1) == 0) {
            return findX(y1, other);
        }

        //other line is horizontal
        if (Math.abs(m2) == 0) {
            return findX(y2, other);
        }

        //otherwise- the normal condition
        return ((m1 * x1 - y1 - m2 * x2 + y2) / (m1 - m2));
    }


    /**
     * checking if the lines are intersect.
     *
     * @param other geometry.Line
     * @return intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {

        // in case to line are point
        if (start.equals(end) && other.start.equals(other.end)
                && start.equals(other.start)) {
            return start;
        }

        // checking if the lines are equal
        if (!isIntersecting(other)) {
            return null;
        }

        double lengthThis = length();
        double lengthOther = other.length();
        double distance = Math.max(Math.max((start.distance(other.start)),
                start.distance(other.end)), Math.max((end.distance(other.start)),
                end.distance(other.end)));

        // the same slope and one is the continue of other
        if ((isItContinue(other) != null)
                && (((lengthOther + lengthThis) - distance) <= epsilon)) {
            return isItContinue(other);
        }

        // not the same slope and one is the continue of other
        if (((Math.abs(lineSlope()) != Math.abs(other.lineSlope()))
                && ((isItContinue(other) != null)))) {
            return isItContinue(other);
        }

        double xInter = intersectionX(other); //find x value
        double yInter = findY(xInter, other); //find y value
        return new Point(xInter, yInter);
    }

    /**
     * equals- check if this line and another given line are equal.
     *
     * @param other geometry.Line
     * @return boolean value (true if the lines are equal, false otherwise)
     */
    public boolean equals(Line other) {
        Point start2 = other.start;
        Point end2 = other.end;

        // check if the lines are equal
        if ((start.equals(start2)) & (end.equals(end2))) {
            return true;
        }

        if ((start.equals(end2)) & (start2.equals(end))) {
            return true;
        }

        return false;
    }

    /**
     * isItContinue - check if two lines are connected, if one is the ->
     * continues of other.
     *
     * @param other geometry.Line
     * @return geometry.Point value( meeting point if the line are connected,
     * null otherwise)
     */
    public Point isItContinue(Line other) {
        Point start2 = other.start;
        Point end2 = other.end;

        // check if one line is connected to the other
        if ((start.equals(start2)) | (start.equals(end2))) {
            return start;
        }

        if (((end.equals(end2)) | (end.equals(start2)))) {
            return end;
        }
        return null;
    }


    /**
     * closestIntersectionToStartOfLine -  If this line does not->
     * intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rectangle geometry.Rectangle
     * @return shortestDistance geometry.Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rectangle) {
        List<Point> list = rectangle.intersectionPoints(this);
        return shortestDistance(list);
    }

    /**
     * shortestDistance - find the shortest distance from the start of the line.
     *
     * @param points List<geometry.Point>
     * @return closestP geometry.Point.
     */
    public Point shortestDistance(List<Point> points) {
        Point closestP = null;
        double closestDistance = length();
        if (points.isEmpty()) {
            return null;
        }
        if (points.size() == 1) {
            return points.get(0);
        }
        for (Point p : points) {
            double currentDistance = start.distance(p);
            if (currentDistance - closestDistance <= epsilon) {
                closestDistance = currentDistance;
                closestP = p;
            }
        }
        return closestP;
    }

    /**
     * isThisOnLine - check if a given point is on line.
     *
     * @param point geometry.Point
     * @return boolean value
     */
    public boolean isThisOnLine(Point point) {
        // case when the line is vertical
        if (Double.isInfinite(lineSlope())) {
            double minY = Math.min(start.getY(), end.getY());
            double maxY = Math.max(start.getY(), end.getY());

            if ((point.getX() - start.getX() < epsilon)
                    && (point.getY() <= maxY) && (point.getY() >= minY)) {
                return true;
            }
            return false;
            // case when the line is horizontal
        } else if (Math.abs(lineSlope()) == 0) {
            double minX = Math.min(start.getX(), end.getX());
            double maxX = Math.max(start.getX(), end.getX());

            if ((Math.abs(point.getY() - start.getY()) <= epsilon)
                    && (point.getX() <= maxX) && (point.getX() >= minX)) {
                return true;
            }
            return false;
        }
        // regular case
        if (Math.abs(point.getY()
                - lineSlope() * (point.getX() - start.getX()) + start.getY()) <= epsilon) {
            return true;
        }
        return false;
    }
}