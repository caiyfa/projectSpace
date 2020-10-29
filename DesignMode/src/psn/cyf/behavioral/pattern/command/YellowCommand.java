package psn.cyf.behavioral.pattern.command;

import javax.swing.*;
import java.awt.*;

public class YellowCommand extends JButton implements ICommand {
    private JPanel panel;

    public YellowCommand(String text, JPanel panel) {
        super(text);
        this.panel = panel;
    }

    @Override
    public void execute() {
        panel.setBackground(Color.YELLOW);
    }
}
