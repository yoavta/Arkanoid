package general.Game;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * EndScreen - is the animation that appear on the end of the game,
 * if you win or if you lose.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class EndScreen implements Animation {
    private int score;
    private boolean stop;
    private boolean isWin;
    private double size = 32;
    private int sizeDirection = 1;
    private int confettiLocation1 = 0;
    private int confettiLocation2 = 0;
    private int confettiLocation3 = -50;

    /**
     * EndScreen constructor.
     *
     * @param isWin boolean
     * @param score int
     */
    public EndScreen(boolean isWin, int score) {
        this.stop = false;
        this.isWin = isWin;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (isWin) {


            BackgroundLevel1 backgroundLevel1 = new BackgroundLevel1(new Color(186, 255, 241));
            backgroundLevel1.drawOn(d);
            d.setColor(Color.blue);
            d.drawText(d.getWidth() / 2 - 200, d.getHeight() / 2, "You Win!!!", (int) size);


            Random rand = new Random();
            rand.setSeed(1);
            for (int i = 0; i < 50; i++) {
                d.setColor(new Color(rand.nextInt(254), rand.nextInt(254), rand.nextInt(254)));
                int leftX = rand.nextInt(20) + i * 20;
                int leftY = rand.nextInt(150) + confettiLocation1;
                int confettiSize = rand.nextInt(5) + 7;
                d.fillRectangle(leftX, leftY, confettiSize, confettiSize);
            }
            confettiLocation1 += 5;
            for (int i = 0; i < 30; i++) {
                d.setColor(new Color(rand.nextInt(253), rand.nextInt(253), rand.nextInt(253)));
                int leftX = rand.nextInt(20) + i * 30;
                int leftY = rand.nextInt(150) + confettiLocation2;
                int confettiSize = rand.nextInt(5) + 2;
                d.fillCircle(leftX, leftY, confettiSize);
            }
            confettiLocation2 += 7;
            for (int i = 0; i < 50; i++) {
                d.setColor(new Color(rand.nextInt(252), rand.nextInt(253), rand.nextInt(253)));
                int leftX = rand.nextInt(20) + i * 20;
                int leftY = rand.nextInt(150) + confettiLocation3;
                int confettiSize = rand.nextInt(5);
                d.fillOval(leftX, leftY, confettiSize, confettiSize + 2);
            }
            confettiLocation3 += 10;
        } else {
            BackgroundLevel1 backgroundLevel1 = new BackgroundLevel1(new Color(255, 186, 186));
            backgroundLevel1.drawOn(d);
            d.setColor(Color.red);
            d.drawText(d.getWidth() / 2 - 200, d.getHeight() / 2, "Game Over.", (int) size);
        }
        d.setColor(new Color(198, 198, 198));
        d.drawText(484, 557, "your score is:" + score, 29);
        d.setColor(Color.black);
        d.drawText(480, 560, "your score is:" + score, 30);
        d.drawText(20, 560, "press SPACE to exit", 20);
        if (size > 100) {
            sizeDirection = -1;
        }
        if (size < 30) {
            sizeDirection = 1;
        }
        size = size + sizeDirection * 1;


    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
