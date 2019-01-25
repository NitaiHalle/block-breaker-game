package animation;
/**
 *
 * @author nitai
 *
 * @param <T> t.
 */
public class Subselction<T> {
    private Menu<T> menu;
    private String key;
    private String message;
    /**
     *
     * @param mes message.
     * @param k key.
     * @param m menu.
     */
    public Subselction(String mes, String k , Menu<T> m) {
         this.menu = m;
         this.key = mes;
         this.message = k;
    }
    /**
     *
     * @return m.
     */
    public Menu<T> task() {
        return menu;
    }
    /**
     *
     * @return k.
     */
    public String getKey() {
        return key;
    }
    /**
     *
     * @return m.
     */
    public String getMessage() {
        return message;
    }
}
