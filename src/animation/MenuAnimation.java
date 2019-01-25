package animation;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 * @author nitai
 *
 * @param <T> t.
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<MenuSelction<T>> selections = new ArrayList<MenuSelction<T>>();
    private boolean stop = false;
    private T status;
    private KeyboardSensor keyboard;
    private AnimationRunner ar;
    private String key;
    private List<Subselction<T>> menus = new ArrayList<Subselction<T>>();
    /**
     *
     * @param ks k.
     * @param ar a.
     */
    public MenuAnimation(KeyboardSensor ks, AnimationRunner ar) {
        this.keyboard = ks;
        this.ar = ar;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        int i = (int) (d.getHeight() / 4);
        /**for (MenuSelction<T> s: selections) {
            d.drawText(d.getWidth() / 4, i, "(" +s.getKey()+ ")   " +  s.getMessage(), 30);
            i += 45;
        }**/
        for (Subselction<T> s : this.menus) {
            d.drawText(d.getWidth() / 4, i, "(" + s.getKey() + ")   " +  s.getMessage(), 30);
            i += 45;
        }
        for (MenuSelction<T> s: selections) {
            d.drawText(d.getWidth() / 4, i, "(" + s.getKey() + ")   " +  s.getMessage(), 30);
            i += 45;
        }
         for (Subselction<T> s : this.menus) {
             if (this.keyboard.isPressed(s.getKey())) {
                 this.ar.run(s.task());
                 Task<Void> t = (Task<Void>) s.task().getStatus();
                 t.run();
                 s.task().getBack();
             }
         }
         for (MenuSelction<T> s : selections) {
             if (this.keyboard.isPressed(s.getKey())) {
                 this.key = s.getKey();
                 this.status = s.task();
                 this.stop = true;
             }
         }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public void addSelection(String k, String message, T returnVal) {
        selections.add(new MenuSelction<T>(k, message, returnVal));
    }

    @Override
    public T getStatus() {
        this.stop = true;
        return this.status;
    }
    /**
     *
     */
    public void getBack() {
        this.stop = false;
    }

    @Override
    public void addSubMenu(String k, String message, Menu<T> subMenu) {
        this.menus.add(new Subselction<T>(k, message, subMenu));

    }

}
