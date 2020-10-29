package psn.base.eventhandler;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import psn.base.utils.UIUtil;

import java.lang.reflect.Method;

public class MouseExitEventHandler implements EventHandler {
    @Override
    public void handle(Event event) {
        try {
            Button button= (Button) event.getSource();
            //使用反射统一修改样式就不用在新增按钮后需要新增switch-case了 2019-09-07
            Method method=Button.class.getMethod("setStyle",Class.forName("java.lang.String"));
            method.invoke(button, UIUtil.Style.Translucent);
      /*  switch (button.getId()){
            case "patchBtn":{
                patchBtn.setStyle(UIUtil.Style.Translucent);
                break;
            }
            case "projectBtn":{
                projectBtn.setStyle(UIUtil.Style.Translucent);
                break;
            }
        }*/
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
