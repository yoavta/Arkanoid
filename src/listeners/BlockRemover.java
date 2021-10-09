package listeners;

import collidables.Ball;
import collidables.Block;
import general.Game.Counter;
import general.Game.GameLevel;

/**
 * BlockRemover -  a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * BlockRemover constructor.
     *
     * @param gameLevel          Game
     * @param removedBlocks Counter
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * hitEvent -      Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit Block
     * @param hitter   Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeCollidable(beingHit);
        gameLevel.removeSprite(beingHit);
        remainingBlocks.decrease(1);
    }
}