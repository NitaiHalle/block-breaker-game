package animation;
/**
 *
 * @author nitai
 *
 * @param <T> generics.
 */
public interface Menu<T> extends Animation {
    /**
     *
     * @param key k.
     * @param message m.
     * @param returnVal r.
     */
     void addSelection(String key, String message, T returnVal);
     /**
     *
     * @return s.
     */
    T getStatus();
    /**
    *
    * @param key k.
    * @param message m.
    * @param subMenu s.
    */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**
     *return.
     */
    void getBack();
}
