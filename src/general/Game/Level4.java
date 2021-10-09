package general.Game;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level4 class- implement LevelInformation and responsible on the settings of level 4.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class Level4 implements LevelInformation {

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
     * constructor for Level4.
     */
    public Level4() {
        this.numberOfBlocksOnStart = 38;

        this.numberOfBlocksToRemove = 38;
        this.numberOfBalls = 40;
        this.initialBallVelocities = initialBallVelocities();
        this.paddleSpeed = 8;
        this.paddleWidth = 185;
        this.levelName = "No Way";
        this.getBackground = new BackgroundLevel4();
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
            tempVelocities.add(Velocity.fromAngleAndSpeed(200 + i * 17, 8));
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
        int brickWidth = 41;
        int brickHeight = 20;
        int leftFix = 10;
        int changingFactor = 30;
        Color color = new Color(0, 255, 34);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int startRow = 4;
        for (int j = 0; j < 2; j++) {
            int newR = Math.abs(Math.min((r - j * changingFactor), 255));
            int newG = Math.abs(Math.min((g - j * changingFactor), 255));
            int newB = Math.abs(Math.min((b - j * changingFactor), 255));
            Color newColor = new Color(newR, newG, newB);
            //creating the gradient
            for (int i = 0; i < 19; i++) {
                Point upperLeft = new Point(((i) * brickWidth + leftFix),
                        ((j + startRow) * brickHeight));
                Block block = new Block(new Rectangle(upperLeft, brickWidth,
                        brickHeight), newColor);
                tempBlocks.add(block);
            }
        }
        return tempBlocks;

    }


    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
