package psn.cyf.business.json;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.vo.BaseVO;

public class JSONCtrl extends SuperCtrl {
    public AnchorPane jsonPane;







    @Override
    protected Button[] getEditStateButton() {
        return new Button[0];
    }

    @Override
    protected Button[] getNormalStateButton() {
        return new Button[0];
    }

    @Override
    protected TextInputControl[] getTextInputCtrl() {
        return new TextInputControl[0];
    }

    @Override
    protected Class<? extends BaseVO> getBaseClass() {
        return null;
    }

    @Override
    protected Node[] getDecorateArr() {
        return new Node[0];
    }

    @Override
    protected Pane getPane() {
        return null;
    }
}
