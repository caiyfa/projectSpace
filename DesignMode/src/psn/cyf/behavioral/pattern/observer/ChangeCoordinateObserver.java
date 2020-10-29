package psn.cyf.behavioral.pattern.observer;

/**
 * 修改坐标
 */
public class ChangeCoordinateObserver  implements ClickableObserver{
    @Override
    public void clicked(Clickable clickable) {
        Button button= (Button) clickable;
        button.x=1000;
        button.y=90;
    }
}
