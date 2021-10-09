package collidables;

import biuoop.DrawSurface;
import listeners.HitNotifier;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import general.Game.Velocity;
import general.Game.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import listeners.HitListener;

/**
 * collidables.Block - class define block game.
 * this class implements both collidables.Collidable and sprites.Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields
    private Rectangle rectangle;
    private Point upperLeft;
    private double width;
    private double height;
    private List<Line> borders;
    private java.awt.Color color;
    private boolean visibility = true;
    private List<HitListener> hitListeners;


    //constructors

    /**
     * collidables.Block constructor- construct a block by a given geometry.Rectangle.
     *
     * @param rectangle geometry.Rectangle
     */
    public Block(Rectangle rectangle) {
        this.height = rectangle.getHeight();
        this.width = rectangle.getWidth();
        this.upperLeft = rectangle.getUpperLeft();
        this.borders = Rectangle.createBorders(upperLeft, width, height);
        this.rectangle = rectangle;
        this.color = Color.black;
        this.hitListeners = new ArrayList<>();


    }

    /**
     * collidables.Block constructor- construct a block by a given geometry.Rectangle.
     *
     * @param rectangle geometry.Rectangle
     * @param color     Color
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.height = rectangle.getHeight();
        this.width = rectangle.getWidth();
        this.upperLeft = rectangle.getUpperLeft();
        this.borders = Rectangle.createBorders(upperLeft, width, height);
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * collidables.Block constructor- construct a block by a given geometry.Rectangle.
     *
     * @param hitListeners List<HitListener>
     * @param rectangle    geometry.Rectangle
     * @param color        Color
     */
    public Block(List<HitListener> hitListeners, Rectangle rectangle, java.awt.Color color) {
        this.height = rectangle.getHeight();
        this.width = rectangle.getWidth();
        this.upperLeft = rectangle.getUpperLeft();
        this.borders = Rectangle.createBorders(upperLeft, width, height);
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = hitListeners;
    }


    /**
     * getCollisionRectangle - accessor to geometry.Rectangle dimension.
     *
     * @return geometry.Rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(upperLeft, width, height);
    }

    /**
     * hit-  Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  geometry.Point
     * @param currentVelocity generalGame.Velocity
     * @param hitter          Ball
     * @return new generalGame.Velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // setting the borders of the rectangle
        Line upperBorder = borders.get(0);
        Line lowerBorder = borders.get(2);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point upperRight = upperBorder.end();
        Point lowerLeft = lowerBorder.start();
        Point lowerRight = lowerBorder.end();
        Line rightBorder = borders.get(1);
        Line leftBorder = borders.get(3);

        this.notifyHit(hitter);

        if (collisionPoint.equals(upperRight) || collisionPoint.equals(upperLeft)
                || collisionPoint.equals(lowerLeft) || collisionPoint.equals(lowerRight)) {
            return new Velocity(-dx, -dy);
        }
        // check if the collision occurred on the upper or lower border
        if (upperBorder.isIntersecting(new Line(collisionPoint, collisionPoint))
                || lowerBorder.isIntersecting(new Line(collisionPoint, collisionPoint))) {
            return new Velocity(dx, -dy);
        } else if (rightBorder.isIntersecting(new Line(collisionPoint, collisionPoint))
                || leftBorder.isIntersecting(new Line(collisionPoint, collisionPoint))) {
            // if the collision occurred on the right or left border
            return new Velocity(-dx, dy);
        }
        return currentVelocity;
    }

    /**
     * drawOn- draw the block on the surface.
     *
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        if (!visibility) {
            return;
        }
        // fill the inside of the of the rectangle
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) (width), (int) (height));
        // draw the outer stroke line of the of the rectangle
        surface.setColor(color.darker());
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) (width), (int) (height));
        // draw the inner stroke line of the of the rectangle
        surface.setColor(color.brighter().brighter());
        surface.drawRectangle((int) upperLeft.getX() + 1,
                (int) upperLeft.getY() + 1, (int) (width) - 2,
                (int) (height) - 2);
    }

    @Override
    public void timePassed() {
    }

    /**
     * addToGame - adding the block to the game.
     *
     * @param gameLevel generalGame.Game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }


    /**********************************/
    /**
     * removeFromGame - removing one block from the game after the ball hit it.
     *
     * @param gameLevel Game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * notifyHit - notify when hit happened.
     *
     * @param hitter Ball
     */
    private void notifyHit(Ball hitter) {
        if (hitListeners != null) {
            // Make a copy of the hitListeners before iterating over them.
            List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }

    /**
     * setVisibility- set the visibility of the block.
     *
     * @param isVisibility boolean
     */
    public void setVisibility(boolean isVisibility) {
        this.visibility = isVisibility;
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * addHitListener- adding hit listeners via list.
     *
     * @param hitListenersList List<HitListener>
     */
    public void addHitListener(List<HitListener> hitListenersList) {
        this.hitListeners = hitListenersList;
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}