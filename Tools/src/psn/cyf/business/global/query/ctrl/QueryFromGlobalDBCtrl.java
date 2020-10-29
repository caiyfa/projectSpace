package psn.cyf.business.global.query.ctrl;

import com.alibaba.fastjson.JSON;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.base.database.impl.BaseDAO;
import psn.cyf.base.database.vo.DataBaseVO;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.business.global.query.util.TraverseDataBase;
import psn.cyf.cache.Cache;
import psn.cyf.exception.RunException;
import psn.cyf.freemarker.*;
import psn.cyf.utils.FileUtil;


import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryFromGlobalDBCtrl extends SuperCtrl {

    public AnchorPane anchorPane;
    public ComboBox dbComBo;
    public Button queryBtn;
    public Button showLog;
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
    public void onBtnShowLog(){
        try {
            LogToHtmlPlugin.getInstance().logToHtml();
        } catch (RunException e) {
            println(e.getMessage());
        }
    }
    /**
     * 某个数据源下，查询条件下的 某个表对应的查询语句
     */
    private LogVO logVO=new LogVO();
    public void onQueryBtnPressed(){
        msgArea.appendText("查询中...\n");
    }
    public void onBtnQuery()     {

        DataBaseVO dataBaseVO= (DataBaseVO) this.dbComBo.getSelectionModel().getSelectedItem();
String queryItem=queryDataText.getText().trim();
        //1.加载缓存。为生成页面结果做准备
        ContentVO contentVO=null;
        //先根据dataVO找content 找不到就初始化
        if((contentVO=logVO.getContentWithDbVO(dataBaseVO))==null){
            contentVO=new ContentVO();
            contentVO.setDbInfo(dataBaseVO);
            logVO.getContentVOS().add(contentVO);
        }
        QueryVO queryVO=new QueryVO();
        queryVO.setQueryString(queryItem);
        if(contentVO.getQueryVOS()==null){
            List<QueryVO> queryVOS=new ArrayList<>();
            queryVOS.add(queryVO);
            contentVO.setQueryVOS(queryVOS);
        }else{
            contentVO.getQueryVOS().add(queryVO);
        }
       //2.进行查询阶段
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
                            String querySql=sqlBuffer.toString().replace("COUNT(*)","*");
                             //塞入查询结果
                            QueryResultVO queryResultVO=new QueryResultVO(resCount,tableName,querySql);
                            if(queryVO.getTableWithQuerySqlVOS()==null){
                                List<QueryResultVO> queryResultVOS=new ArrayList<>();
                                queryResultVOS.add(queryResultVO);
                                queryVO.setTableWithQuerySqlVOS(queryResultVOS);
                            }else{
                                queryVO.getTableWithQuerySqlVOS().add(queryResultVO);
                            }
                            //输出查询日志
                            println("查出"+resCount+"条 \n "+querySql);

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


        try {

            BaseDAO.getInstance().curringData("queryCache",JSON.toJSONString(logVO));
        } catch (RunException e) {
            e.printStackTrace();
        }
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
        return new Node[]{dbComBo,queryBtn,msgArea,queryDataText,showLog};
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

