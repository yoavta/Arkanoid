package general.Game;

import collidables.Block;
import sprites.Sprite;

import java.util.List;

/**
 * LevelInformation - interface for every Level class that holds the settings for one level.
 *
 * @author yoav tamir
 * @version 1.0
 */
public interface LevelInformation {

    /**
     * numberOfBalls - accessor for numberOfBalls.
     *
     * @return numberOfBalls int
     */
    int numberOfBalls();

    /**
     * createVelocity - create list of velocities for each of the balls.
     *
     * @return List<Velocity>
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed - accessor for paddleSpeed.
     *
     * @return paddleSpeed int
     */
    int paddleSpeed();

    /**
     * paddleWidth - accessor for paddleWidth.
     *
     * @return paddleWidth int
     */
    int paddleWidth();

    /**
     * levelName - accessor for levelName.
     *
     * @return levelName String
     */
    String levelName();


    /**
     * getBackground - accessor for background.
     *
     * @return background Sprite - sprite with the background of the level
     */
    Sprite getBackground();


    /**
     * blocks- initialize the blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return List<Block> blocks.
     */
    List<Block> blocks();


    /**
     * numberOfBlocksToRemove - accessor for numberOfBlocksToRemove.
     *
     * @return numberOfBlocksToRemove int
     */
    int numberOfBlocksToRemove();


}