package psn.cyf.business.datasource.ctrl;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.database.connection.DBConnection;
import psn.cyf.base.database.impl.BaseDAO;
import psn.cyf.base.database.vo.DataBaseVO;
import psn.cyf.base.ui.dialog.MessageDialog;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.exception.RunException;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class DataBaseConfigureCtrl extends SuperCtrl {
    public AnchorPane pane;
     public TextField sourceName;
    public TextField    ip;
    public TextField    port;
    public TextField    user;
    public TextField    password;
    public TextField pk_dataBase;
    public TextField instance;
    public Button addBtn;
    public Button saveBtn;
    public Button testBtn;
    public Button delBtn;
    public Button editBtn;
    public Button cancelBtn;
    public ComboBox dbComBo;

    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{cancelBtn,addBtn, saveBtn, testBtn, delBtn, editBtn, dbComBo};
    }

    @Override
    protected Pane getPane() {
        return pane;
    }

    @Override
    protected Button[] getEditStateButton() {
        return new Button[]{saveBtn,cancelBtn};
    }

    @Override
    protected Button[] getNormalStateButton() {
        return new Button[]{addBtn,editBtn,testBtn,delBtn};
    }

    @Override
    protected TextInputControl[] getTextInputCtrl() {
        return new TextInputControl[]{ip,port,user,password,sourceName,pk_dataBase,instance};
    }

    @Override
    protected Class<? extends BaseVO> getBaseClass() {
        return DataBaseVO.class;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);

        sourceName.setVisible(false);
        pk_dataBase.setVisible(false);
        super.turnToNormalState();
        doRefresh(null);

    }
    private void doRefresh(DataBaseVO selectedVO){
        try {
            List<DataBaseVO> dataBaseVOList= (List<DataBaseVO>) BaseDAO.getInstance().queryAllVO(DataBaseVO.class);
            dbComBo.getItems().clear();
            if(dataBaseVOList!=null&&dataBaseVOList.size()!=0){
                dbComBo.getItems().addAll(dataBaseVOList);
                if(selectedVO!=null){
                    for(DataBaseVO baseVO:dataBaseVOList){
                        if(selectedVO.getPk_dataBase().equals(baseVO.getPk_dataBase())){
                            dbComBo.setValue(baseVO);//设置默认值
                        }
                    }
                }else {
                    dbComBo.setValue(dataBaseVOList.get(0));//设置默认值
                }

            }
            onDbComboSelected();
        } catch (RunException e) {
            (new MessageDialog(e.getMessage())).show();
        }
    }
    public void onBtnAdd(){
        super.turnToEditState();
        for(TextInputControl control:getTextInputCtrl() ){
            control.setText("");
        }
        changeDbNameToEditState(true);

    }
    public void onBtnEdit(){
        super.turnToEditState();
        changeDbNameToEditState(true);
    }
    public void onBtnSave(){

        DataBaseVO baseVO= (DataBaseVO) getCacheVO();

        try {
            //验重
            boolean isInsert=true;
            List<DataBaseVO> dataBaseVOList= (List<DataBaseVO>) BaseDAO.getInstance().queryAllVO(DataBaseVO.class);
            if(dataBaseVOList!=null){
                for(DataBaseVO dataBaseVO:dataBaseVOList){
                    if(dataBaseVO.getSourceName().equals(baseVO.getSourceName())){
                        if(baseVO.getPk_dataBase()==null||baseVO.getPk_dataBase().trim().length()==0){
                            throw new RunException("数据源名重复");
                        }else {
                            isInsert=false;
                        }

                    }
                }
            }
            //数据固化
            if(isInsert){
                baseVO= (DataBaseVO) BaseDAO.getInstance().insertVO(baseVO);
                super.showMessage("数据源已添加。");
            }else{
                BaseDAO.getInstance().updateVO(baseVO);
                super.showMessage("数据源已更新。");
            }


            doRefresh(baseVO);


            //将页面状态改为非编辑态
            super.turnToNormalState();
            changeDbNameToEditState(false);
        } catch (RunException e) {
           super.showMessage(e.getMessage());
        }
    }
    public void onBtnTest(){
        DataBaseVO cacheVO= (DataBaseVO) getCacheVO();
        try {
            DBConnection.getInstance().testConnection(cacheVO);
            super.showMessage("测试通过");
        } catch (RunException e) {
            super.showMessage(e.getMessage());
        }

    }
    public void onBtnDelete(){
        DataBaseVO projectVO= (DataBaseVO) this.dbComBo.getSelectionModel().getSelectedItem();
        try {
            BaseDAO.getInstance().deleteVOByPK(DataBaseVO.class,projectVO.getPk_dataBase());
            doRefresh(null);
        } catch (RunException e) {
            super.showMessage(e.getMessage());
        }
    }
    public void onBtnCancel(){
    super.turnToNormalState();
        changeDbNameToEditState(false);
        onDbComboSelected();
    }
    private void changeDbNameToEditState(boolean isEdit){
        dbComBo.setVisible(!isEdit);
        sourceName.setVisible(isEdit);
    }
    public void onDbComboSelected(){
        DataBaseVO dataBaseVO= (DataBaseVO) this.dbComBo.getSelectionModel().getSelectedItem();
        if(dataBaseVO==null){
            super.clear();
            editBtn.setDisable(true);
            return;
        }
         super.setCacheVO(dataBaseVO);
    }


}
