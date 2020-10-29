package psn.cyf.base.listener;

import javafx.beans.value.ChangeListener;
import psn.cyf.base.ctrl.BusinessCtrl;

public abstract class AbstractChangeListener<T>   implements ChangeListener<T> {
    private BusinessCtrl businessCtrl;

    public AbstractChangeListener(BusinessCtrl businessCtrl) {
        this.businessCtrl = businessCtrl;
    }

    public BusinessCtrl getBusinessCtrl() {
        return businessCtrl;
    }
}
