package psn.cyf.business.java.project.ctrl;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.filebase.BaseDAO;
import psn.cyf.base.ui.dialog.BooleanDialog;
import psn.cyf.base.ui.dialog.MessageDialog;
import psn.cyf.base.ui.dialog.TextInputDialog;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.java.project.vo.ProjectSpaceVO;
import psn.cyf.cache.Cache;
import psn.cyf.exception.RunException;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * 该控制器主要实现对于Java工程的优化
 * TODO 对Eclipse 的工作空间SourceFolder进行排序
 */
public class ProjectSpaceCtrl extends SuperCtrl {

    public AnchorPane anchorPane;

    public ListView<ProjectSpaceVO> directoryView;

    public ListView<ProjectSpaceVO> selectedDirectoryView;

    public Button addBtn;
    public Button formatBtn;
    public Button removeBtn;
    public Button selectOneBtn;
    public Button unSelectedAllBtn;
    public Button unSelectedOneBtn;
    public Button selectAllBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        refresh();
    }

    public void addAction() {
      /*  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开项目目录");
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.showOpenDialog(Cache.STAGE.get(Cache.Name.WholePage));*/
      //生成新的VO
        ProjectSpaceVO projectSpaceVO = getProjectSpaceVO();
        try {
            BaseDAO.getInstance().insertVO(projectSpaceVO);
            refresh();
        } catch (RunException e) {
            super.showMessage(e.getMessage());
        }

    }

    private ProjectSpaceVO getProjectSpaceVO() {
        //获取目录节点
        DirectoryChooser directoryChooser=new DirectoryChooser();
        directoryChooser.setTitle("打开项目目录");
        File dir=directoryChooser.showDialog(Cache.STAGE.get(Cache.Name.WholePage));

        TextInputDialog dialog=new TextInputDialog("项目名称");
        dialog.showAndWait();
        String projectName=dialog.getData();
        ProjectSpaceVO projectSpaceVO=new ProjectSpaceVO();
        projectSpaceVO.setProjectName(projectName);
        projectSpaceVO.setProjectPath(dir.getPath());
        return projectSpaceVO;
    }

    void refresh(){
       try {
           //查询所有数据
          List<ProjectSpaceVO> spaceVOList= (List<ProjectSpaceVO>) BaseDAO.getInstance().queryAllVO(ProjectSpaceVO.class);
           System.out.println(spaceVOList.size());
           directoryView.getItems().clear();
           directoryView.getItems().addAll(spaceVOList);
       } catch (RunException e) {
           super.showMessage(e.getMessage());
       }
   }

    public void removeAction() {
        if(selectedDirectoryView.getItems().size()==0){
            MessageDialog message=new MessageDialog("请选择项目");
            message.show();
            return;
        }

        BooleanDialog dialog=new BooleanDialog("是否移除？");

        dialog.showAndWait();
        Boolean isContinue=dialog.getDialogResult();
        if(isContinue){
            List<ProjectSpaceVO> spaceVOList=  selectedDirectoryView.getItems();
            try {
                BaseDAO.getInstance().deleteVOS(spaceVOList);
                selectedDirectoryView.getItems().clear();

            } catch (RunException e) {
                super.showMessage(e.getMessage());
            }
        }
        refresh();
    }

    public void formatAction() {
        List<ProjectSpaceVO> spaceVOS=selectedDirectoryView.getItems();
        for(ProjectSpaceVO spaceVO:spaceVOS){
            String filePath=spaceVO.getProjectPath()+File.separator+".classpath";
            System.out.println(filePath);
        }


    }

    public void selectOneAction() {
      ProjectSpaceVO spaceVO=  directoryView.getSelectionModel().getSelectedItem();
        if(spaceVO==null){
            return;
        }
      directoryView.getItems().remove(spaceVO);
      selectedDirectoryView.getItems().add(spaceVO);

    }

    public void selectAllAction() {
      List<ProjectSpaceVO> spaceVOS=  directoryView.getItems();
      selectedDirectoryView.getItems().addAll(spaceVOS);
        directoryView.getItems().clear();
    }

    public void unSelectedAllAction() {
        List<ProjectSpaceVO> spaceVOS=  selectedDirectoryView.getItems();
        directoryView.getItems().addAll(spaceVOS);
        selectedDirectoryView.getItems().clear();
    }

    public void unSelectedOneAction() {
        ProjectSpaceVO spaceVO=  selectedDirectoryView.getSelectionModel().getSelectedItem();
        if(spaceVO==null){
            return;
        }
        selectedDirectoryView.getItems().remove(spaceVO);
        directoryView.getItems().add(spaceVO);
    }

    @Override
    protected Button[] getEditStateButton() {
        return new Button[0];
    }

    @Override
    protected Button[] getNormalStateButton() {
        return new Button[]{addBtn, formatBtn, removeBtn, selectOneBtn, unSelectedAllBtn, unSelectedOneBtn, selectAllBtn};
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
    protected Node[] getDecorateArr() {
        return new Node[]{directoryView, selectedDirectoryView, addBtn, formatBtn, removeBtn, selectOneBtn, unSelectedAllBtn, unSelectedOneBtn, selectAllBtn};
    }

    @Override
    protected Pane getPane() {
        return anchorPane;
    }
}
