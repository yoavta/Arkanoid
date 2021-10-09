package general.Game;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * BackgroundLevel1 - special background for level 1.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class BackgroundLevel1 implements Sprite {

    private final Color color;

    /**
     * constructor for this background.
     *
     * @param color Color
     */
    public BackgroundLevel1(Color color) {
        this.color = color;
    }

    private double colorChange = 0;
    private boolean add = true;

    @Override
    public void drawOn(DrawSurface d) {
        int numOfColors = 30;
        int changingFactor = 4;
        int colorHeight = (int) 800 / numOfColors;
        int r = (int) (color.getRed() + colorChange) % 256;
        int g = (int) (color.getGreen()) % 256;
        int b = (int) (color.getBlue()) % 256;
        int j = 0;
        for (; j < numOfColors; j++) {
            int newR = Math.abs(Math.min((r + j * changingFactor), 255));
            int newB = Math.abs(Math.min((b + j * changingFactor), 255));
            int newG = Math.abs(Math.min((g + j * changingFactor / 2), 255));
            int x = 0;
            int y = j * colorHeight;
            Color newColor = new Color(newR, newG, newB);
            d.setColor(newColor);
            int width = 800;
            int height = 600 - j * colorHeight;
            d.fillRectangle(x, y, width, height);
        }
        if (colorChange > 250) {
            add = false;
        }
        if (colorChange < 5) {
            add = true;
        }
        if (add) {
            colorChange += 2;
        } else {
            colorChange -= 2;

        }

    }

    @Override
    public void timePassed() {
    }
}
