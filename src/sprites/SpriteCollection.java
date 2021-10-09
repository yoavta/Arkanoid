package sprites;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * sprites.SpriteCollection class - hold all the sprites in the game.
 */
public class SpriteCollection {
    //fields
    private List<Sprite> list = new ArrayList<Sprite>();

    /**
     * addSprite - add sprite to the collection.
     *
     * @param s sprites.Sprite
     */
    public void addSprite(Sprite s) {
        list.add(s);
    }

    /**
     * removeSprite - remove sprite to the collection.
     *
     * @param s sprites.Sprite
     */
    public void removeSprite(Sprite s) {
        list.remove(s);
    }

    /**
     * notifyAllTimePassed - call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tempSprites = new ArrayList<Sprite>(list);
        for (Sprite s : tempSprites) {
            s.timePassed();
        }
    }


    /**
     * call drawOn(d) on all sprites.
     *
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : list) {
            s.drawOn(d);
        }
    }

}