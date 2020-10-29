package psn.cyf.test.combobox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class JComboCheckBox extends JComboBox {
    private int maxWidth=300;

    public JComboCheckBox( ) {
        super();
        setRenderer(new ComboCheckBoxRenderer());
        updateUI();
    }
    public JComboCheckBox(String[] items){
        setRenderer(new ComboCheckBoxRenderer());
        addItems(items);
        updateUI();
    }
    public JComboCheckBox(Vector<String> items){
        super();
        setRenderer(new ComboCheckBoxRenderer());
        addItems(items.toArray(new String[0]));
        updateUI();
    }
    public JComboCheckBox(int maxWidth){
        super();
        this.maxWidth=maxWidth;
        setRenderer(new ComboCheckBoxRenderer());
        updateUI();
    }
    public JComboCheckBox(String [] items,int maxWidth){
        super();
        this.maxWidth=maxWidth;
        setRenderer(new ComboCheckBoxRenderer());
        addItems(items);
        updateUI();
    }
    public JComboCheckBox(Vector<String> items,int maxWidth){
        super();
        this.maxWidth=maxWidth;
        setRenderer(new ComboCheckBoxRenderer());
        addItems(items.toArray(new String[0]));
        updateUI();
    }

    public void addItems(String[] items){
        for(int i=0;i<items.length;i++){
            String str=items[i];
            this.addItem(new ComboCheckBoxEntry(String.valueOf(i+1),str));
        }
    }
    public void addItem(ComboCheckBoxEntry item){
        super.addItem(item);
    }
    public void addItem(boolean checked,boolean state ,String id,String value){
        super.addItem(new ComboCheckBoxEntry(checked,state,id,value));
    }
    public String[] getCheckedCodes(){
        Vector values=new Vector();
        DefaultComboBoxModel model= (DefaultComboBoxModel) getModel();
        for(int i=0;i<model.getSize();i++){
            ComboCheckBoxEntry item= (ComboCheckBoxEntry) model.getElementAt(i);
            boolean checked=item.isChecked();
            if(checked){
                values.add(item.getUniqueCode());
            }
        }
        String[] resArr=new String[values.size()];
        values.copyInto(resArr);
        return resArr;
    }
    public String[] getCheckedValues(){
        Vector values=new Vector();
        DefaultComboBoxModel model= (DefaultComboBoxModel) getModel();
        for(int i=0;i<model.getSize();i++){
            ComboCheckBoxEntry item= (ComboCheckBoxEntry) model.getElementAt(i);
            boolean checked=item.isChecked();
            if(checked){
                values.add(item.value);
            }
        }
        String[] retVal=new String[values.size()];
        values.copyInto(retVal);
        return retVal;
    }

    @Override
    public void updateUI() {
        setUI(new ComboCheckBoxUI(maxWidth));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(650, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();

        String[] values = new String[]{"1111111111111111111", "22", "33333333333333333333",
                "4444444444444", "55555555555555555", "6", "77", "6", "77", "6", "77"};
        final JComboCheckBox checkBox = new JComboCheckBox(values);

        checkBox.setPreferredSize(new Dimension(150, 30));
        jPanel.add(checkBox);

        JButton btnCode = new JButton("Code");
        btnCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String string : checkBox.getCheckedCodes()) {
                    System.out.print(string + " ");
                }
                System.out.println("");
            }
        });
        jPanel.add(btnCode);

        JButton btnValue = new JButton("Value");
        btnValue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                for (String string : checkBox.getCheckedValues()) {
                    System.out.print(string + " ");
                }
                System.out.println("");
            }
        });
        jPanel.add(btnValue);

        frame.getContentPane().add(jPanel);
        frame.setVisible(true);
    }
}
