package psn.cyf.behavioral.pattern.command;

import javax.swing.*;

public class ExitCommand extends JButton implements ICommand {
    public ExitCommand(String text) {
        super(text);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
