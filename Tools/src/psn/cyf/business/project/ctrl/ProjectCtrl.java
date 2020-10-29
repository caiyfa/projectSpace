package psn.cyf.business.project.ctrl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;



import psn.base.fxml.FxmlAnchor;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.database.impl.BaseDAO;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.project.vo.ProjectVO;
import psn.cyf.cache.Cache;
import psn.cyf.exception.RunException;
import psn.cyf.main.MultiCtrl;
import psn.cyf.utils.FontawesomeWithJavaFX;


import java.io.IOException;
import java.lang.Override;
import java.lang.System;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
public class ProjectCtrl  extends SuperCtrl {

    @FXML
    public AnchorPane anchorPane;

    /**
     * 项目视图
     */
    @FXML
    public TreeView projectView;

    /**
     * 删除项目
     */
    @FXML
    public Button deleteProjectBtn;
    /**
     * 项目版本
     */
    @FXML
    public TreeView patchVersion;

    /**
     * 项目维护
     */
    public Button maintainProjectBtn;

    public Button addBtn;


    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{maintainProjectBtn,deleteProjectBtn,addBtn,patchVersion};
    }

    @Override
    protected Pane getPane() {
        return anchorPane;
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
        return ProjectVO.class;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
           super.initialize(location,resources);

//        Menu project =new Menu("项目维护");
            //构造树
            makeProjectTreeDate();
            Cache.CONTROLLER.put(Cache.Name.PojectCtrl,this);
        } catch (RunException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建项目结构
     * @throws RunException
     */
    public void makeProjectTreeDate() throws RunException {
        /*ImageView folderIcon=new ImageView();
        folderIcon.setFitHeight(16);
        folderIcon.setFitWidth(16);
        folderIcon.setImage(UIUtil.getImage(ResourceAnchor.folder));*/
        ProjectVO rootProject=new ProjectVO();
        rootProject.setName("所有项目");
        rootProject.setCode("root");
        TreeItem<ProjectVO> root=new TreeItem<>(rootProject);
        root.setGraphic(getIcon());
        root.setExpanded(true);
        List<ProjectVO> projectVOList = (List<ProjectVO>) BaseDAO.getInstance().queryAllVO(ProjectVO.class);
        if(projectVOList!=null){
            for(int i=0;i<projectVOList.size();i++){
                TreeItem<ProjectVO> item=new TreeItem<>(projectVOList.get(i));
                item.setGraphic(getIcon());
                root.getChildren().add(item);
            }
        }
        //暂时不启用缓存
//        MapCache.ProjectCache.put(CacheNAMEConfig.VO_PROJECTVO,projectVOList);
        // MapCache.VOCache.get(CacheNAMEConfig.VO_PROJECTVO).get(0);
        /*for(int i = 0;i < 5;i++){
            TreeItem<ProjectVO> item = new TreeItem<>(new ProjectVO("节点",""+i));
            for(int j=0;j<2;j++){
                TreeItem<ProjectVO> item2 = new TreeItem<>(new ProjectVO("子节点",""+j));
                item.getChildren().add(item2);
            }
            root.getChildren().add(item);
        }*/
        projectView.setRoot(root);
    }
    private Node getIcon(){

        Label label= FontawesomeWithJavaFX.createGlyphLabel("folder_alt",16);

        return label;
    }

    public void deleteProjectAction() throws RunException {
        TreeItem item= (TreeItem) projectView.getSelectionModel().getSelectedItem();
        ProjectVO projectVO= (ProjectVO) item.getValue();
        List treeItems= projectView.getRoot().getChildren();
        treeItems.remove(item);
        projectView.getRoot().getChildren().remove(item);
        BaseDAO.getInstance().deleteVOByPK(projectVO.getClass(),projectVO.getPrimaryKey());


    }

    public void openProjectEditPage(){
        try {
            showEditStage(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditStage(boolean needData) throws IOException {

        Label label=new Label("项目维护");
        label.setId(psn.base.cache.Cache.Tab.ProjectEdit);
        MultiCtrl multiPaneCtrl= (MultiCtrl)  Cache.CONTROLLER.get( Cache.Name.MultiCtrl);
        if(!multiPaneCtrl.selectTabIfExist(label)){
            Parent parent= super.loadFXML(ProjectEditCtrl.class);
            multiPaneCtrl.addPane(label,parent);
        }
        if(needData){
//                Cache.STAGE.get(Cache.Name.ProjectEditPage).show();
            sendDataToProjectEditPage();
            return;
        }




    }

    public void addNewProject(){
        try {
            showEditStage(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送信息到项目维护页面
     */
    private void sendDataToProjectEditPage() {
        ProjectEditCtrl projectEditCtrl= (ProjectEditCtrl) Cache.CONTROLLER.get(Cache.Name.ProjectEditCtrl);
        if(projectEditCtrl==null)return;
        TreeItem  item= (TreeItem) projectView.getSelectionModel().getSelectedItem();
        if (item==null)return;
        ProjectVO projectVO= (ProjectVO) item.getValue();
        projectEditCtrl.name.setText(projectVO.getName());
        projectEditCtrl.code.setText(projectVO.getCode());
        projectEditCtrl.birthday.setText(projectVO.getBirthday());
        projectEditCtrl.lastEditDate.setText(projectVO.getLastEditDate());
        projectEditCtrl.primaryKey.setText(projectVO.getPrimaryKey());
    }

    public void selectFile(){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("选择文件");

        fileChooser.showOpenDialog(Cache.STAGE.get(Cache.Name.PatchPage));
        System.out.println(fileChooser.getInitialFileName());
    }
    public void selectProject(){
        TreeItem item= (TreeItem) projectView.getSelectionModel().getSelectedItem();
        if(item!=null)
            System.out.println(((ProjectVO)item.getValue()).getName());
    }
    public  void selectMenu(){
        System.out.println( "menu");
    }

}

