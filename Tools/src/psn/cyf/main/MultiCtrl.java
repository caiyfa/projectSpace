package psn.cyf.main;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.ui.MagicTab;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.multi.MainPageCtrl;
import psn.cyf.cache.Cache;
import psn.cyf.img.ResourceAnchor;
import psn.cyf.utils.FontawesomeWithJavaFX;
import psn.cyf.utils.FxmlUtils;
import psn.cyf.utils.UIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MultiCtrl extends SuperCtrl {
    public TabPane tabPane;


    @Override
    protected Node[] getDecorateArr() {
        return new Node[0];
    }

    @Override
    protected Pane getPane() {
        return null;
    }

    @Override
    protected Button[] getEditStateButton() {
        return new Button[0];
    }

    @Override
    protected Button[] getNormalStateButton() {
        return new Button[0];
    }

    @Override
    protected TextInputControl[] getTextInputCtrl() {
        return new TextInputControl[0];
    }

    @Override
    protected Class<? extends BaseVO> getBaseClass() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);
        FontawesomeWithJavaFX.initInfo();

        MagicTab tab=new MagicTab();

        try {
            Label homeLabel= FontawesomeWithJavaFX.createGlyphLabel("home",20);
            homeLabel.setId(Cache.Tab.Main);
            tab.setGraphic(homeLabel);
            Parent parent= FXMLLoader.load(FxmlUtils.getFxmlFromCtrlName(MainPageCtrl.class));
//            parent.prefHeight(432.0);
//            parent.prefWidth(691.0);
            tab.setContent(parent);
            //设置主页面不可关闭
            tab.setClosable(false);
//            tabPane.setStyle(UIUtil.Style.Colors);
            tabPane.setStyle("my-tab-header-background: #8abcd1 ;");
            tabPane.getTabs().add(tab);
            tabPane.setSide(Side.TOP);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Cache.CONTROLLER.put(Cache.Name.MultiCtrl,this);
    }
    public boolean selectTabIfExist(Label label){
        if(label.getId()==null){
            return false;
        }

        List<Tab> tabList= tabPane.getTabs();
        for(Tab tab:tabList){
            if(label.getId().equals(tab.getGraphic().getId())){
                SingleSelectionModel selectionModel= tabPane.getSelectionModel();
                selectionModel.select(tab);
                return true;
            }
        }
        return false;
    }
    public void addPane(Node tabName,Parent parent){
        MagicTab tab=new MagicTab();
        tab.setGraphic(tabName);
        tab.setContent(parent);
        tabPane.getTabs().add(tab);
        SingleSelectionModel selectionModel= tabPane.getSelectionModel();
        selectionModel.select(tab);
    }
    public void closeCurrentTab(){
        SingleSelectionModel selectionModel= tabPane.getSelectionModel();
        selectionModel.selectPrevious();
    }
}
