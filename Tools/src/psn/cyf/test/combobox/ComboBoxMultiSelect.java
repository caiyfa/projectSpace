package psn.cyf.test.combobox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ComboBoxMultiSelect extends JFrame implements ActionListener {
   private JPanel panel;
    private java.util.List<Person>  listInit=new ArrayList<> ();
    private java.util.List<Person>  listSelected=new ArrayList<> ();
    private JComboBox<Person> jComboBox=new JComboBox<>();
    public ComboBoxMultiSelect( ) throws HeadlessException {
        super("下拉多选");
        panel=new JPanel();
        listInit.add(new Person("张三",25));
        listInit.add(new Person("张四",26));
        listInit.add(new Person("张无",27));
        listInit.stream().forEach((p)->{
            jComboBox.addItem(p);
        });
        panel.add(jComboBox);
        panel.setPreferredSize(new Dimension(100,100));

        this.add(panel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        ComboBoxMultiSelect clientDemo=new ComboBoxMultiSelect() ;

        clientDemo.setSize(new Dimension(500,500));
        //设置窗体居中显示
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        int x = (int)(toolkit.getScreenSize().getWidth()-clientDemo.getWidth())/2;

        int y = (int)(toolkit.getScreenSize().getHeight()-clientDemo.getHeight())/2;
        clientDemo.setLocation(x,y);
        clientDemo.setVisible(true);
        clientDemo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

