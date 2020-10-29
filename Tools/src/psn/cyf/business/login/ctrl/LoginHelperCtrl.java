package psn.cyf.business.login.ctrl;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.database.impl.BaseDAO;
import psn.cyf.base.database.impl.DataBaseDAO;
import psn.cyf.base.database.vo.DataBaseVO;
import psn.cyf.base.ui.eventhandler.FileDragOverEventHandler;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.login.event.LoginHelperCSVDragDroppedHandler;
import psn.cyf.business.login.vo.ContrastWayVO;
import psn.cyf.business.login.vo.NCOAUserVO;
import psn.cyf.cache.Cache;
import psn.cyf.cache.UICache;
import psn.cyf.exception.RunException;
import psn.cyf.utils.DESKeyUtils;
import psn.cyf.utils.ReadCSV;


import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LoginHelperCtrl extends SuperCtrl {
    public AnchorPane loginPane;
    public ComboBox contrastComBo;
    public ComboBox dbComBo;
    public Button makeURLBtn;
    public TextField oaUserText;
    public TextField ncUserText;
    public TextField ipAndPortText;
    public TextField keyText;
    public TextField ssoURLText;
    public static final String CSVFileName="loginConfig.csv";
    public TextField userNameText;
    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{userNameText,ssoURLText,keyText,ipAndPortText,ncUserText,oaUserText,makeURLBtn,contrastComBo,dbComBo};
    }

    @Override
    protected Pane getPane() {
        return loginPane;
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);
        Cache.CONTROLLER.put(Cache.Name.LoginHelperCtrl,this);
        initContrastCombo( );
        onChangeContrastWay(null);
        //System.out.println(((ComboBox)event.getSource()).getSelectionModel().getSelectedItem().toString());
        loadCacheAndRegisterReleasedAction();

    }

    public void initContrastCombo() {
        //初始化方式框
        List<ContrastWayVO> contrastWayVOList=new ArrayList<>();
        ContrastWayVO db=new ContrastWayVO("DataBase","数据库对照");
        ContrastWayVO self=new ContrastWayVO("self","手工输入");
        ContrastWayVO csv=new ContrastWayVO("CSV","CSV文件对照");
        contrastWayVOList.add(db);
        contrastWayVOList.add(self);
        contrastWayVOList.add(csv);
        contrastComBo.getItems().clear();
        contrastComBo.getItems().addAll(contrastWayVOList);
        contrastComBo.setValue(self);
    }
    private void initDBCombo(){
        try {
            List<DataBaseVO> dataBaseVOList= (List<DataBaseVO>) BaseDAO.getInstance().queryAllVO(DataBaseVO.class);
            if(dataBaseVOList==null||dataBaseVOList.size()==0){
                return;
            }
            dbComBo.getItems().clear();
            dbComBo.getItems().addAll(dataBaseVOList);
            dbComBo.setValue(dataBaseVOList.get(0));
            dbComBo.setVisible(true);
        } catch (RunException e) {
            super.showMessage(e.getMessage());
        }
    }



    public void makeURL(ActionEvent event){
            String oaUser=oaUserText.getText();
            String ncUser=ncUserText.getText();
            //String ipAndPort=ipAndPortText.getText();
        String time=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
            StringBuffer stringBuffer=new StringBuffer(ipAndPortText.getText());
            stringBuffer.append("/portal/registerServlet?oaflag=y&userid={0}&ts={1}");
        oaUser= DESKeyUtils.encode(keyText.getText(),oaUser);
        time= DESKeyUtils.encode(keyText.getText(),time);
        ssoURLText.setText(MessageFormat.format(stringBuffer.toString(),oaUser,time));
         UICache.putValue(this,"ssoURLText",ssoURLText.getText());
        showMessage("已成功生成登录指令");


    }
    public void onChangeDB(){

    }
    public void onChangeContrastWay(ActionEvent event){
        ContrastWayVO selectedContrastWay= (ContrastWayVO) this.contrastComBo.getSelectionModel().getSelectedItem();
       // System.out.println(selectedContrastWay);
        if(selectedContrastWay.getContrastType().equals("DataBase")){

            long t1=System.currentTimeMillis();
            initDBCombo();
            long t2=System.currentTimeMillis();
//            System.out.println("加载数据库选项耗时："+(t2-t1)+"ms");
            this.getPane().setOnDragOver(null);
            this.getPane().setOnDragDropped(null);
            return;

        }else if(selectedContrastWay.getContrastType().equals("CSV")){
            File file=new File(LoginHelperCSVDragDroppedHandler.cacheCSVFileName);
            if(!file.exists()){
                showMessage("请拖动要使用的CSV文件至本窗口。");
            }
           this.getPane().setOnDragOver(new FileDragOverEventHandler(new String[]{".csv",".CSV"}));
           this.getPane().setOnDragDropped(new LoginHelperCSVDragDroppedHandler());

        }else {
            this.getPane().setOnDragOver(null);
            this.getPane().setOnDragDropped(null);
        }
        //在非数据库模式数据源选项不显示
        dbComBo.setVisible(false);
    }
    public void onUserNameChanged(){
        onUserInfoChanged(false);
    }
    public void onNCUserCodeChanged(){
        onUserInfoChanged(true);
    }
    private void onUserInfoChanged(boolean isUserCode){
        String  type=((ContrastWayVO)this.contrastComBo.getSelectionModel().getSelectedItem()).getContrastType();
        try {
            if("DataBase".equals(type))
                userChangeWithSearchDB(isUserCode);
            if("CSV".equals(type)){
                userChangeWithSearchCSV(isUserCode);
            }
        } catch (RunException e) {
            showMessage(e.getMessage());
        }
    }
    public ReadCSV csvCache=null;
    private void initCsvCache() throws RunException {
        if(csvCache==null )
        csvCache=new ReadCSV(LoginHelperCSVDragDroppedHandler.cacheCSVFileName);
    }
    public void clearCSVCache(){
        csvCache=null;
    }
    private void userChangeWithSearchCSV(boolean isUserCode) throws RunException {
        initCsvCache();
        csvCache.reset();

        while (csvCache.hasNext()){
            Map<String,String> map=csvCache.next();
            if(isUserCode){
                String userCode=ncUserText.getText().trim();
                if(userCode.equals(map.get("NCCODE"))){
                    this.oaUserText.setText(map.get("OACODE"));
                    this.userNameText.setText(map.get("USERNAME"));
                    return;
                }
            }else{
                String ncUserName=this.userNameText.getText().trim();
                if(ncUserName.equals(map.get("USERNAME"))){
                    this.oaUserText.setText(map.get("OACODE"));
                    this.ncUserText.setText(map.get("NCCODE"));
                   return;
                }
            }

        }
        clearInputText(isUserCode);

    }

    private void userChangeWithSearchDB(boolean isUserCode) {
        DataBaseVO dataBaseVO= (DataBaseVO) this.dbComBo.getSelectionModel().getSelectedItem();
        try {
            String sql="select code ncCode,mnecode oaCode,name userName from bd_defdoc where pk_defdoclist =" +
                    "(select pk_defdoclist from bd_defdoclist where code = 'nc_oa_user') ";
            if(isUserCode){
                sql=sql+" and code='"+ncUserText.getText().trim()+"'";
            }else{
                sql=sql+" and name='"+userNameText.getText().trim()+"'";
            }
           List<NCOAUserVO> userVOList= (List<NCOAUserVO>) DataBaseDAO.getInstance().setDataSource(dataBaseVO).query(sql, NCOAUserVO.class);
            if(userVOList==null||userVOList.size()==0){
                clearInputText(isUserCode);

            }
           if(userVOList.size()==1){
               this.oaUserText.setText(userVOList.get(0).getOaCode());
               if(isUserCode){
                   this.userNameText.setText(userVOList.get(0).getUserName());
               }else{
                   this.ncUserText.setText(userVOList.get(0).getNcCode());
               }
           }
        } catch (Exception e) {
            super.showMessage(e.getMessage());
        }
    }

    private void clearInputText(boolean isUserCode) {
        this.oaUserText.setText("");
        if(isUserCode){
            this.userNameText.setText("");
        }else{
            this.ncUserText.setText("");
        }
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
        return new TextInputControl[]{};
    }

    /*@Override
    protected Node[] getCacheNode() {
        return new Node[]{oaUserText,ncUserText,ipAndPortText,keyText,ssoURLText,userNameText,contrastComBo};
    }*/

    @Override
    protected Class<? extends BaseVO> getBaseClass() {
        return null;
    }
}
