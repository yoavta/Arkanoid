package general.Game;

/**
 * Counter- simple class for counting.
 *
 * @author yoav tamir
 * @version 1.0
 */
public class Counter {
    //fields
    private int value;

    /**
     * Counter constructor.
     *
     * @param value int
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * add number to current count.
     *
     * @param number int.
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number int
     */
    public void decrease(int number) {
        value -= number;
    }

    /**
     * getValue - get value.
     *
     * @return getValue int
     */
    public int getValue() {
        return value;
    }
}