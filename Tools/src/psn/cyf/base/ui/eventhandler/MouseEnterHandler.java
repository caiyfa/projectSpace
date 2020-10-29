package psn.cyf.base.ui.eventhandler;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import psn.cyf.utils.UIUtil;

import java.lang.reflect.Method;

public class MouseEnterHandler implements EventHandler {
    @Override
    public void handle(Event event) {
        Button button= (Button) event.getSource();
        //使用反射统一修改样式就不用在新增按钮后需要新增switch-case了 2019-09-07
        Method method= null;
        try {
            method = Button.class.getMethod("setStyle", Class.forName("java.lang.String"));
            //        Method method=Node.class.getMethod("setStyle");
//        System.out.println("method:"+method.toString());
            method.invoke(button, UIUtil.Style.Colors);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
