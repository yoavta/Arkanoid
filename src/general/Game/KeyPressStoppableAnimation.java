package general.Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation - decorator class responsible of the condition stop->
 * for in between animations like pause, end screen etc'.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * KeyPressStoppableAnimation constructor.
     * @param sensor KeyboardSensor
     * @param key String
     * @param animation Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
        if (isAlreadyPressed) {
            return;
        }
        if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.

}