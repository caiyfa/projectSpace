package psn.cyf.behavioral.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Button implements Clickable {
    String color;//颜色
    int x,y;//坐标
    //保存观察者
    List<ClickableObserver> observers=new ArrayList<>();

    public Button(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public void click() {
        System.out.println("点击按钮");
        observers.stream().forEach((observer)->{
            observer.clicked(this);
        });

    }

    @Override
    public void addClickableObserver(ClickableObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeClickableObserver(ClickableObserver observer) {
        observers.remove(observer);
    }

    @Override
    public String toString() {
        return "颜色:"+color+"，坐标：（"+x+","+y+"）";
    }
}
