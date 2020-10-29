package psn.cyf.business.patch.manage.ctrl;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.database.impl.BaseDAO;
import psn.cyf.base.ui.PatchRightMenu;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.demand.vo.DemandVO;
import psn.cyf.business.patch.manage.vo.PatchVO;
import psn.cyf.business.project.vo.ProjectVO;
import psn.cyf.cache.Cache;
import psn.cyf.exception.RunException;
import psn.cyf.utils.FileUtil;
import psn.cyf.utils.FontawesomeWithJavaFX;
import psn.cyf.utils.VOHelper;


import java.io.File;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.System;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PatchCtrl extends SuperCtrl {
    @FXML
    public AnchorPane patchPane;
    @FXML
    public ComboBox projectCom;
    @FXML
    public ComboBox demandCom;

//    public VBox patchArea;
//    public Group group;
//    public Label tipLable;

    /**
     * 补丁展示
     */
    public TreeView patchView;


    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{demandCom,projectCom};
    }

    @Override
    protected Pane getPane() {
        return patchPane;
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
        return PatchVO.class;
    }

    @Override
    public   void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);
        List<ProjectVO> projectVOList = null;
        try {
            projectVOList = (List<ProjectVO>) BaseDAO.getInstance().queryAllVO(ProjectVO.class);
            if(projectVOList!=null&&projectVOList.size()!=0){
                projectCom.getItems().addAll(projectVOList);
                projectCom.setValue(projectVOList.get(0));//设置默认值
                selectProject();
            }
        } catch (RunException e) {
            e.printStackTrace();
        }

            Cache.CONTROLLER.put(Cache.Name.PatchCtrl,this);
    }


    public void selectProject() throws RunException {
        //清除痕迹的时候不应加入限制条件
        List item=demandCom.getItems();
        demandCom.getItems().remove(0,item.size());
      List<DemandVO> demandVOList= (List<DemandVO>) BaseDAO.getInstance().queryAllVO(DemandVO.class);
        ProjectVO projectVO= (ProjectVO) projectCom.getSelectionModel().getSelectedItem();
        if(projectVO==null){
            return;
        }
        List<DemandVO> showDemandVOList=new ArrayList<>();
        for(DemandVO demandVO:demandVOList){
            if(projectVO.getPrimaryKey().equals(demandVO.getPk_project())){
                showDemandVOList.add(demandVO);
            }
        }
        Collections.sort(showDemandVOList, new Comparator<DemandVO>() {
            @Override
            public int compare(DemandVO o1, DemandVO o2) {
                return o1.getCreateTime().compareTo(o2.getCreateTime());
            }
        });
      if(showDemandVOList!=null&&showDemandVOList.size()!=0){

          demandCom.getItems().addAll(showDemandVOList);
          demandCom.setValue(showDemandVOList.get(0));
      }
     Object o=   demandCom.getSelectionModel().getSelectedItem();
        updatePatchView();
    }
    public void selectDemand() throws RunException {
        updatePatchView();
    }
    //拖入文件至界面上方。这时可以对文件进行校验
    public void dragPatchFileOver(DragEvent dragEvent){
        Dragboard dragboard=dragEvent.getDragboard();
        if(dragboard.hasFiles()){
            List<File> fileList = dragboard.getFiles();
            boolean flag=true;
            for(File file:fileList){
                if (!file.getAbsolutePath().endsWith(".zip")) { //用来过滤拖入类型
                    flag=false;

                }
            }
        if(fileList.size()!=1){
            flag=false;
//            Alert alert=new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("每次只允许插入一条数据");
//            alert.showAndWait();
        }
            if(flag){
                dragEvent.acceptTransferModes(TransferMode.COPY);//接受拖入文件
            }
        }

    }
    //在dragAttachementOver 方法中校验通过之
    // 后松开鼠标即可上传文件 ，
    // 上传动作由该方法执行
    public void dragPatchFileDropped(DragEvent dragEvent) throws RunException {
        // get drag enter file
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragEvent.isAccepted()) {
            List<File> fileList = dragboard.getFiles(); //获取拖入的文件
            if(fileList==null||fileList.size()==0){
                return;
            }
            for(File file:fileList){
                System.out.println(""+file.getPath());
            }
            //限制同时上传补丁文件不超过1
            if(fileList.size()!=1){
                            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("每次只允许上传一个补丁文件");
            alert.showAndWait();
                return;
            }
            //插入数据
            String pk_patch= VOHelper.getUUID();
            String uploadPath= FileUtil.uploadFile(fileList.get(0),pk_patch);
            PatchVO patchVO=new PatchVO();
            patchVO.setPath(uploadPath);
            patchVO.setPk_patch(pk_patch);
            patchVO.setName(fileList.get(0).getName());
        ProjectVO projectVO= (ProjectVO) projectCom.getSelectionModel().getSelectedItem();
            patchVO.setPk_project(projectVO.getPrimaryKey());
            DemandVO demandVO= (DemandVO) demandCom.getSelectionModel().getSelectedItem();
            patchVO.setPk_demand(demandVO.getPrimaryKey());
            patchVO.setVersion((new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())));
            BaseDAO.getInstance().insertVO(patchVO);
            updatePatchView();
        }

    }
    public void updatePatchView() throws RunException {
        ProjectVO projectVO= (ProjectVO) projectCom.getSelectionModel().getSelectedItem();
        DemandVO demandVO= (DemandVO) demandCom.getSelectionModel().getSelectedItem();
        if(demandVO==null||projectVO==null){
            patchView.setRoot(null);
            return;
        }
        List<PatchVO> patchVOList= (List<PatchVO>) BaseDAO.getInstance().queryAllVO(PatchVO.class);
        List<PatchVO> showList=new ArrayList<>();
        for(PatchVO patchVO:patchVOList){
            if(demandVO.getPrimaryKey().equals(patchVO.getPk_demand())){
                showList.add(patchVO);
            }
        }
        Collections.sort(showList, new Comparator<PatchVO>() {
            @Override
            public int compare(PatchVO o1, PatchVO o2) {
                return o1.getVersion().compareTo(o2.getVersion());
            }
        });
//

        PatchVO rootPatch=new PatchVO();
        rootPatch.setName(projectVO.getName()+"->"+demandVO.getDemandName()+"补丁列表");
        rootPatch.setVersion("");
        TreeItem<PatchVO> root=new TreeItem<>(rootPatch);
        root.setGraphic(getIcon(null));
        root.setExpanded(true);
        for(PatchVO patchVO:showList){
            TreeItem<PatchVO> treeItem=new TreeItem<>(patchVO);
            treeItem.setGraphic(getIcon(patchVO.getPrimaryKey()));
            root.getChildren().add(treeItem);
        }
        patchView.setRoot(root);
    }
    private Node getIcon(String id){
        /*ImageView folderIcon=new ImageView();
        folderIcon.setFitHeight(16);
        folderIcon.setFitWidth(16);
        folderIcon.setImage(UIUtil.getImage(ResourceAnchor.folder));
        folderIcon.setId(id);*/
        Label label= FontawesomeWithJavaFX.createGlyphLabel("folder_alt",20);
        label.setId(id);
        return label;
    }

//    public void dragPatchFile(DragEvent event){
//        Dragboard dragboard=event.getDragboard();
//        System.out.println(dragboard.getFiles().get(0).getPath());
//    }

    public  void  selectPatchItem(MouseEvent event){
        MouseButton mouseButton=event.getButton();
       /* switch (mouseButton){
            case PRIMARY:{
                System.out.println("鼠标左键");
                break;
            }
            case MIDDLE:{
                System.out.println("滚轮");
                break;
            }
            case SECONDARY:{
                System.out.println("鼠标右键");
                break;
            }
        }*/
        if(MouseButton.SECONDARY.equals(mouseButton)){
//            System.out.println(event.toString());
            TreeItem item= (TreeItem) patchView.getSelectionModel().getSelectedItem();
            if(item!=null)
                PatchRightMenu.getInstance(item.getGraphic().getId()).show(item.getGraphic(), Side.BOTTOM,80,0);
        }

    }
}
