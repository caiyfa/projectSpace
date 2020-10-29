package psn.cyf.base.ui.dialog;


import javafx.scene.control.ButtonType;

/**
 * 负责交互
 */
public class BooleanDialog extends BaseDialog<ButtonType> {

    public BooleanDialog() {
        //初始化页面
        super.initPage();
        super.setTitle("是否进行该操作");
    }
    public BooleanDialog(String message) {
        //初始化页面
        super.initPage();
        super.setTitle(message);
    }

    public boolean getDialogResult(){
        ButtonType res=  this.getResult();

        return ButtonType.OK.equals(res);
    }
}
