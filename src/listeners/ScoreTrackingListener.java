package listeners;

import collidables.Ball;
import collidables.Block;
import general.Game.Counter;

/**
 * ScoreTrackingListener - implements HitListener. if block as been hit->
 * decreasing the counter of score.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener - constructor.
     *
     * @param scoreCounter Counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
