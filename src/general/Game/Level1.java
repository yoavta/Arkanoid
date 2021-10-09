package general.Game;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level1 class- implement LevelInformation and responsible on the settings of level 1.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class Level1 implements LevelInformation {

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
     * constructor for Level1.
     */
    public Level1() {
        this.guiWidth = 800;
        this.guiHeight = 600;
        this.numberOfBlocksOnStart = 1;
        this.numberOfBlocksToRemove = 1;
        this.numberOfBalls = 1;
        this.initialBallVelocities = initialBallVelocities();
        this.paddleSpeed = 8;
        this.paddleWidth = 80;
        this.levelName = "Easy start";
        this.getBackground = new BackgroundLevel1(new Color(0, 115, 226));
        this.blocks = blocks();

    }


    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> tempVelocities = new ArrayList<>();
        tempVelocities.add(Velocity.fromAngleAndSpeed(0, 3));

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
        int brickWidth = 50;
        int brickHeight = 20;
        tempBlocks.add(new Block(new Rectangle(new Point(guiWidth / 2 - brickWidth / 2,
                guiHeight / 6 + brickHeight), brickWidth, brickHeight), new Color(208, 118, 49)));

        return tempBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
