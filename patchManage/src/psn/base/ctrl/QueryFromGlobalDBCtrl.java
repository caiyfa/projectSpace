package psn.base.ctrl;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import psn.base.cache.Cache;
import psn.base.dao.BaseDAO;
import psn.base.exception.RunException;
import psn.base.utils.FileUtil;
import psn.base.vo.BaseVO;
import psn.base.vo.DataBaseVO;
import test.TraverseDataBase;


import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryFromGlobalDBCtrl extends SuperCtrl {
    /**
     *
     */
    /*class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        public synchronized void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));

        }

        public void write(int b) throws IOException {
            appendText(String.valueOf((char)b));
        }
    }*/
    public AnchorPane anchorPane;
    public ComboBox dbComBo;
    public Button queryBtn;
    //private PrintStream ps ;

    public TextArea msgArea;
    public TextField queryDataText;
    private PrintStream outLog=null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Cache.CONTROLLER.put(Cache.Ctrl.QueryFromGlobalDBCtrl,this);
        try {
            outLog=new PrintStream(FileUtil.getCacheDir()+File.separator+"queryLog.log");
        } catch (FileNotFoundException e) {
            showMessage(e.getMessage());
        }
        initDBCombo();
        //中文乱码
     //   ps = new PrintStream(new Console(msgArea) ) ;


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
    private void println(String msg){
        System.out.println(msg);
        outLog.println(msg);
        msgArea.appendText(msg);
        msgArea.appendText(System.lineSeparator());

    }
    public void onQueryBtnPressed(){
        msgArea.appendText("查询中...\n");
    }
    public void onBtnQuery()     {

        DataBaseVO dataBaseVO= (DataBaseVO) this.dbComBo.getSelectionModel().getSelectedItem();
String queryItem=queryDataText.getText().trim();

        println("开始加载数据库表名");

            TraverseDataBase base=new TraverseDataBase(dataBaseVO);
        System.out.println(dataBaseVO.toString());
            long t1=System.currentTimeMillis();
            List<String> tableList= null;
        StringBuffer sqlBuffer=null;
            try {
                tableList = base.loadTableNames();
                long t2=System.currentTimeMillis();
               println("加载"+tableList.size()+"张表。成功耗时"+(t2-t1)+"ms。");

                 println("开始查询");
                for(String tableName:tableList){

                    String getColumns="select column_name ,data_type from user_tab_columns  " +
                            "where table_name='"+tableName+"'and data_type <> 'BLOB'";
                    List<Map<String,String>> columnList=base.loadColumnName(getColumns);
                    StringBuffer baseBuffer=new StringBuffer("SELECT COUNT(*) FROM ");
                    baseBuffer.append(tableName).append(" WHERE ");

                   /* Stack<String> columnStack=new Stack<>();
                    for(String col:columnList){
                        columnStack.push(col);
                    }*/

                    sqlBuffer= new StringBuffer(baseBuffer);
                    for(int i=0,len=columnList.size();i<len;i++){
                        Map<String,String> col=columnList.get(i);
                       if(col.get("DATA_TYPE").indexOf("CHAR")!=-1){
                           sqlBuffer.append(col.get("COLUMN_NAME")).append(" LIKE '%").append(queryItem).append("%' OR ");
                       }else if(col.get("DATA_TYPE").indexOf("NUMBER")!=-1&&isNumeric(queryItem)){
                           sqlBuffer.append(col.get("COLUMN_NAME")).append(" =").append(queryItem).append(" OR ");
                       }

                       }
                    sqlBuffer=new StringBuffer(sqlBuffer.substring(0,sqlBuffer.length()-3));
                    try {
                        Integer resCount=     base.getSqlCount(sqlBuffer.toString());
                        if(resCount>0){
                            println("查出"+resCount+"条  "+sqlBuffer.toString());

                        }


                    }catch (Exception e){
                        println("【"+e.getMessage().replace("\n","")+"】"+sqlBuffer.toString());
                        e.printStackTrace();
                    }finally {
                        //重置查询语句
                        sqlBuffer= new StringBuffer(baseBuffer);
                    }
                }





            } catch (Exception e) {
                System.out.println(sqlBuffer);
                 println(e.getMessage());
            }

        msgArea.appendText("查询结束!\n");
        }
    /**
     * 判断字符是否是数字
     *
     * @author shenlp
     * @time 2018下午4:18:19
     */
    private boolean isNumeric(String str) {
        if (str == null)
            return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    @Override
    protected Node[] getDecorateArr() {
        return new Node[]{dbComBo,queryBtn,msgArea,queryDataText};
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
        return new TextInputControl[]{queryDataText};
    }

    @Override
    protected Class<? extends BaseVO> getBaseClass() {
        return null;
    }



    @Override
    protected Pane getPane() {
        return anchorPane;
    }
}

