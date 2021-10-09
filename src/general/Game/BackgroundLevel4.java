package general.Game;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;
import java.awt.Polygon;


/**
 * BackgroundLevel4 - special background for level 4.
 *
 * @author yoav tamir
 * @version 1.0
 */

public class BackgroundLevel4 implements Sprite {

    private int moveRight = 5;
    private double sunCenter = -100;
    private int colorChange;
    private boolean add = true;

    @Override
    public void drawOn(DrawSurface d) {
        int numOfColors = 30;
        int changingFactor = 4;
        int colorHeight = (int) 800 / numOfColors;
        Color color = new Color(226, 0, 0);
        int r = (int) (color.getRed());
        int g = (int) (color.getGreen() + colorChange) % 256;
        int b = (int) (color.getBlue());
        for (int i = 0; i < numOfColors; i++) {
            int newB = Math.abs(Math.min((b + i * changingFactor), 255));
            int newR = Math.abs(Math.min((r + i * changingFactor), 255));
            int newG = Math.abs(Math.min((g + i * changingFactor / 2), 255));
            int width = 800;
            int height = 600 - i * colorHeight;
            int y = i * colorHeight;
            int x = 0;
            Color newColor = new Color(newR, newG, newB);
            d.setColor(newColor);
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

        for (int i = -1; i < 3; i++) {
            d.setColor(new Color(97, 97, 97));
            int spikesNumber = 30;
            int[] xPoints;
            xPoints = new int[spikesNumber];
            int[] yPoints;
            yPoints = new int[spikesNumber];

            int distanceBetweenSpikes = 800 / spikesNumber + 10;
            int j = 0, t = 1;
            for (; j < spikesNumber - 1; j += 2, t += 2) {
                xPoints[j] = j * distanceBetweenSpikes + (int) sunCenter;
                yPoints[j] = 600;
                xPoints[t] = j * distanceBetweenSpikes + distanceBetweenSpikes + (int) sunCenter;
                yPoints[t] = 580;
            }
            xPoints[j - 1] = j * distanceBetweenSpikes;
            yPoints[j - 1] = 600;

            d.fillPolygon(new Polygon(xPoints, yPoints, 30));

        }

        if (sunCenter > 0) {
            moveRight = -5;
        }
        if (sunCenter < -80) {
            moveRight = 5;
        }
        sunCenter = (sunCenter + (moveRight) * 1);
        d.setColor(new Color(255, 40, 40));
        d.drawText(50, 300, "I will BEAT you!! :O", 80);


    }

    @Override
    public void timePassed() {
    }
}
