package psn.cyf.behavioral.pattern.observer;

public interface Clickable {
    void click();
    void addClickableObserver(ClickableObserver observer);
    void removeClickableObserver(ClickableObserver observer);
}
