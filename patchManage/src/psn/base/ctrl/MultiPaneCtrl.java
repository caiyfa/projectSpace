package psn.base.ctrl;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import psn.base.cache.Cache;
import psn.base.fxml.FxmlAnchor;
import psn.base.ui.MagicTab;
import psn.base.utils.FontawesomeWithJavaFX;
import psn.base.utils.UIUtil;
import psn.base.vo.BaseVO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MultiPaneCtrl extends SuperCtrl {
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
            Label homeLabel=FontawesomeWithJavaFX.createGlyphLabel("home",20);
            homeLabel.setId(Cache.Tab.Main);
             tab.setGraphic(homeLabel);
            Parent parent= FXMLLoader.load(FxmlAnchor.class.getResource(FxmlAnchor.mainPage));
//            parent.prefHeight(432.0);
//            parent.prefWidth(691.0);
            tab.setContent(parent);
            //设置主页面不可关闭
            tab.setClosable(false);
            tabPane.setStyle(UIUtil.Style.Translucent);
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
