package test;

import psn.base.dao.DBConnection;
import psn.base.exception.RunException;
import psn.base.vo.DataBaseVO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryBlob {
    public static void main(String[] args) throws Exception {
        String sql="select content from pub_wf_def wd where  wd.pk_wf_def='1001ZZ1000000000RH77'  ";
        Connection con= getDbConn();
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        InputStream is=resultSet.getBlob(1).getBinaryStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String tmp=null;
        while((tmp=reader.readLine())!=null){
            sb.append(tmp);
        }
        System.out.println(sb.toString() );

    }
    private static Connection getDbConn() throws RunException {
        DataBaseVO dataBaseVO=new DataBaseVO();
        dataBaseVO.setInstance("orcl");
        dataBaseVO.setIp("127.0.0.1");
        dataBaseVO.setPort("1521");
        dataBaseVO.setSourceName("test");
        dataBaseVO.setUser("hxgfnc0511");
        dataBaseVO.setPassword("1");
        dataBaseVO.setPk_dataBase("pk");


        return DBConnection.getInstance().getConnection(dataBaseVO);
    }
}
