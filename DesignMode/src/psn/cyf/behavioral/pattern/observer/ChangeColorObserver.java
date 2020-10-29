package psn.cyf.behavioral.pattern.observer;

public class ChangeColorObserver implements ClickableObserver {
    @Override
    public void clicked(Clickable clickable) {
        Button button= (Button) clickable;
        button.color="红色";
    }
}
