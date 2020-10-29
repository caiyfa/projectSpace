package psn.cyf.business.global.query.util;



import psn.cyf.base.database.connection.DBConnection;
import psn.cyf.base.database.vo.DataBaseVO;
import psn.cyf.exception.RunException;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraverseDataBase {
    private DataBaseVO dataBaseVO;

    public TraverseDataBase(DataBaseVO dataBaseVO) {
        this.dataBaseVO = dataBaseVO;
    }

    public static void main(String[] args) throws RunException, SQLException, IOException {
        TraverseDataBase traverseDataBase=new TraverseDataBase(null);
        Connection connection=traverseDataBase.getDbConn();
String sql="select content from  pub_wf_def WHERE pk_wf_def = '1001GF1000000000F0PH'";
        ResultSet resultSet= null;
        Blob blob = null;
        PreparedStatement preparedStatement=   connection.prepareStatement(sql);
        resultSet= preparedStatement.executeQuery();
        if(resultSet.next()){
             blob= resultSet.getBlob(1);
        }
       InputStream is= blob.getBinaryStream();
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, "UTF-8");
        while ((in.read(buffer))>-1){
            out.append(buffer,0,buffer.length);
        }
        System.out.println(out.toString());
    }

    private static void testLoadColumnName() throws RunException, SQLException {
        TraverseDataBase traverseDataBase=new TraverseDataBase(null);
        String getColumns="select column_name ,data_type from user_tab_columns   where table_name='IUFO_MEASURE_DATA_O0XKMP43'and data_type <> 'BLOB'";
        List<Map<String,String>> res= traverseDataBase.loadColumnName(getColumns);
        System.out.println(res);
    }

    /* static   PrintStream out;

        static {
            try {
                out = new PrintStream("./out.log");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        public static String queryItem="1001JZ10000000009E40";
        public static void main(String[] args) {
            out.println("开始加载数据库表名");
            TraverseDataBase base=new TraverseDataBase();
            long t1=System.currentTimeMillis();
            List<String> tableList= null;
            try {
                tableList = base.loadTableNames();
                long t2=System.currentTimeMillis();
                out.println("加载"+tableList.size()+"张表。成功耗时"+(t2-t1)+"ms。");
                out.println("开始查询");
                for(String tableName:tableList){
                    List<String> columnList=base.loadColumnName(tableName);
                    StringBuffer stringBuffer=new StringBuffer("SELECT COUNT(*) FROM ");
                    stringBuffer.append(tableName).append(" WHERE ");
                    for(String column:columnList){
                        stringBuffer.append(column).append(" LIKE '%").append(queryItem).append("%' OR ");
                    }

                    stringBuffer=new StringBuffer(stringBuffer.substring(0,stringBuffer.length()-3));
                    //  out.println(stringBuffer.toString());
                    // System.out.println("开始查询 "+stringBuffer.toString());
                    try {
                        Integer resCount=     base.getSqlCount(stringBuffer.toString());
                        if(resCount>0){
                            out.println("查出"+resCount+"条  "+stringBuffer.toString());
                        }
                    }catch (Exception e){
                        out.println("异常语句   "+stringBuffer.toString());
                        e.printStackTrace();
                    }


                }
            } catch (Exception e) {
                out.println(e);
            }


        }*/
    public Integer getSqlCount(String sql) throws RunException, SQLException {
        Connection connection=getDbConn();
        ResultSet resultSet= null;
        PreparedStatement preparedStatement=null;
        Integer res=0;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                res=res+  resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }finally {
            if(resultSet!=null){
                resultSet.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
        }
        return res;

    }
    public List<Map<String,String>> loadColumnName(String sql) throws RunException, SQLException {
        // and data_type like 'VARCHAR%'";
        Connection connection=getDbConn();
        List<Map<String,String>> columnList=new ArrayList<>();

        ResultSet resultSet= null;
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()){

            Map<String,String > map=new HashMap<>();
            for(int i=1,len=metaData.getColumnCount()+1;i<len;i++){
                map.put(metaData.getColumnLabel(i),resultSet.getString(i));
                map.put(metaData.getColumnLabel(i),resultSet.getString(i));
            }

            columnList.add( map);
        }
        } catch (SQLException e) {
            System.out.println(sql);
            throw new RunException(e);
        }finally {
            if(resultSet!=null){
                resultSet.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
        }

        return columnList;
    }
    public List<String> loadTableNames() throws RunException, SQLException {
        String getTables="select table_name from user_tables order by table_name ";
        Connection connection=getDbConn();
        List<String> tableList=new ArrayList<>();
        PreparedStatement preparedStatement= connection.prepareStatement(getTables);
       ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
        tableList.add(resultSet.getString(1));
        }

        resultSet.close();
        preparedStatement.close();
        return tableList;
    }
    private Connection getDbConn() throws RunException {
      return   DBConnection.getInstance().getConnection(dataBaseVO);
    }
   /*   private Connection getDbConn() throws RunException {
        DataBaseVO dataBaseVO=new DataBaseVO();
        dataBaseVO.setInstance("orcl");
        dataBaseVO.setIp("10.10.10.1");
        dataBaseVO.setPort("1521");
        dataBaseVO.setSourceName("test");
        dataBaseVO.setUser("hxgfnc");
        dataBaseVO.setPassword("1");
        dataBaseVO.setPk_dataBase("pk");


        return DBConnection.getInstance().getConnection(dataBaseVO);
    }*/
}
