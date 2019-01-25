package removal;
/**
 *
 * @author nitai
 *
 */
public class Counter {
    private int counter;
    /**
     *
     * @param c counter.
     */
    public Counter(int c) {
        this.counter = c;
    }
    /**
     *
     * @param number add number to current count.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }
    /**
     *
     * @param number subtract number from current count.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }
    /**
     *
     * @return get current count.
     */
    public int getValue() {
        return this.counter;
    }
}
