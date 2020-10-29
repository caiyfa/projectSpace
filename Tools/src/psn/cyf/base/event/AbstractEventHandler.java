package psn.cyf.base.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import psn.cyf.base.ctrl.BusinessCtrl;

public abstract class  AbstractEventHandler<T extends Event> implements EventHandler {
    private BusinessCtrl businessCtrl;
    public AbstractEventHandler(BusinessCtrl businessCtrl) {
        this.businessCtrl=businessCtrl;
    }

    public BusinessCtrl getBusinessCtrl() {
        return businessCtrl;
    }


}
