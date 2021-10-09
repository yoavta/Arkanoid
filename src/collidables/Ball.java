package collidables;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;
import general.Game.GameEnvironment;
import general.Game.Velocity;
import general.Game.GameLevel;

import java.util.Random;
import java.awt.Color;

/**
 * collidables.Ball class.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class Ball implements Sprite {
    //fields
    private int r; // radius
    private Point center;
    private Velocity v;
    private Point leftUp;
    private Point rightDown;
    private java.awt.Color color;
    private GameEnvironment environment;
    private final double epsilon = Math.pow(10, -10); // define epsilon const

    public static final int PIXEL = 1;

// constructors

    /**
     * constructor case point given by geometry.Point.
     *
     * @param center geometry.Point
     * @param r      int
     * @param color  Color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor case point given by geometry.Point.
     *
     * @param center geometry.Point
     * @param r      int
     */
    public Ball(Point center, int r) {
        this.center = center;
        this.r = r;
        this.color = Color.blue;
    }

    /**
     * constructor case point given by x and y int values.
     *
     * @param x     int
     * @param y     int
     * @param r     int
     * @param color Color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * constructor case point given by x and y int values.
     *
     * @param x           int
     * @param y           int
     * @param r           int
     * @param color       Color
     * @param environment generalGame.GameEnvironment
     */
    public Ball(int x, int y, int r, java.awt.Color color,
                GameEnvironment environment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * constructor in case of random ball.
     *
     * @param r int
     */
    public Ball(int r) {
        this.r = r;
    }

    /**
     * randomizeBall - create randomize ball.
     */
    public void randomizeBall() {
        Random rand = new Random();
        int width = (int) rightDown.getX();
        int height = (int) rightDown.getY();
        int x = rand.nextInt(width - r); // generate random x value.
        int y = rand.nextInt(height - r); // generate random y value.
        this.center = new Point(x, y);
        // generate random new
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        double angle = (double) rand.nextInt(360); // generate angle.
        this.color = new Color(red, green, blue);
    }

    /**
     * constructor case point given by x and y Double values.
     *
     * @param x     Double
     * @param y     Double
     * @param r     int
     * @param color Color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    // accessors

    /**
     * getX - accessor to x value.
     *
     * @return x value of ball center int
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * getY - accessor to y value.
     *
     * @return y value of ball center int
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * getSize - accessor to r value.
     *
     * @return r value of ball int
     */
    public int getSize() {
        return r;
    }

    /**
     * getColor - accessor to color value.
     *
     * @return color value of ball Color.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface DrewSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
        surface.setColor(Color.black);
        surface.drawCircle((int) center.getX(), (int) center.getY(), r);

    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * set the screen size to defined the ball limits.
     *
     * @param height int
     * @param width  int
     */
    public void setScreenSize(int height, int width) {
        this.leftUp = new Point(0, 0);
        this.rightDown = new Point((double) width, (double) height);
    }

    /**
     * set the velocity of the ball by generalGame.Velocity.
     *
     * @param velocity generalGame.Velocity
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * set the velocity of the ball by dx and dy Double.
     *
     * @param dx Double
     * @param dy Double
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getColor - accessor to velocity value.
     *
     * @return v generalGame.Velocity.
     */
    public Velocity getVelocity() {
        // in case of calling the method without setting velocity
        if (v == null) {
            this.v = new Velocity(10, 12);
        }
        return v;
    }

    /**
     * set the limit of the ball.
     *
     * @param leftUpP    geometry.Point
     * @param rightDownP geometry.Point
     */
    public void setLimit(Point leftUpP, Point rightDownP) {
        this.leftUp = leftUpP;
        this.rightDown = rightDownP;
    }

    /**
     * set the radius of the ball.
     *
     * @param radius int
     */
    public void setRadius(int radius) {
        this.r = radius;
    }


    /**
     * move the ball position one step.
     */
    public void moveOneStep() {
        Point start = new Point(center.getX(), center.getY());
        Point end = this.getVelocity().applyToPoint(this.center);
        Line moveLine = new Line(start, end);
        CollisionInfo collision = environment.getClosestCollision(moveLine);
        //check if collision occurred
        if (collision == null) {
            this.center = end;
        } else {
            double endX = (start.getX() + collision.collisionPoint().getX()) / 2;
            double endY = (start.getY() + collision.collisionPoint().getY()) / 2;
            // move the ball close ti the collision point
            this.center = new Point(endX, endY);
            // change the velocity according to the collision point
            this.v = collision.collisionObject().hit(this, collision.collisionPoint(), v);
            while (collision.collisionObject().getCollisionRectangle().isItInRectangle(this.center)) {
                this.center = this.getVelocity().applyToPoint(this.center);
            }
        }
    }

    /**
     * make sure that the ball is inside the limits and not exceeds.
     * if the ball exceeds-> it change the position inside the limits.
     */

    public void ballOnFrame() {
        double x = center.getX();
        double y = center.getY();

        if (rightDown == null | leftUp == null) {
            return;
        }

        // check if the ball is out of the limits.
        if ((x + r >= rightDown.getX())) {
            this.center = new Point(rightDown.getX() - r, y);
        } else if ((x - r <= leftUp.getX())) {
            this.center = new Point(leftUp.getX() + r, y);
        }

        if ((y + r >= rightDown.getY())) {
            this.center = new Point(center.getX(), rightDown.getY() - r);
        } else if ((y - r <= leftUp.getY())) {
            this.center = new Point(center.getX(), leftUp.getY() + r);
        }
    }

    /**
     * createBall- creating ball.
     *
     * @param width  int
     * @param height int
     * @param r      int
     * @return collidables.Ball
     */
    public static Ball createBall(int width, int height, int r) {
        Ball ball = new Ball(r);
        ball.setScreenSize(height, width);
        ball.randomizeBall();
        ball.ballOnFrame();
        return ball;
    }

    /**
     * addToGame - adding the ball to the game environment.
     *
     * @param gameLevel generalGame.Game
     */
    public void addToGame(GameLevel gameLevel) {
        this.environment = gameLevel.getEnvironment();
        gameLevel.getSprites().addSprite(this);
    }

    /**
     * getCenter - accessor to center.
     *
     * @return center geometry.Point
     */
    public Point getCenter() {
        return center;
    }
}

