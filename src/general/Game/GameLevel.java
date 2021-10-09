package general.Game;

import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biuoop.KeyboardSensor;
import collidables.Collidable;
import collidables.Ball;
import collidables.Block;
import collidables.Paddle;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import listeners.BlockRemover;
import listeners.BallRemover;
import sprites.Sprite;
import sprites.SpriteCollection;

/**
 * generalGame.Game class - this class initialize ang running the game.
 */
public class GameLevel implements Animation {
    private final int paddleSpeed;
    private final int paddleWidth;
    private final String levelName;
    private final Sprite background;
    private final List<Block> blocks;
    private final int numberOfBlocksToRemove;
    private List<Velocity> initialBallVelocities;

    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private int guiWidth;
    private int guiHeight;
    private List<Ball> balls;
    private int borderSize;
    private Counter blocksRemains;
    private Counter ballsRemains;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private Random random;
    private int numberOfBalls;
    private KeyboardSensor keyboard;

    /**
     * constructor for GameLevel.
     *
     * @param levelInformation LevelInformation
     * @param keyboardSensor   KeyboardSensor
     * @param animationRunner  AnimationRunner
     * @param score            Counter
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.guiHeight = 600;
        this.guiWidth = 800;
        this.random = new Random();
        this.numberOfBalls = levelInformation.numberOfBalls();
        this.paddleSpeed = levelInformation.paddleSpeed();
        this.paddleWidth = levelInformation.paddleWidth();
        this.levelName = levelInformation.levelName();
        this.background = levelInformation.getBackground();
        this.blocks = levelInformation.blocks();
        this.numberOfBlocksToRemove = levelInformation.numberOfBlocksToRemove();
        this.initialBallVelocities = levelInformation.initialBallVelocities();
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.score = score;
    }


    /**
     * getBalls - accessor to getBalls.
     *
     * @return getBalls List<collidables.Ball>
     */
    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * getBorderSize - accessor to borderSize.
     *
     * @return borderSize int
     */
    public int getBorderSize() {
        return borderSize;
    }

    /**
     * getGui - accessor to gui.
     *
     * @return gui Gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * addCollidable - adding collidable to the game environment.
     *
     * @param c collidables.Collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite - adding sprites the sprites.SpriteCollection.
     *
     * @param s sprites.Sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);

    }

    /**
     * generalGame.GameEnvironment - accessor to game environment.
     *
     * @return environment generalGame.GameEnvironment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * getSprites - accessor to sprites collection.
     *
     * @return sprites sprites.SpriteCollection
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * initialize- a new game: create the Blocks and collidables.Ball (and collidables.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        sprites.addSprite(background);
        Block topBar = new Block(new Rectangle(new Point(0, 0), 799, 30), new Color(49, 49, 49));
        sprites.addSprite(topBar);
        double paddleHeight = 23; // paddle height
//        double paddleWidth = 120;
        this.borderSize = 10; // border size


        // paddle
        Point paddleUpperPoint = new Point((int) guiWidth / 2 - paddleWidth / 2,
                guiHeight - paddleHeight - borderSize);
        Paddle paddle = new Paddle(new Rectangle(paddleUpperPoint,
                paddleWidth, paddleHeight), new Color(33, 114, 2), paddleSpeed);
        paddle.addToGame(this); // adding the paddle to the game


        //limit blocks
        Block rightBlock = new Block(new Rectangle(new Point(800 - borderSize, 30), borderSize, 1000));
        Block leftBlock = new Block(new Rectangle(new Point(-1, 30), borderSize, 1000));
        Block upperBlock = new Block(new Rectangle(new Point(0, 30), 1000, borderSize));
        Block lowerBlock = new Block(new Rectangle(new Point(0, 620 - borderSize), 1000, borderSize));
        lowerBlock.setVisibility(false);
        Block[] limits = {lowerBlock, upperBlock, leftBlock, rightBlock};
        for (Block b : limits) {
            b.addToGame(this);
        }


//        int numberOfBricks = 12;

        // listeners
//        PrintingHitListener printNotify = new PrintingHitListener();

        blocksRemains = new Counter(numberOfBlocksToRemove);
        BlockRemover blockRemover = new BlockRemover(this, blocksRemains);

        ballsRemains = new Counter(numberOfBalls);
        BallRemover ballRemover = new BallRemover(this, ballsRemains);
        lowerBlock.addHitListener(ballRemover);

        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);


        List<HitListener> listeners = new ArrayList<>();
        listeners.add(blockRemover);
        listeners.add(scoreTrackingListener);


        for (Block b : blocks) {
            b.addHitListener(listeners);
            b.addToGame(this);
        }

        // create blocks in a certain pattern
//        int numberOfBricksCreated = ass3Pattern(listeners, numberOfBricks, new Color(2, 124, 227));


    }

    /**
     * run -  Run the game -- start the animation loop.
     */
    public void run() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(0.2,
                3, sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.

        this.runner.run(this);

    }

    /**
     * createBallsOnTopOfPaddle- create new balls and place them on top of the paddle.
     */
    private void createBallsOnTopOfPaddle() {
        // balls
        balls = new ArrayList<Ball>();
        int ballSpeed = 3;
        // create balls
        for (int i = 0; i < numberOfBalls; i++) {
            Ball b = new Ball((int) guiWidth / 2, 550, 5, Color.white, environment);
            b.setVelocity(initialBallVelocities.get(i));
            balls.add(b);
        }
        // adding the balls to the game
        for (Ball b : balls) {
            b.addToGame(this);
        }
    }

    /**
     * regularPattern - drawing regular game pattern.
     *
     * @param numberOfBricks int
     */
    public void regularPattern(int numberOfBricks) {
        int brickWidth = (int) (guiWidth / numberOfBricks) / 2;
        int brickHeight = (int) (brickWidth * 0.5);
        for (int j = 1; j < 5; j += 2) {
            //odd rows
            for (int i = 3 + j; i <= numberOfBricks; i++) {
                Point upperLeft = (new Point((i * 2 * brickWidth),
                        (j * brickHeight)));
                Block block = new Block(new Rectangle(upperLeft, brickWidth,
                        brickHeight), Color.blue);
                block.addToGame(this);

            } // even rows
            for (int p = 3 + j; p <= numberOfBricks; p++) {
                Point upperLeft = new Point((p * 2 * brickWidth + brickWidth),
                        ((j + 1) * brickHeight));
                Block block = new Block(new Rectangle(upperLeft, brickWidth,
                        brickHeight), Color.green);
                block.addToGame(this);
            }
        }
    }

//    /**
//     * ass3Pattern - drawing bricks for according to the assigment required.
//     *
//     * @param numberOfBricks int
//     * @param color          java.awt.Color
//     * @param listeners      List<HitListener>
//     * @return counter int - number of blocks added
//     */
//    public int ass3Pattern(List<HitListener> listeners, int numberOfBricks, java.awt.Color color) {
//        int counter = 0;
//        int brickWidth = 50;
//        int brickHeight = 20;
//        int leftFix = 140;
//        int changingFactor = 20;
//        int r = color.getRed();
//        int g = color.getGreen();
//        int b = color.getBlue();
//        int startRow = 4;
//        for (int j = 0; j < 10; j++) {
//            int newR = Math.abs(Math.min((r - j * changingFactor), 255));
//            int newG = Math.abs(Math.min((g - j * changingFactor), 255));
//            int newB = Math.abs(Math.min((b - j * changingFactor), 255));
//            Color newColor = new Color(newR, newG, newB);
//            //creating the gradient
//            for (int i = 0; i <= numberOfBricks - j; i++) {
//                Point upperLeft = new Point(((i + j) * brickWidth + leftFix),
//                        ((j + startRow) * brickHeight));
//                Block block = new Block(listeners, new Rectangle(upperLeft, brickWidth,
//                        brickHeight), newColor);
//                block.addToGame(this);
//                counter++;
//            }
//        }
//        return counter;
//    }

//    /**
//     * gradientPaint - drawing gradient by a given color.
//     *
//     * @param d     DrawSurface
//     * @param color java.awt.Color
//     */
//    private void gradientPaint(DrawSurface d, java.awt.Color color) {
//        int numOfColors = 20;
//        int changingFactor = 4;
//        int colorHeight = (int) guiHeight / numOfColors;
//        int r = color.getRed();
//        int g = color.getGreen();
//        int b = color.getBlue();
//        for (int i = 0; i < numOfColors; i++) {
//            int newR = Math.abs(Math.min((r + i * changingFactor), 255));
//            int newG = Math.abs(Math.min((g + i * changingFactor / 2), 255));
//            int newB = Math.abs(Math.min((b + i * changingFactor), 255));
//            Color newColor = new Color(newR, newG, newB);
//            d.setColor(newColor);
//            int x = 0;
//            int y = i * colorHeight;
//            int width = guiWidth;
//            int height = guiHeight - i * colorHeight;
//            d.fillRectangle(x, y, width, height);
//            ;
//        }
//        // sun paint
//        d.setColor(new Color(255, 255, 212));
//        d.fillCircle(30, 30, 200);
//        d.setColor(Color.yellow);
//        for (int i = 0; i < 10; i++) {
//            int move = i * 50;
//            d.drawLine(30, 30, +(int) (move), (int) (300 - 0.3 * move));
//            d.setColor(new Color(255, 254, 169));
//            d.fillCircle(30, 30, 150);
//            d.setColor(new Color(255, 253, 99));
//            d.fillCircle(30, 30, 100);
//            d.setColor(new Color(255, 251, 0));
//            d.fillCircle(30, 30, 50);
//
//        }
//
//
//    }

    /**
     * remove a certain Collidable from the screen.
     *
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove a certain Sprite from the screen.
     *
     * @param s Collidable
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
//                gradientPaint(d, new Color(162, 237, 255));
        background.drawOn(d);
        if (keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        this.sprites.drawAllOn(d);

        if (getBallsRemains().getValue() == 0) {
            running = false;
        }
        if (getBlocksRemains().getValue() == 0) {
            running = false;
        }
        this.sprites.notifyAllTimePassed();
//        d.setColor(new Color(206, 206, 206));
//        d.fillRectangle(0, 0, 800, 30);
        String scoreString = "Score: " + score.getValue();
        String levelString = "Level Name: " + levelName;
        String indicatorString = "Blocks remain: " + blocksRemains.getValue();

        d.setColor(new Color(255, 255, 255));
        d.drawText(380, 20, scoreString, 20);
        d.drawText(650, 20, levelString, 12);
        d.drawText(30, 20, indicatorString, 12);

//        score.increase(100);

        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
    }

    @Override
    public boolean shouldStop() {
        return (!running);
    }

    /**
     * KeyboardSensor - keyboard accessor.
     *
     * @return keyboard  KeyboardSensor
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * getBallsRemains - ballsRemains accessor.
     *
     * @return ballsRemains  Counter
     */
    public Counter getBallsRemains() {
        return ballsRemains;
    }

    /**
     * getBlocksRemains - blocksRemains accessor.
     *
     * @return blocksRemains  Counter
     */
    public Counter getBlocksRemains() {
        return blocksRemains;
    }

}
