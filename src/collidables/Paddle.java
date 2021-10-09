package collidables;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import general.Game.Velocity;
import general.Game.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * collidables.Paddle class- define the paddle of the game.
 */
public class Paddle implements Sprite, Collidable {

    private Velocity v;
    private Rectangle rectangle;
    private double moveSpeed;
    private List<Line> borders;
    private java.awt.Color color;
    private GameLevel gameLevel;

    // constructors

    /**
     * collidables.Paddle constructor via rectangle and color.
     *
     * @param rectangle geometry.Rectangle
     * @param color     java.awt.Color
     * @param moveSpeed double
     */
    public Paddle(Rectangle rectangle, java.awt.Color color, double moveSpeed) {
        this.moveSpeed = moveSpeed;
        v = new Velocity(moveSpeed, 0);
        this.rectangle = rectangle;
        this.borders = Rectangle.createBorders(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
        this.color = color;
    }

    /**
     * moveLeft - activate the move left after press "left".
     */
    public void moveLeft() {
        Velocity tempV = new Velocity(-moveSpeed, v.getDy());
        double upLeftY = rectangle.getUpperLeft().getY();
        double upLeftX = rectangle.getUpperLeft().getX();
        double height = rectangle.getHeight();
        Line upperLine = new Line(rectangle.getUpperLeft(),
                tempV.applyToPoint(rectangle.getUpperLeft()));
        Line lowerLine = new Line(new Point(upLeftX, upLeftY + height),
                new Point((upLeftX - moveSpeed), upLeftY + height));
        CollisionInfo upperCollision = gameLevel.getEnvironment().getClosestCollision(upperLine, this);
        CollisionInfo lowerCollision = gameLevel.getEnvironment().getClosestCollision(lowerLine, this);
        double upperLeftX = rectangle.getUpperLeft().getX();
        double upperLeftY = rectangle.getUpperLeft().getY();
        // check if the next move will run over other sprite
        if (upperCollision != null || lowerCollision != null) {
            double newX = (upperLeftX + upperCollision.collisionPoint().getX()) / 2;
            Point newEndPoint = new Point(newX, upperLeftY);
            rectangle = new Rectangle(newEndPoint, rectangle.getWidth(), rectangle.getHeight());
            this.borders = Rectangle.createBorders(rectangle.getUpperLeft(),
                    rectangle.getWidth(), rectangle.getHeight());
            return;
        }
        // create new velocity
        v = new Velocity(-moveSpeed, v.getDy());
        Rectangle temp = new Rectangle(v.applyToPoint(rectangle.getUpperLeft()),
                rectangle.getWidth(), rectangle.getHeight());
        // check if the next move will run over ball
        if (isThisPaddleRunOverBall(temp)) {
            return;
        }
        // if the move is clean -> move the paddle
        this.rectangle = temp;
        this.borders = Rectangle.createBorders(rectangle.getUpperLeft(),
                rectangle.getWidth(), rectangle.getHeight());

    }

    /**
     * moveRight - activate the move right after press "right".
     */
    public void moveRight() {
        v = new Velocity(moveSpeed, v.getDy());
        double upperRightX = rectangle.getUpperLeft().getX() + rectangle.getWidth();
        double upperRightY = rectangle.getUpperLeft().getY();
        Point upperRightStart = new Point(upperRightX, upperRightY);
        Point upperRightEnd = new Point((upperRightX + moveSpeed), upperRightY);
        Point lowerRightStart = new Point(upperRightX, upperRightY - rectangle.getHeight());
        Point lowerRightEnd = new Point((upperRightX + moveSpeed), upperRightY);
        Line upperLine = new Line(upperRightStart, upperRightEnd);
        Line lowerLine = new Line(lowerRightStart, lowerRightEnd);
        CollisionInfo upperCollision = gameLevel.getEnvironment().getClosestCollision(upperLine, this);
        CollisionInfo lowerCollision = gameLevel.getEnvironment().getClosestCollision(lowerLine, this);
        // check if the next move will run over other sprite
        if (upperCollision != null || lowerCollision != null) {
            double newX = (upperRightX + upperCollision.collisionPoint().getX()) / 2;
            Point newEndPoint = new Point((newX - rectangle.getWidth()), upperRightY);
            rectangle = new Rectangle(newEndPoint, rectangle.getWidth(), rectangle.getHeight());
            this.borders = Rectangle.createBorders(rectangle.getUpperLeft(),
                    rectangle.getWidth(), rectangle.getHeight());
            return;
        }
        Rectangle temp = new Rectangle(v.applyToPoint(rectangle.getUpperLeft()),
                rectangle.getWidth(), rectangle.getHeight());
        // check if the next move will run over ball
        if (isThisPaddleRunOverBall(temp)) {
            return;
        }
        // if the move is clean -> move the paddle
        this.rectangle = temp;
        this.borders = Rectangle.createBorders(rectangle.getUpperLeft(),
                rectangle.getWidth(), rectangle.getHeight());
    }

    // sprites.Sprite

    /**
     * timePassed - this activate the things that happen every unit of time pass.
     */
    public void timePassed() {

//        // check which key pressed
//        if (game.getGui().getKeyboardSensor().isPressed(KeyboardSensor.LEFT_KEY)) {
//            moveLeft();
//        }
//        if (game.getGui().getKeyboardSensor().isPressed(KeyboardSensor.RIGHT_KEY)) {
//            moveRight();
//        }

//         check which key pressed
        if (gameLevel.getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        if (gameLevel.getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }

    }

    /**
     * drawOn - draw the paddle on the DrawSurface.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) (rectangle.getWidth());
        int height = (int) (rectangle.getHeight());
        // draw the paddle with style
        d.fillRectangle(x, y, width, height);
        d.setColor(color.darker().darker());
        d.drawRectangle(x, y, width, height);
        d.setColor(color.brighter().brighter());
        d.drawRectangle(x + 1, y + 1, width - 2, height - 2);
    }


    /**
     * getCollisionRectangle - accessor the the rectangle.
     *
     * @return rectangle geometry.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * this method calculate the new velocity of the ball that hit the paddle.
     *
     * @param collisionPoint  geometry.Point
     * @param currentVelocity generalGame.Velocity
     * @param hitter Ball
     * @return velocity generalGame.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line upperBorder = borders.get(0);
        Line rightBorder = borders.get(1);
        Line lowerBorder = borders.get(2);
        Line leftBorder = borders.get(3);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // variables for checking collision with nodes
        Point upperLeftP = upperBorder.start();
        Point upperRightP = upperBorder.end();
        Point lowerLeftP = lowerBorder.start();
        Point lowerRightP = lowerBorder.end();

        // check if the collision is with the nodes
        if (collisionPoint.equals(upperRightP) || collisionPoint.equals(upperLeftP)
                || collisionPoint.equals(lowerLeftP) || collisionPoint.equals(lowerRightP)) {
            return new Velocity(-dx, -dy);
        }
        // check if the collision is with the upper border
        if (upperBorder.isIntersecting(new Line(collisionPoint, collisionPoint))) {
            double spaceSize = upperBorder.length() / 5;
            double y = upperBorder.start().getY();
            // create list of regions in the upper border
            ArrayList<Line> spaces = new ArrayList<Line>();
            for (int i = 0; i < 5; i++) {
                Point upperLeft = new Point(upperBorder.start().getX()
                        + (i * spaceSize), y);
                Point lowerRight = new Point(upperBorder.start().getX()
                        + ((i + 1) * spaceSize), y);
                spaces.add(i, new Line(upperLeft, lowerRight));
            }
            double speed = currentVelocity.getSpeed(); // calculate current speed
            // check in which border from the region above was hit

            for (int i = 0; i < 5; i++) {
                if (spaces.get(i).isIntersecting(new Line(collisionPoint, collisionPoint))) {
                    double angle = (i * 30) - 60;
                    Velocity newV = Velocity.fromAngleAndSpeed(angle, speed);
                    return newV;
                }
            }
            return new Velocity(dx, -dy);
        }
        // check if the lower border was hit
        if (lowerBorder.isIntersecting(new Line(collisionPoint, collisionPoint))) {
            return new Velocity(dx, -dy);
        }
        // check if the right or left border was hit
        if (rightBorder.isIntersecting(new Line(collisionPoint, collisionPoint))
                || leftBorder.isIntersecting(new Line(collisionPoint, collisionPoint))) {

            return new Velocity(-dx, dy);
        }
        return null;
    }

    // Add this paddle to the game.

    /**
     * addToGame - add the paddle to the game environment.
     *
     * @param g generalGame.Game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
        this.gameLevel = g;
    }

    /**
     * setRectangle - set new rectangle.
     *
     * @param rec rectangle
     */
    public void setRectangle(Rectangle rec) {
        this.rectangle = rec;
    }

    /**
     * isThisPaddleRunOverBall - check if the paddle will cross over the ball->
     * in his next move.
     *
     * @param rec geometry.Rectangle
     * @return boolean value
     */
    public boolean isThisPaddleRunOverBall(Rectangle rec) {

        for (Ball b : gameLevel.getBalls()) {
            // check if the ball is in the rectangle
            if (rec.isItInRectangle(b.getCenter())) {
                return true;
            }
        }
        return false;
    }


}