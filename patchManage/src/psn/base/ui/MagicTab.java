package psn.base.ui;

import javafx.event.Event;
import javafx.scene.control.Tab;

public class MagicTab extends Tab {
    public void close(MagicTab magicTab){
        Event.fireEvent(magicTab,new Event(Tab.CLOSED_EVENT));
    }
}
