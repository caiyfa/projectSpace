package psn.cyf.base.ui.dialog;

import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MessageDialog extends BaseDialog<String> {
    public MessageDialog() {
        init();
    }

    public MessageDialog(String message) {
        init();
        super.setMessage(message);


    }
    public void setMessage(String message){
        super.setMessage(message);
    }

    private void init() {
        //初始化界面
        initPage( );
        super.setButtonTypes(ButtonType.OK);
        //设置内容
        super.setDialogTitle("提示");
    }


}
