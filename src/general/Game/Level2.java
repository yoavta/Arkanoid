package general.Game;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level2 class- implement LevelInformation and responsible on the settings of level 2.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class Level2 implements LevelInformation {

    private final int numberOfBlocksOnStart;
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite getBackground;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private int guiHeight;
    private int guiWidth;

    /**
     * constructor for Level2.
     */
    public Level2() {
        this.numberOfBlocksOnStart = 15;

        this.numberOfBlocksToRemove = 15;
        this.numberOfBalls = 10;
        this.initialBallVelocities = initialBallVelocities();
        this.paddleSpeed = 8;
        this.paddleWidth = 600;
        this.levelName = "Warm up";
        this.getBackground = new BackgroundLevel2();
        this.blocks = blocks();
        this.guiWidth = 800;
        this.guiHeight = 600;

    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> tempVelocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            tempVelocities.add(Velocity.fromAngleAndSpeed(290 + i * 16, 5));
        }
        return tempVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }


    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return getBackground;
    }

    @Override
    public List<Block> blocks() {
        List<Block> tempBlocks = new ArrayList<>();
        int brickWidth = 52;
        int brickHeight = 20;
        int leftFix = 10;
        int changingFactor = 20;

        int startRow = 12;

        for (int i = 0; i <= 14; i++) {

            Point upperLeft = new Point(((i) * brickWidth + leftFix),
                    ((+startRow) * brickHeight));
            Block block = new Block(new Rectangle(upperLeft, brickWidth,
                    brickHeight), new Color((30 + i * 10) % 255, 118, 49));
            tempBlocks.add(block);

        }
        return tempBlocks;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
