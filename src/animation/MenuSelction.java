package animation;
/**
 *
 * @author nitai
 *
 * @param <T> t.
 */
public class MenuSelction<T> {
    private String key;
    private String message;
    private T returnVal;
    /**
     *
     * @param key k.
     * @param message m.
     * @param returnVal r.
     */
    public MenuSelction(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }
    /**
     *
     * @return key.
     */
    public String getKey() {
        return key;
    }
    /**
     *
     * @return massage.
     */
    public String getMessage() {
        return message;
    }
    /**
     *
     * @return val.
     */
    public T task() {
        return returnVal;
    }

}
