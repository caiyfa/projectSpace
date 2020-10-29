package psn.cyf.behavioral.pattern.command;

import javax.swing.*;
import java.awt.*;

public class RedCommand extends JButton implements ICommand {
    private JPanel panel;

    public RedCommand(String text, JPanel panel) {
        super(text);
        this.panel = panel;
    }

    @Override
    public void execute() {
        panel.setBackground(Color.RED);
    }
}
