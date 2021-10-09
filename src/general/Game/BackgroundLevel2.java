package general.Game;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * BackgroundLevel2 - special background for level 2.
 *
 * @author yoav tamir
 * @version 1.0
 */

public class BackgroundLevel2 implements Sprite {

    private double cloudsLocation = 200;

    @Override
    public void drawOn(DrawSurface d) {
        int changingFactor = 4;
        int numOfColors = 20;
        int colorHeight = (int) 800 / numOfColors;
        Color color = new Color(0, 115, 226);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        for (int i = 0; i < numOfColors; i++) {
            int newG = Math.abs(Math.min((g + i * changingFactor / 2), 255));
            int newB = Math.abs(Math.min((b + i * changingFactor), 255));
            int newR = Math.abs(Math.min((r + i * changingFactor), 255));
            int y = i * colorHeight;
            int x = 0;
            Color newColor = new Color(newR, newG, newB);
            d.setColor(newColor);
            int width = 800;
            int height = 600 - i * colorHeight;
            d.fillRectangle(x, y, width, height);
        }

        // cloud paint
        int cloudHeight = 100;
        for (int i = -1; i < 3; i++) {
            d.setColor(new Color(255, 255, 255));
            d.fillRectangle((int) cloudsLocation + i * 200, cloudHeight, 100, 30);
            d.fillCircle((int) cloudsLocation + i * 200, cloudHeight + 5, 25);
            d.fillCircle((int) cloudsLocation + 40 + i * 200, cloudHeight - 5, 35);
            d.fillCircle((int) cloudsLocation + 95 + i * 200, cloudHeight + 5, 25);
        }


        if (cloudsLocation > 1100) {
            cloudsLocation = -550;
        } else {
            cloudsLocation = (cloudsLocation + 1);
        }

    }

    @Override
    public void timePassed() {
    }
}
