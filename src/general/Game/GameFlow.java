package general.Game;

import biuoop.KeyboardSensor;

import java.util.List;

/**
 * GameFlow - this class is responsible on the entire game flow.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class GameFlow {

    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter score;
    private boolean isWin;

    /**
     * GameFlow constructor.
     *
     * @param ar AnimationRunner
     * @param ks KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        score = new Counter(0);
        this.isWin = true;
    }

    /**
     * runLevels is responsible for running the levels one after another.
     *
     * @param levels List<LevelInformation>
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner, score);

            level.initialize();


            while (level.getBallsRemains().getValue() > 0 & level.getBlocksRemains().getValue() > 0) {
                level.run();
                score.increase(100);
            }

            if (level.getBallsRemains().getValue() == 0) {
                this.isWin = false;
                break;
            }
        }
        if (isWin) {
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new EndScreen(true, score.getValue())));
        } else {
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new EndScreen(false, score.getValue())));
        }
    }


}