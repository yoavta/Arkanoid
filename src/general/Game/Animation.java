
package general.Game;

import biuoop.DrawSurface;

/**
 * Animation interface- for each animation in the game.
 *
 * @author yoav tamir
 * @version 1.0
 */
public interface Animation {
    /**
     * doOneFrame - change the animation after one frame of the game passed.
     *
     * @param d DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop - return if the animation should stop or not.
     *
     * @return boolean value
     */
    boolean shouldStop();
}
