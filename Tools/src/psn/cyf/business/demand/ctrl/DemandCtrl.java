package psn.cyf.business.demand.ctrl;

import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;



import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.database.impl.BaseDAO;
import psn.cyf.base.ui.AttachementRightMenu;
import psn.cyf.base.ui.UIState;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.demand.vo.DemandVO;
import psn.cyf.business.project.vo.ProjectVO;
import psn.cyf.cache.Cache;
import psn.cyf.exception.RunException;
import psn.cyf.img.ResourceAnchor;
import psn.cyf.utils.FileUtil;
import psn.cyf.utils.FontawesomeWithJavaFX;
import psn.cyf.utils.UIUtil;
import psn.cyf.utils.VOHelper;


import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class DemandCtrl extends SuperCtrl {
    public AnchorPane pane;
    public ComboBox projectCom;
    public TreeView demandTree;
    public VBox attachmentBox;
    public TextField demandName;
    public TextField createTime;
    public TextArea describe;
    public Button addBtn;
    public Button editBtn;
    public Button deleteBtn;
    public Button saveBtn;
    public Button refreshBtn;
    public TextField pk_demand;
    public ScrollBar scrollBar;


    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{demandTree,addBtn,editBtn,deleteBtn,saveBtn,refreshBtn,demandName,createTime,describe
        ,pk_demand,};
    }

    @Override
    protected Pane getPane() {
        return pane;
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
        return DemandVO.class;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);
        //设置样式
        //在滚动条移动时移动vBox   滚动条的灵魂
        pane.setBackground(UIUtil.getBackground(ResourceAnchor.bacImg));
        scrollBar.valueProperty().addListener((ov, old_val, new_val) -> {
            attachmentBox.setLayoutY(-new_val.doubleValue());
        });
        scrollBar.setMin(0);
        attachmentBox.setLayoutX(5);
        attachmentBox.setSpacing(10);
        attachmentBox.layoutYProperty();

        saveBtn.setDisable(true);
        refreshBtn.setGraphic( FontawesomeWithJavaFX.createGlyphLabel("refresh",16));
        this.createTime.setDisable(true);
        doRefresh();

    Cache.CONTROLLER.put(Cache.Name.DemandCtrl,this);
    }
    public  void doRefresh(){
        try {

                 projectCom.getItems().clear();

            long t1=System.currentTimeMillis();
            List<ProjectVO> projectVOList = (List<ProjectVO>) BaseDAO.getInstance().queryAllVO(ProjectVO.class);
            long t2=System.currentTimeMillis();
            System.out.println("加载项目耗时:"+(t2-t1)+"ms");
            if(projectVOList!=null&&projectVOList.size()!=0){
                projectCom.getItems().addAll(projectVOList);
                projectCom.setValue(projectVOList.get(0));//设置默认值
                refreshDemandView();
            }

            long t3=System.currentTimeMillis();
            System.out.println("加载需求明细耗时："+(t3-t2)+"ms");
        } catch (RunException e) {
            e.printStackTrace();
        }
    }
    public void selectProject() throws RunException {
        ProjectVO projectVO= (ProjectVO) projectCom.getSelectionModel().getSelectedItem();
        refreshDemandView();
    }

    /**
     * 选中需求列表
     */
    public void selectItem() throws RunException {
        refreshDetail();
    }

    /**
     * 刷新明细部分
     */
    public void refreshDetail() throws RunException {
        TreeItem<DemandVO> treeItem= (TreeItem<DemandVO>) this.demandTree.getSelectionModel().getSelectedItem();
        if(treeItem!=null&&treeItem.getValue().getPrimaryKey()!=null&&treeItem.getValue().getPrimaryKey().trim().length()!=0){
            this.demandName.setText(treeItem.getValue().getDemandName());
            this.describe.setText(treeItem.getValue().getDescribe());
            this.createTime.setText(treeItem.getValue().getCreateTime());
            this.pk_demand.setText(treeItem.getValue().getPk_demand());
            showFileBtn(treeItem.getValue().getPk_demand());
        }else{
            this.pk_demand.setText("");
            this.demandName.setText("");
            this.describe.setText("");
            this.createTime.setText("");
        }

    }

    public void dragAttachement(DragEvent dragEvent){
        Dragboard db=dragEvent.getDragboard();
        System.out.println(db.getFiles().toString());
    }
    public void refreshDemandView()throws RunException{
        ProjectVO projectVO= (ProjectVO) this.projectCom.getSelectionModel().getSelectedItem();
        if(projectVO==null){
            return;
        }
        List<DemandVO> demandVOList= (List<DemandVO>) BaseDAO.getInstance().queryAllVO(DemandVO.class);
        List<DemandVO> showDemandList=new ArrayList<>();
        for(DemandVO demandVO:demandVOList){
            if(demandVO.getPk_project().equals(projectVO.getPrimaryKey())){
                showDemandList.add(demandVO);
            }
        }
        Collections.sort(showDemandList, new Comparator<DemandVO>() {
            @Override
            public int compare(DemandVO o1, DemandVO o2) {
                return o1.getCreateTime().compareTo(o2.getCreateTime());
            }
        });
        /*ImageView folderIcon=new ImageView();
        folderIcon.setFitHeight(16);
        folderIcon.setFitWidth(16);
        folderIcon.setImage(UIUtil.getImage(ResourceAnchor.folder));*/
        DemandVO rootDemand=new DemandVO();
        rootDemand.setDemandName(projectVO.getName()+"的需求列表");
        TreeItem<DemandVO> root=new TreeItem<>(rootDemand);
        root.setGraphic(getIcon());
        root.setExpanded(true);
        for(DemandVO demandVO:showDemandList){
            TreeItem<DemandVO> treeItem=new TreeItem<>(demandVO);
            treeItem.setGraphic(getIcon());
            root.getChildren().add(treeItem);
        }
        demandTree.setRoot(root);
        refreshDetail();
    }
    private Node getIcon(){

        Label label= FontawesomeWithJavaFX.createGlyphLabel("folder_alt",16);

        return label;
    }
    public void doSave() throws RunException {
      String demandName=  this.demandName.getText();
        String createTime=this.createTime.getText();

        String describe=this.describe.getText();
        String pk_demand=this.pk_demand.getText();
        DemandVO demandVO=new DemandVO();
        demandVO.setDescribe(describe);
        demandVO.setCreateTime(createTime);
        demandVO.setDemandName(demandName);
        demandVO.setPk_demand(pk_demand);
        ProjectVO projectVO= (ProjectVO) projectCom.getSelectionModel().getSelectedItem();
        demandVO.setPk_project(projectVO.getPk_project());
//        System.out.println(demandVO.toString());
        if(pk_demand==null||pk_demand.trim().length()==0){
            BaseDAO.getInstance().insertVO(demandVO);
        }else {
            BaseDAO.getInstance().updateVO(demandVO);

        }


        changeUIStage(UIState.nomal);
//        this.demandName.setFont(Font.font("Verdana", 20));
//        this.demandName.setF
        refreshDemandView();
    }
    public void doDelete() throws RunException {
        TreeItem<DemandVO> treeItem= (TreeItem<DemandVO>) this.demandTree.getSelectionModel().getSelectedItem();
        if(treeItem==null)return;
        BaseDAO.getInstance().deleteVOByPK(DemandVO.class,treeItem.getValue().getPrimaryKey());
        refreshDemandView();
    }
    public void doEdit() throws RunException {
        changeUIStage(UIState.edit);
        refreshDetail();
    }
    public void doAdd(){

        changeUIStage(UIState.add);
    }
    public void changeUIStage(UIState state){
        switch (state){
            case add:{
                changeToEditState(true);
//                changeBtnStatus(false);
                this.createTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                this.demandName.setText("");
                this.describe.setText("");
                break;

            }
            case edit:{
                changeToEditState(true);
//                changeBtnStatus(false);
                break;
            }
            case nomal:{
                changeToEditState(false);
//                changeBtnStatus(true);
                break;
            }
        }
    }

    public void changeToEditState(boolean flag) {

        this.saveBtn.setDisable(flag?false:true);

        this.deleteBtn.setDisable(flag?true:false);
        this.addBtn.setDisable(flag?true:false);
        this.editBtn.setDisable(flag?true:false);



//        this.createTime.setDisable(flag?false:true);
//        this.demandName.setDisable(flag?false:true);
//        this.describe.setDisable(flag?false:true);
//        this.attachmentBox.setDisable(flag?false:true);
    }

    public void changeBtnStatus(boolean flag) {
        this.saveBtn.setDisable(flag?true:false);

        this.deleteBtn.setDisable(flag?false:true);
        this.addBtn.setDisable(flag?false:true);
        this.editBtn.setDisable(flag?false:true);
    }


    public void dragAttachementOver(DragEvent event){
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            List<File> fileList = dragboard.getFiles();
            boolean flag=true;
           /* for(File file:fileList){
                if (!file.getAbsolutePath().endsWith(".txt")) { //用来过滤拖入类型
                    flag=false;

                }
            }*/
            if(flag){
                event.acceptTransferModes(TransferMode.COPY);//接受拖入文件
            }

        }
    }public void dragAttachementDrop(DragEvent event) throws RunException {
        // get drag enter file
        Dragboard dragboard = event.getDragboard();
        if (event.isAccepted()) {
            List<File> fileList = dragboard.getFiles(); //获取拖入的文件
            if(fileList==null||fileList.size()==0){
                return;
            }
            for(File file:fileList){
                System.out.println(file.getPath());
            }

        }
       //新增时准备需求主键
        if(this.pk_demand.getText()==null||this.pk_demand.getText().trim().length()==0){
           this.pk_demand.setText(VOHelper.getUUID());
       }

//        Button btn=new Button(dragboard.getFiles().get(0).getName());

            putFileBtnToVBox(dragboard.getFiles());

    }

    public void putFileBtnToVBox(List<File> fileList) throws RunException {
        String pk_demand=this.pk_demand.getText();
        List<Button> filebuttons=new ArrayList<>();
        for(File file:fileList){
            String pk_demAtt= VOHelper.getUUID();
            //构建上传附件对象
            DemandAttachementVO demandAttachementVO=new DemandAttachementVO();
            demandAttachementVO.setPk_attachement(pk_demAtt);
            demandAttachementVO.setPk_demand(pk_demand);
            demandAttachementVO.setUploadTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            demandAttachementVO.setAttachementName(file.getName());
            demandAttachementVO.setSourceFilePath(file.getAbsolutePath());
            demandAttachementVO.setFileSuffix(file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."),file.getAbsolutePath().length()));
            //上传文件
          String truePath=  FileUtil.uploadFile(file,pk_demAtt);
          demandAttachementVO.setTruePath(truePath);
            BaseDAO.getInstance().insertVO(demandAttachementVO);

            //创建显示的文件对应按钮
            createFilesBtn(filebuttons, file.getName(), pk_demAtt);

        }

        HBox hBox=new HBox();

        hBox.getChildren().addAll(filebuttons);
        this.attachmentBox.getChildren().add(hBox);
    }
    public void showFileBtn(String pk_demand) throws RunException {
        List<DemandAttachementVO> tmpVOList= (List<DemandAttachementVO>) BaseDAO.getInstance().queryAllVO(DemandAttachementVO.class);
        //清除已有按钮  这步操作需要在判断是否为空之前操作。要不然会导致删掉最后一个元素之后不刷新。
        this.attachmentBox.getChildren().removeAll(this.attachmentBox.getChildren());
        if(tmpVOList==null||tmpVOList.size()==0){
            return;
        }
        //筛选数据
        List<DemandAttachementVO> currDemandAttachementVOList=new ArrayList<>();
        for(DemandAttachementVO attachementVO:tmpVOList){
            if(attachementVO.getPk_demand().equals(pk_demand)){
                currDemandAttachementVOList.add(attachementVO);
            }
        }
        //根据上传时间排序
        Collections.sort(currDemandAttachementVOList, new Comparator<DemandAttachementVO>() {
            @Override
            public int compare(DemandAttachementVO o1, DemandAttachementVO o2) {
                return o1.getUploadTime().compareTo(o2.getUploadTime());
            }
        });
        //制作显示按钮
        List<Button> showBtnList=new ArrayList<>();
        for(DemandAttachementVO attachementVO:currDemandAttachementVOList){
            createFilesBtn(showBtnList,attachementVO.getAttachementName(),attachementVO.getPk_attachement());
        }

        //按两列加载按钮
        HBox hBox=null;
        for(int i=0 ,size=showBtnList.size();i<size;i++){
            //一行存两个
            if((i%2)==0){
                hBox=new HBox();
                hBox.getChildren().add(showBtnList.get(i));
                this.attachmentBox.getChildren().add(hBox);
            }else{
                hBox.getChildren().add(showBtnList.get(i));
            }
        }

    }

    /**
     * @param buttons 显示的按钮
     * @param fileName 显示的文件名
     * @param pk_demAtt 对应的按钮ID 即主键
     */
    public void createFilesBtn(List<Button> buttons, String fileName, String pk_demAtt) {
        Button btn=new Button(fileName);

        btn.setStyle(UIUtil.Style.Translucent);
        btn.setId(pk_demAtt);
        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SuperCtrl baseCtrl= new SuperCtrl() {
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
                };
                baseCtrl.btnEnter(event);
            }
        });
        btn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SuperCtrl baseCtrl= new SuperCtrl() {
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
                };
                baseCtrl.btnExit(event);
            }
        });
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(MouseButton.SECONDARY.equals(mouseEvent.getButton()))
                AttachementRightMenu.getInstance(Cache.Name.DemandCtrl,Cache.Name.DemandPage).show(btn, Side.BOTTOM,0,-5);
            }
        });
        buttons.add(btn);

    }


}
