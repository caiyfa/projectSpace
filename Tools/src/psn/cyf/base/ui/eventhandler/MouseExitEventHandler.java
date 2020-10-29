package psn.cyf.base.ui.eventhandler;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import psn.cyf.utils.UIUtil;

import java.lang.reflect.Method;

public class MouseExitEventHandler implements EventHandler {
    @Override
    public void handle(Event event) {
        try {
            Button button= (Button) event.getSource();
            //使用反射统一修改样式就不用在新增按钮后需要新增switch-case了 2019-09-07
            Method method=Button.class.getMethod("setStyle",Class.forName("java.lang.String"));
            method.invoke(button, UIUtil.Style.Translucent);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
