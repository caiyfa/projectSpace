package psn.cyf.base.ctrl;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import psn.cyf.base.event.AbstractEventHandler;
import psn.cyf.base.listener.AbstractChangeListener;
import psn.cyf.cache.UICache;
import psn.cyf.exception.RunException;
import psn.cyf.base.ui.dialog.MessageDialog;
import psn.cyf.utils.UIUtil;
import psn.cyf.base.vo.BaseVO;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public abstract class SuperCtrl extends BusinessCtrl implements Initializable {

    protected abstract Button[] getEditStateButton();

    protected abstract Button[] getNormalStateButton();

    protected abstract TextInputControl[] getTextInputCtrl();

    protected abstract Class<? extends BaseVO> getBaseClass();
    /*protected Node [] getCacheNode(){
        return new Node[0];
    }*/
    protected void loadCacheAndRegisterReleasedAction(){
//        Node[] textInputControls=getCacheNode();
       Field[] fields= this.getClass().getDeclaredFields();
       for(Field field:fields){
           if(TextInputControl.class.isAssignableFrom(field.getType())){
               try {
                   TextInputControl inputControl= (TextInputControl) field.get(this);
                   inputControl.setOnKeyReleased(new AbstractEventHandler<KeyEvent>(this) {
                       @Override
                       public void handle(Event event) {
//                System.out.println(event.getSource() instanceof TextField);
                   /* if(event.getSource() instanceof TextInputControl){
                        System.out.println(((TextInputControl)event.getSource()).getText());

                    }*/
                           if(event instanceof KeyEvent){
                               // System.out.println("释放"+((KeyEvent)event).getText()+"值"+"  类名#属性:"+this.getBusinessCtrl().getClass().getName()+"#"+((Node)event.getSource()).getId()+" "+((TextInputControl)event.getSource()).getText());
                               UICache.getMap().put(this.getBusinessCtrl().getClass().getName()+"#"+((Node)event.getSource()).getId(), ((TextInputControl)event.getSource()).getText());
                           }

                       }
                   });
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
       }
        //向文本中注入
        /*for(Node node:textInputControls){
            //文本框鼠标释放事件监听
            if(node instanceof TextInputControl){
                node.setOnKeyReleased();
            }


        }*/
//遍历界面缓存并装载到界面
        for(String key:UICache.getMap().keySet()){
            String [] clsField=key.split("#");
            if(clsField!=null&&clsField.length==2){
                if(this.getClass().getName().equals(clsField[0])){
                    try {
                        Field field=this.getClass().getDeclaredField(clsField[1]);
                       /* Class obj=this.getClass().getClassLoader().loadClass(field.getType().getTypeName());
                        System.out.println("装载"+ obj.asSubclass(TextInputControl.class)  );
                        System.out.println("isAssignAble"+TextInputControl.class.isAssignableFrom(obj) );
                        System.out.println("12345 "+TextInputControl.class.isAssignableFrom(field.getType()));*/
                       //文本框设值
                        if(TextInputControl.class.isAssignableFrom(field.getType())){
                            TextInputControl inputControl= (TextInputControl) field.get(this);
                            inputControl.setText(UICache.getMap().get(key)==null?"":UICache.getMap().get(key).toString());
                        }
                    } catch ( Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 加载FXML文件，简化加载过程
     *
     * @return
     * @throws IOException
     */
    public   Parent loadFXML(Class<? extends SuperCtrl> cls) throws IOException {
        Parent parent = FXMLLoader.load(cls.getResource(cls.getSimpleName().substring(0, cls.getSimpleName().length() - 4) + ".fxml"));
        String fxmlName = cls.getSimpleName().substring(0, cls.getSimpleName().length() - 4) + ".fxml";
        return parent;

    }

    protected void clear() {
        if (getTextInputCtrl() != null) {
            for (TextInputControl inputControl : getTextInputCtrl()) {
                inputControl.setText("");
            }
        }
    }

    protected void setCacheVO(BaseVO baseVO) {
        if (getTextInputCtrl() != null) {
            for (TextInputControl inputControl : getTextInputCtrl()) {
                try {
                    Object value = baseVO.getFieldValue(inputControl.getId());
                    inputControl.setText(value == null ? null : value.toString());
                } catch (RunException e) {
                    showMessage(e.getMessage());
                }
            }
        }
    }

    protected BaseVO getCacheVO() {
        if (getBaseClass() == null) {
            return null;
        }
        Class<BaseVO> voClass = (Class<BaseVO>) getBaseClass();
        Field[] fields = voClass.getDeclaredFields();
        try {
            BaseVO instance = voClass.newInstance();
            for (Field field : fields) {

                for (TextInputControl inputControl : getTextInputCtrl()) {
                    if (inputControl.getId() == null) {
                        continue;
                    }
                    if (inputControl.getId().equals(field.getName())) {
                        instance.setFieldValue(field.getName(), inputControl.getText());
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

    protected void turnToEditState() {
        changeStateTo(true);
        changeInputState(true);
    }

    protected void turnToNormalState() {
        changeStateTo(false);
        changeInputState(false);
    }

    protected void changeInputState(boolean isEdit) {
        if (getTextInputCtrl() != null) {
            for (TextInputControl control : getTextInputCtrl()) {
                control.setEditable(isEdit);
            }
        }
    }

    private void changeStateTo(boolean isEditState) {
        if (getEditStateButton() != null) {
            for (Button button : getEditStateButton()) {
                button.setDisable(!isEditState);
            }
        }
        if (getNormalStateButton() != null) {
            for (Button button : getNormalStateButton()) {
                button.setDisable(isEditState);
            }
        }
    }


    public void btnEnter(MouseEvent event) throws RuntimeException {
        try {
            Button button = (Button) event.getSource();
            //使用反射统一修改样式就不用在新增按钮后需要新增switch-case了 2019-09-07
            Method method = Button.class.getMethod("setStyle", Class.forName("java.lang.String"));
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void btnExit(MouseEvent event) throws RuntimeException {
        try {
            Button button = (Button) event.getSource();
            //使用反射统一修改样式就不用在新增按钮后需要新增switch-case了 2019-09-07
            Method method = Button.class.getMethod("setStyle", Class.forName("java.lang.String"));
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected void showMessage(Object message) {
        new MessageDialog(message == null ? "" : message.toString()).show();
    }

}
