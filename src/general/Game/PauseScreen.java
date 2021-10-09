package general.Game;

import biuoop.DrawSurface;

import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * PauseScreen - implements Animation. animation appear when pausing the game.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class PauseScreen implements Animation {
    private int location;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor for PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
        this.location = 1400;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        BackgroundLevel1 background = new BackgroundLevel1(new Color(231, 255, 186));
        background.drawOn(d);
        d.setColor(new Color(36, 140, 238));
        d.drawText(150, d.getHeight() / 2 + 50, "|| paused", 130);
        d.setColor(new Color(1, 97, 184));
        d.drawText(152, d.getHeight() / 2 + 50, "|| paused", 127);
        d.setColor(new Color(0, 53, 101));
        d.drawText(400, 550, "press space to continue", 32);

        d.setColor(new Color(0, 0, 0));
        d.drawText(50, 50 + location, "z", 32);
        d.drawText(100, 100 + location, "z", 50);
        d.drawText(150, 200 + location, "z", 45);
        d.drawText(200, 70 + location, "z", 70);
        d.drawText(250, 80 + location, "z", 55);
        d.drawText(300, 0 + location, "z", 65);
        d.drawText(350, 400 + location, "z", 25);
        d.drawText(400, 10 + location, "z", 44);
        d.drawText(450, 80 + location, "z", 55);
        d.drawText(500, 200 + location, "z", 22);
        d.drawText(550, 300 + location, "z", 88);
        d.drawText(600, 10 + location, "z", 32);

        if (location < -500) {
            location = 1000;
        }
        location -= 10;


    }
@Override
    public boolean shouldStop() {
        return this.stop;
    }
}