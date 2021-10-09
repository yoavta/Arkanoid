package general.Game;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * BackgroundLevel3 - special background for level 3.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class BackgroundLevel3 implements Sprite {

    private double sunCenter = -200;

    @Override
    public void drawOn(DrawSurface d) {
        int numOfColors = 20;
        int changingFactor = 4;
        int colorHeight = (int) 800 / numOfColors;
        Color color = new Color(0, 115, 226);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        for (int i = 0; i < numOfColors; i++) {
            int newR = Math.abs(Math.min((r + i * changingFactor), 255));
            int newG = Math.abs(Math.min((g + i * changingFactor / 2), 255));
            int newB = Math.abs(Math.min((b + i * changingFactor), 255));
            Color newColor = new Color(newR, newG, newB);
            d.setColor(newColor);
            int x = 0;
            int y = i * colorHeight;
            int width = 800;
            int height = 600 - i * colorHeight;
            d.fillRectangle(x, y, width, height);
            ;
        }
        // sun paint
        int ballRadius = 150;
        d.setColor(Color.yellow);
//        d.drawLine((int)sunCenter, 20,(int)sunCenter-300,170);

        d.drawLine((int) sunCenter, 20, (int) sunCenter - 320, 100);
        d.drawLine((int) sunCenter, 20, (int) sunCenter - 350, 0);
        d.drawLine((int) sunCenter, 20, (int) sunCenter - 310, 150);
        d.drawLine((int) sunCenter, 20, (int) sunCenter - 250, 250);
        d.drawLine((int) sunCenter, 20, (int) sunCenter - 150, 310);
        d.drawLine((int) sunCenter, 20, (int) sunCenter, 350);
        d.drawLine((int) sunCenter, 20, (int) sunCenter + 150, 310);
        d.drawLine((int) sunCenter, 20, (int) sunCenter + 250, 250);
        d.drawLine((int) sunCenter, 20, (int) sunCenter + 310, 150);
        d.drawLine((int) sunCenter, 20, (int) sunCenter + 350, 0);
        d.drawLine((int) sunCenter, 20, (int) sunCenter + 320, 100);
//        d.drawLine((int)sunCenter, 20,(int)sunCenter+300,170);

//        d.drawLine((int)sunCenter, 30,(int)sunCenter,300);
//        d.drawLine((int)sunCenter, 30,(int)sunCenter+50,250);
//        d.drawLine((int)sunCenter, 30,300,300);
//        d.drawLine((int)sunCenter, 30,300,300);
//        d.drawLine((int)sunCenter, 30,300,300);
//        d.drawLine((int)sunCenter, 30,300,300);
//        d.drawLine((int)sunCenter, 30,300,300);
//        d.drawLine((int)sunCenter, 30,300,300);


        d.setColor(new Color(255, 255, 212));
        d.fillCircle((int) sunCenter, 20, 200);
        d.setColor(new Color(255, 254, 169));
        d.fillCircle((int) sunCenter, 20, 150);
        d.setColor(new Color(255, 253, 99));
        d.fillCircle((int) sunCenter, 20, 100);
        d.setColor(new Color(255, 251, 0));
        d.fillCircle((int) sunCenter, 20, 50);
        if (sunCenter > 1100) {
            sunCenter = -300;
        } else {

            sunCenter = (sunCenter + 1);
        }

    }

    @Override
    public void timePassed() {
    }
}
