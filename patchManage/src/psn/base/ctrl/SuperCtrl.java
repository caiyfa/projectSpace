package psn.base.ctrl;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import psn.base.eventhandler.MouseClickHandler;
import psn.base.eventhandler.MouseEnterHandler;
import psn.base.eventhandler.MouseExitEventHandler;
import psn.base.exception.RunException;
import psn.base.img.ResourceAnchor;
import psn.base.ui.MessageDialog;
import psn.base.utils.UIUtil;
import psn.base.vo.BaseVO;

import java.lang.Class;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;


public abstract class SuperCtrl extends  BusinessCtrl implements Initializable {

     protected abstract Button[] getEditStateButton();
    protected abstract Button[] getNormalStateButton();
    protected abstract TextInputControl[] getTextInputCtrl();
    protected abstract Class<? extends BaseVO> getBaseClass();
    protected void clear(){
        if(getTextInputCtrl()!=null){
            for(TextInputControl inputControl:getTextInputCtrl()){
                inputControl.setText("");
            }
        }
    }
    protected void setCacheVO(BaseVO baseVO){
        if(getTextInputCtrl()!=null){
            for(TextInputControl inputControl:getTextInputCtrl()){
                try {
                    Object value=baseVO.getFieldValue(inputControl.getId());
                    inputControl.setText(value==null?null:value.toString());
                } catch (RunException e) {
                    showMessage(e.getMessage());
                }
            }
        }
    }
    protected BaseVO getCacheVO(){
        if(getBaseClass()==null){
            return null;
        }
        Class<BaseVO> voClass= (Class<BaseVO>) getBaseClass();
        Field[] fields=voClass.getDeclaredFields();
        try {
            BaseVO instance=voClass.newInstance();
             for(Field field:fields){

                 for(TextInputControl inputControl:getTextInputCtrl()){
                     if(inputControl.getId()==null){
                         continue;
                     }
                     if(inputControl.getId().equals(field.getName())){
                         instance.setFieldValue(field.getName(),inputControl.getText());
                     }
                 }
//
             }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    protected void turnToEditState(){
        changeStateTo(true);
        changeInputState(true);
    }
    protected void turnToNormalState(){
        changeStateTo(false);
        changeInputState(false);
    }
    protected void changeInputState(boolean isEdit){
        if(getTextInputCtrl()!=null){
            for(TextInputControl control :getTextInputCtrl()){
                control.setEditable(isEdit);
            }
        }
    }
    private void changeStateTo(boolean isEditState){
        if(getEditStateButton()!=null){
            for(Button button:getEditStateButton()){
                button.setDisable(!isEditState);
            }
        }
        if(getNormalStateButton()!=null){
            for(Button button:getNormalStateButton()){
                button.setDisable(isEditState);
            }
        }
    }


    public void btnEnter(MouseEvent event) throws RuntimeException {
       try {
           Button button= (Button) event.getSource();
           //使用反射统一修改样式就不用在新增按钮后需要新增switch-case了 2019-09-07
           Method method=Button.class.getMethod("setStyle", Class.forName("java.lang.String"));
//        Method method=Node.class.getMethod("setStyle");
//        System.out.println("method:"+method.toString());
           method.invoke(button, UIUtil.Style.Colors);
//        System.out.println(field.getClass());
       /* switch (button.getId()){
            case "patchBtn":{
                patchBtn.setStyle(UIUtil.Style.Colors);
                break;
            }
            case "projectBtn":{
                projectBtn.setStyle(UIUtil.Style.Colors);
                break;
            }
        }*/
       }catch (Exception e){
           throw new RuntimeException(e);
       }


    }

    public void btnExit(MouseEvent event) throws  RuntimeException {
        try {
            Button button= (Button) event.getSource();
            //使用反射统一修改样式就不用在新增按钮后需要新增switch-case了 2019-09-07
            Method method=Button.class.getMethod("setStyle",Class.forName("java.lang.String"));
            method.invoke(button,UIUtil.Style.Translucent);
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

    protected void showMessage(Object message){
        new MessageDialog(message==null?"":message.toString()).show();
    }

}
