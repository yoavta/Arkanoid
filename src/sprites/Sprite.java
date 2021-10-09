package sprites;

import biuoop.DrawSurface;

/**
 * sprites.Sprite interface - interface of all the sprites in the game.
 */
public interface Sprite {

    /**
     * drawOn -  draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed - notify the sprite that time has passed.
     */
    void timePassed();

}