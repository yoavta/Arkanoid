package listeners;

/**
 * HitNotifier - interface for all notifies in the game.
 */
public interface HitNotifier {


    /**
     * addHitListener -      Add hl as a listener to hit events.
     *
     * @param hl HitListener
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener -      Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener
     */
    void removeHitListener(HitListener hl);
}
