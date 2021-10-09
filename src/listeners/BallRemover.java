package listeners;

import collidables.Ball;
import collidables.Block;
import general.Game.Counter;
import general.Game.GameLevel;

/**
 * BallRemover - removing the ball when it not on the screen.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * BallRemover constructor.
     * @param gameLevel Game
     * @param removedBlocks Counter
     */
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeSprite(hitter);
        remainingBalls.decrease(1);
    }
}
