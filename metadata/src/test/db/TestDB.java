package test.db;


import psn.base.exception.RunException;
import psn.base.utils.StringUtils;
import psn.base.vo.BaseVO;
import psn.base.vo.MDTableVO;
import psn.database.ConnectionCenter;
import psn.database.DBContent;
import psn.database.DatabaseType;

import java.sql.*;
import java.util.*;

public class TestDB {
    public static  Connection connection=ConnectionCenter.getInstance().getConnecction("db",getDBContent());
    public static void main(String[] args) throws Exception {
String value="gl_global_amount_loc";
        Map<String,List<String>>  tableInfoMap=queryTableCols();
        for(String table:tableInfoMap.keySet()){
            System.out.println("查询:"+table);
            queryTableColByValue(table,tableInfoMap.get(table),value);

          /*  StringBuffer sb =new StringBuffer();
            List<String> cols=tableInfoMap.get(table);
            for(String col:cols){
                sb.append(col).append(",");
            }
           String colsStr= sb.substring(0,sb.length()-1);
            System.out.println("表"+table+"共有："+colsStr+" "+ cols.size()+"个字段");*/
        }
    //    System.out.println("共查询到："+tableInfoMap.size()+"张表");

   /* List<String> tables=getAllTables();
    for(String tab:tables){
        System.out.println(tab);
    }
        System.out.println("共查询到："+tables.size()+"张表");
        */


    }
    private static void queryTableColByValue(String table,List<String> cols,String value){
        StringBuffer sql=new StringBuffer();
        sql.append("select ");
        for(String col:cols){
            sql.append(col).append(",");
        }
        sql=new StringBuffer(sql.substring(0,sql.length()-1));//去逗号
        sql.append(" from ").append(table).append(" where ");
        for(String col:cols){
            sql.append( col).append(" like '%"+value+"%' and ");
        }
        sql=new StringBuffer(sql.substring(0,sql.length()-4));//去and

        PreparedStatement ps= null;ResultSet rs=null;

        try {
            ps = connection.prepareStatement(sql.toString());

        rs= ps.executeQuery();
        StringBuffer info=new StringBuffer();
        while(rs.next()){
            for( String col:cols){
                Object tmp=rs.getObject(col);
                if(tmp!=null&&tmp.toString().trim().length()!=0){
                    info.append(col).append(",");
                }
            }
        }

        if(info.length()!=0){
            System.out.println(table+" "+info.substring(0,info.length()-1));
        }
            rs.close();
        ps.close();
        } catch (SQLException e) {
            try {
            if(rs!=null){
                    rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println(sql.toString());
            e.printStackTrace();
        }


    }
    private static Map<String,List<String>> queryTableCols() throws  Exception {
        Map<String,List<String>> res=new HashMap<>();
        PreparedStatement ps=connection.prepareStatement("select  table_name,column_name,data_type   from user_tab_columns  ");
        ResultSet rs= ps.executeQuery();
        ///long t2=System.currentTimeMillis();


        while(rs.next()){
            String table_name= rs.getString("table_name");
            String column_name= rs.getString("column_name");
            String data_type= rs.getString("data_type");
           // System.out.println("【table】  "+table_name+"  【column】  "+column_name+"【data_type】"+data_type);
            if(!StringUtils.isNullWithTrim(table_name)&&!StringUtils.isNullWithTrim(column_name)&&data_type.indexOf("CHAR")!=-1){
                if(res.containsKey(table_name)){
                    res.get(table_name).add(column_name);
                }else{
                    ArrayList<String> cols=new ArrayList< >();
                    cols.add(column_name);
                    res.put(table_name,cols);
                }
            }

         }
        rs.close();
        ps.close();
        return res;
    }
    private static List<String> getAllTables() throws  Exception {
    List<String> res=new ArrayList<>();
        PreparedStatement ps=connection.prepareStatement("select table_name from user_tables");
        // long t1=System.currentTimeMillis();
        ResultSet rs= ps.executeQuery();
        ///long t2=System.currentTimeMillis();


        while(rs.next()){
           String table_name= rs.getString("table_name");
            if(table_name!=null&&table_name.trim().length()!=0)
            res.add(table_name);


        }
        rs.close();
        ps.close();
        return res;
    }

    private static DBContent getDBContent(){
        DBContent content=new DBContent();
        content.setIp("127.0.0.1");
        content.setPort("1521");
        content.setUserName("zq0920");
        content.setPassWord("1");
        content.setDbInstance("orcl");
        content.setDatabase(DatabaseType.Oracle11G);
        return content;
    }
}
