package psn.cyf.business.multi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.datasource.ctrl.DataBaseConfigureCtrl;
import psn.cyf.business.demand.ctrl.DemandCtrl;
import psn.cyf.business.global.query.ctrl.QueryFromGlobalDBCtrl;
import psn.cyf.business.java.project.ctrl.ProjectSpaceCtrl;
import psn.cyf.business.login.ctrl.LoginHelperCtrl;
import psn.cyf.business.patch.manage.ctrl.PatchCtrl;
import psn.cyf.business.project.ctrl.ProjectCtrl;
import psn.cyf.business.service.ctrl.TestWebServiceCtrl;
import psn.cyf.cache.Cache;
import psn.cyf.img.ResourceAnchor;
import psn.cyf.main.MultiCtrl;
import psn.cyf.utils.UIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageCtrl extends SuperCtrl {
    @FXML
    public Pane mainPane;

    @FXML
    public Button patchBtn;

    public VBox vBox1;
    public VBox vBox2;
    public VBox vBox3;
    public HBox hBox1;

    public Button projectBtn;
    public Button loginHelperBtn;

    public Button demandManagerBtn;
    public Button dbConfigBtn;
    public Button queryGlobal;
    public Button webServiceTest;
    public Button javaProjectBtn;

    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{javaProjectBtn, patchBtn, vBox1, vBox2, hBox1, vBox3, projectBtn, loginHelperBtn, demandManagerBtn, dbConfigBtn, queryGlobal, webServiceTest};
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        patchBtn.setBackground(UIUtil.getBackground(ResourceAnchor.btnExitImg));
        mainPane.setBackground(UIUtil.getBackground(ResourceAnchor.bacImg));
        Cache.CONTROLLER.put(Cache.Name.MainCtrl, this);
         Node[] disableNode = new Node[]{patchBtn, projectBtn, loginHelperBtn, demandManagerBtn,webServiceTest,javaProjectBtn};
        for (Node node : disableNode) {

            node.setVisible(false);

        }
    }

    public void onJavaProject() {
        Label label = new Label("Java项目");
        label.setId(Cache.Tab.JavaProject);
        MultiCtrl MultiCtrl= (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);if( !MultiCtrl.selectTabIfExist(label)){
            if( !MultiCtrl.selectTabIfExist(label)) {
                try {
                    Parent parent = super.loadFXML(ProjectSpaceCtrl.class);

                    MultiCtrl.addPane(label, parent);

                } catch ( Exception e) {
                   e.printStackTrace();
                }

            }


        }
    }

    public void openQueryGlobalPage() {
        Label label = new Label("全局查询");
        label.setId(Cache.Tab.QueryGlobal);
       MultiCtrl MultiCtrl= (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);if( !MultiCtrl.selectTabIfExist(label)){
            if( !MultiCtrl.selectTabIfExist(label)) {
                Parent parent = null;
                try {
                    parent = super.loadFXML(QueryFromGlobalDBCtrl.class);

                    MultiCtrl.addPane(label, parent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            UIUtil.initStage(projectStage,UIUtil.Title.Project,parent);
            }


        }

    }

    public void onWebServiceTest() {
        Label label = new Label("接口测试");
        label.setId(Cache.Tab.WebServiceTest);
        MultiCtrl MultiCtrl = (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);
        if (!MultiCtrl.selectTabIfExist(label)) {

            Parent parent = null;
            try {
                parent = super.loadFXML(TestWebServiceCtrl.class);

                MultiCtrl.addPane(label, parent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public void openLoginHelperPage(ActionEvent event) {
        Label label = new Label("登录辅助");
        label.setId(Cache.Tab.LoginHelper);
        MultiCtrl MultiCtrl= (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);
        if( !MultiCtrl.selectTabIfExist(label)){
            Parent parent= null;
            try {
                parent = super.loadFXML(LoginHelperCtrl.class);

                MultiCtrl.addPane(label,parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            UIUtil.initStage(projectStage,UIUtil.Title.Project,parent);



        }
    }

    /**
     * 打开项目管理界面
     */
    public void openProjectPage(ActionEvent event) {
        //若缓存中存在则直接调用缓存
//        if(Cache.STAGE.get(Cache.Name.ProjectPage)!=null){
//            Cache.STAGE.get(Cache.Name.ProjectPage).show();
//            return;
//        }
      try {
//        Stage projectStage=new Stage();
            Label label=new Label("项目维护");
            label.setId(Cache.Tab.Project);
            MultiCtrl MultiCtrl= (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);
               if( !MultiCtrl.selectTabIfExist(label)){
                   Parent parent=super.loadFXML(ProjectCtrl.class);
//            UIUtil.initStage(projectStage,UIUtil.Title.Project,parent);


                   MultiCtrl.addPane(label,parent);
               }

//            projectStage.setX(Cache.STAGE.get(Cache.Name.MainPage).getX()+35);
//            projectStage.setY(Cache.STAGE.get(Cache.Name.MainPage).getY()+35);
//            Cache.STAGE.put(Cache.Name.ProjectPage,projectStage);
//            projectStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDbConfigPane() throws IOException {
        Label label = new Label("数据源维护");
        label.setId(Cache.Tab.DbConfigBtn);
        MultiCtrl MultiCtrl= (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);if( !MultiCtrl.selectTabIfExist(label)){
            if( !MultiCtrl.selectTabIfExist(label)) {
                try {


                    Parent parent = super.loadFXML(DataBaseConfigureCtrl.class);

                    MultiCtrl.addPane(label, parent);

                } catch ( Exception e) {
                    e.printStackTrace();
                }

            }


        }
    }

    /**
     * 打开补丁管理界面
     */
    public void openPatchPage() throws IOException {


        if(Cache.STAGE.get(Cache.Name.PatchPage)!=null){
            Cache.STAGE.get(Cache.Name.PatchPage).show();
            return;
        }
//        Stage patchStage=new Stage();
        Label label = new Label("补丁维护");
        label.setId(Cache.Tab.Patch);
        MultiCtrl MultiCtrl= (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);
        if(!MultiCtrl.selectTabIfExist(label)){
            Parent parent=super.loadFXML(PatchCtrl.class);
            MultiCtrl.addPane(label,parent);
        }

//        UIUtil.initStage(patchStage,UIUtil.Title.Patch,parent);
//        patchStage.setX(Cache.STAGE.get(Cache.Name.MainPage).getX()+40);
//        patchStage.setY(Cache.STAGE.get(Cache.Name.MainPage).getY()+40);
//        Cache.STAGE.put(Cache.Name.PatchPage,patchStage);
//        patchStage.show();
    }

    public void openDemandPage() {
        //若缓存中存在则直接调用缓存
        if(Cache.STAGE.get(Cache.Name.DemandPage)!=null){
            Cache.STAGE.get(Cache.Name.DemandPage).show();
            return;
        }
       try {
//            Stage demandStage=new Stage();
          Label label=  new Label("需求维护");
            label.setId(Cache.Tab.Demand);
            MultiCtrl MultiCtrl= (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);
            if(!MultiCtrl.selectTabIfExist(label)){
                Parent parent=super.loadFXML(DemandCtrl.class);
                MultiCtrl.addPane(label,parent);
            }

//            UIUtil.initStage(demandStage,UIUtil.Title.Demand,parent);
//            demandStage.setX(Cache.STAGE.get(Cache.Name.MainPage).getX()+30);
//            demandStage.setY(Cache.STAGE.get(Cache.Name.MainPage).getY()+30);
//            Cache.STAGE.put(Cache.Name.DemandPage,demandStage);
//            demandStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Pane getPane() {
        return mainPane;
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

}
