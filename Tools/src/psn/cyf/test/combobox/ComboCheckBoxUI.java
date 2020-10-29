package psn.cyf.test.combobox;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;
import java.awt.event.*;

public class ComboCheckBoxUI extends MetalComboBoxUI {
    private boolean isMultiSel=false;
    public int maxWidth=300;

    public ComboCheckBoxUI() {
    }

    public ComboCheckBoxUI(int maxWidth) {
        this.maxWidth = maxWidth;
    }
    public static ComponentUI creatUI(JComponent c){
        return new ComboCheckBoxUI();
    }

    @Override
    protected ComboPopup createPopup() {

        return super.createPopup();
    }
    public class ComboCheckPopUp extends BasicComboPopup {
        public ComboCheckPopUp(JComboBox combo) {
            super(combo);
        }
        private int width=-1;
        private int maxWidth=300;

        public ComboCheckPopUp(JComboBox combo, int maxWidth) {
            super(combo);
            this.maxWidth = maxWidth;
        }

        @Override
        protected JScrollPane createScroller() {
            return new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }

        @Override
        protected MouseListener createListMouseListener() {
            //新建类
            return super.createListMouseListener();
        }

        @Override
        public void show() {
            Dimension popubSize=comboBox.getSize();
            Insets insets=getInsets();
            popubSize.setSize(popubSize.width-(insets.right+insets.left),getPopupHeightForRowCount(comboBox.getMaximumRowCount()));
            int maxWidthOfCell=calcPreferredWidth();
            width=maxWidthOfCell;
            if(comboBox.getItemCount()>comboBox.getMaximumRowCount()){
                width+=scroller.getVerticalScrollBar().getPreferredSize().width;
            }
            if(width>maxWidth){
                width=this.maxWidth;
            }
            if(width<this.comboBox.getWidth()){
                width=this.comboBox.getWidth();
            }
            if(maxWidthOfCell>width){
                popubSize.height+=scroller.getHorizontalScrollBar().getPreferredSize().height;
            }
            Rectangle popubBounds=computePopupBounds(0,comboBox.getBounds().height,width,popubSize.height);
            scroller.setMaximumSize(popubBounds.getSize());
            scroller.setPreferredSize(popubSize.getSize());
            scroller.setMinimumSize(popubSize.getSize());
            list.invalidate();
            syncListSelectionWithComboBoxSelection();
            list.ensureIndexIsVisible(list.getSelectedIndex());
            setLightWeightPopupEnabled(comboBox.isLightWeightPopupEnabled());
            show(comboBox,popubBounds.x,popubBounds.y);
        }
        void syncListSelectionWithComboBoxSelection(){
            int selectedIndex=comboBox.getSelectedIndex();
            if(selectedIndex==-1){
                list.clearSelection();
            }else {
                list.setSelectedIndex(selectedIndex);
            }
        }
        private int calcPreferredWidth(){
            int prefferedWidth=0;
            ListCellRenderer renderer=list.getCellRenderer();
            for(int index=0,count=list.getModel().getSize();index<count;index++){
                Object element=list.getModel().getElementAt(index);
                Component component=renderer.getListCellRendererComponent(list,element,index,false,false);
                Dimension dimension=component.getPreferredSize();
                if(dimension.width>prefferedWidth){
                    prefferedWidth=dimension.width;
                }

            }
            return prefferedWidth;
        }
        public void setPopubWidth(int width){
            this.width=width;
        }
        protected class CheckBoxKeyHandler extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                isMultiSel=e.isControlDown();
            }

            @Override
            public void keyReleased(KeyEvent e) {
               isMultiSel=e.isControlDown();
            }
        }
        protected class CheckBoxListMouseHandler extends MouseAdapter{
            @Override
            public void mousePressed(MouseEvent e) {
                 int index=list.getSelectedIndex();
                 ComboCheckBoxEntry item= (ComboCheckBoxEntry) list.getModel().getElementAt(index);
                 boolean checked=item.isChecked();
                 int size=list.getModel().getSize();
                 if(isMultiSel){
                     item.setChecked(checked);
                 }else {
                     for(int i=0;i<size;i++){
                         ComboCheckBoxEntry comboCheckBoxEntry= (ComboCheckBoxEntry) list.getModel().getElementAt(i);
                         comboCheckBoxEntry.setChecked(false);
                     }
                     item.setChecked(true);
                 }
                 updateListBoxSelectionForEvent(e,false);
                 Rectangle rectangle=list.getCellBounds(0,size-1);
                 list.repaint(rectangle);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(!isMultiSel){
                    ComboCheckBoxEntry item= (ComboCheckBoxEntry) list.getSelectedValue();
                    if(item.checked){
                        comboBox.setSelectedIndex(list.getSelectedIndex());
                        comboBox.setPopupVisible(false);
                    }
                }
            }
        }

    }

}
