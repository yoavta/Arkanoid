package listeners;

import collidables.Ball;
import collidables.Block;

/**
 * HitListener- interface for all listeners in the game.
 */
public interface HitListener {


    /**
     * hitEvent -      This method is called whenever the beingHit object is hit.
     *      The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit Block
     * @param hitter Ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
