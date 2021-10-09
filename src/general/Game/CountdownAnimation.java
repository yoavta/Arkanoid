package general.Game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;


/**
 * CountdownAnimation-  The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private int sleeperTime;
    private double timePass;
    private final int textSizeWidth = 23;
    private final int textSizeHeight = 10;

    /**
     * CountdownAnimation - run the countdown animation in a special settings.
     *
     * @param numOfSeconds double
     * @param countFrom    int
     * @param gameScreen   SpriteCollection
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        stop = false;
        this.sleeper = new Sleeper();
        this.sleeperTime = (int) (numOfSeconds * 900);
        this.timePass = 0;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        double timePassCut = (double) Math.round(timePass * 10) / 10;
        if (countFrom - timePassCut < 0.2) {
            stop = true;
            shouldStop();
        }
        gameScreen.drawAllOn(d);
        String time = "" + (double) Math.round((countFrom - timePassCut) * 10) / 10;
        if ((countFrom - timePassCut) % 1 == 0) {
            d.setColor(Color.red);
            d.fillCircle(d.getWidth() / 2, d.getHeight() / 2, 45);
            d.setColor(Color.red.darker());
            d.drawCircle(d.getWidth() / 2, d.getHeight() / 2, 45);
        } else {
            d.setColor(new Color(255, 134, 134));
            d.fillCircle(d.getWidth() / 2, d.getHeight() / 2, 40);
            d.setColor(new Color(255, 134, 134).darker());

            d.drawCircle(d.getWidth() / 2, d.getHeight() / 2, 40);

        }
        d.setColor(Color.white);

        d.drawText(d.getWidth() / 2 - textSizeWidth, d.getHeight() / 2 + textSizeHeight, time, 32);
        sleeper.sleepFor(sleeperTime);
        timePass = ((timePassCut + numOfSeconds));
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}


