package psn.cyf.business.project.ctrl;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.database.impl.BaseDAO;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.project.vo.ProjectVO;
import psn.cyf.cache.Cache;
import psn.cyf.exception.RunException;
import psn.cyf.img.ResourceAnchor;
import psn.cyf.main.MultiCtrl;
import psn.cyf.utils.StringUtils;
import psn.cyf.utils.UIUtil;


import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class ProjectEditCtrl extends SuperCtrl {
    public AnchorPane  anchorPane;
    public TextField primaryKey;
    public TextField name;
    public TextField code;
    public TextField birthday;
    public TextField lastEditDate;
    public Button saveBtn;
    public Button cancelBtn;

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
        return ProjectVO.class;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //设置样式
            anchorPane.setBackground(UIUtil.getBackground(ResourceAnchor.bacImg));
            saveBtn.setStyle(UIUtil.Style.Translucent);
            cancelBtn.setStyle(UIUtil.Style.Translucent);
            Cache.CONTROLLER.put(Cache.Name.ProjectEditCtrl,this);
        }catch (Exception e){
                e.printStackTrace();
        }

    }
    public void save() throws RunException {
        String pk_project=primaryKey.getText();
        String projectName=name.getText();
        String projectCode=code.getText();
        String makeDate=birthday.getText();
        if(StringUtils.isNullWithTrim(projectName)|| StringUtils.isNullWithTrim(projectCode)){
           Alert msg=new  Alert(Alert.AlertType.WARNING);
           msg.setContentText("名称或编码不可为空");
           return;
        }
//        String lastDate=lastEditDate.getText();
        if(pk_project!=null&&pk_project.trim().length()!=0){
            ProjectVO projectVO =new ProjectVO();
            projectVO.setPk_project(pk_project);
            projectVO.setName(projectName.trim());
            projectVO.setCode(projectCode.trim());
            projectVO.setBirthday(makeDate);
            projectVO.setLastEditDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            BaseDAO.getInstance().updateVO(projectVO);

        }else{
            ProjectVO projectVO =new ProjectVO();
            projectVO.setName(projectName.trim());
            projectVO.setCode(projectCode.trim());
            projectVO.setBirthday(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            projectVO.setLastEditDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            BaseDAO.getInstance().insertVO(projectVO);
        }


        ProjectCtrl projectCtrl= (ProjectCtrl) Cache.CONTROLLER.get(Cache.Name.PojectCtrl);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("添加成功。");
        alert.showAndWait();
        MultiCtrl multiPaneCtrl= (MultiCtrl) Cache.CONTROLLER.get(Cache.Name.MultiCtrl);
        if(projectCtrl==null){
            multiPaneCtrl.closeCurrentTab();
            return;
        }
        projectCtrl.makeProjectTreeDate();
        multiPaneCtrl.closeCurrentTab();

//        Cache.STAGE.get(Cache.Name.ProjectEditPage).close();

    }
    public void cancel(){
//       Stage projectStage= Cache.STAGE.get(Cache.Name.ProjectEditPage);
//       projectStage.close();
//        Cache.STAGE.remove(Cache.Name.ProjectEditPage);

    }
}
