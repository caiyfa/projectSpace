package psn.cyf.base.ui.eventhandler;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import psn.cyf.img.ResourceAnchor;
import psn.cyf.utils.UIUtil;

import java.lang.reflect.Method;

public class MouseClickHandler implements EventHandler {
    @Override
    public void handle(Event event) {
        Button button= (Button) event.getSource();
        //使用反射统一修改样式就不用在新增按钮后需要新增switch-case了 2019-09-07
        Method method= null;
        try {
            method = Button.class.getMethod("setBackground", Class.forName("javafx.scene.layout.Background"));
            //        Method method=Node.class.getMethod("setStyle");
//        System.out.println("method:"+method.toString());
            method.invoke(button, UIUtil.getBackground(ResourceAnchor.btnEnterImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
