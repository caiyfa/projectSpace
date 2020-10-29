package psn.cyf.base.ui;

import javafx.event.Event;
import javafx.scene.control.Tab;
import psn.cyf.base.ui.dialog.BooleanDialog;

public class MagicTab extends Tab {
    public void close(MagicTab magicTab){
        Event.fireEvent(magicTab,new Event(Tab.CLOSED_EVENT));
        BooleanDialog dialog=new BooleanDialog();

    }
}
