package psn.cyf.base.database.connection;


import psn.cyf.base.database.vo.DataBaseVO;
import psn.cyf.exception.RunException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {

    private DBConnection() {
    }
    private static DBConnection instance;
    public static DBConnection getInstance(){
        if(instance==null){
            instance=new DBConnection();
        }
        return instance;
    }
    private  Map<String,Connection> connectionMap=new HashMap<>();
    private static String driver = oracle.jdbc.driver.OracleDriver.class.getName(); //驱动
    public synchronized Connection getConnection(DataBaseVO baseVO)throws RunException {

        if(baseVO==null){
            return null;
        }
        return makeConnection(baseVO);
    }

    private Connection makeConnection(DataBaseVO baseVO) throws RunException {




        try {
            if(connectionMap.get(baseVO.getPk_dataBase())!=null&&!connectionMap.get(baseVO.getPk_dataBase()).isClosed()){

                return connectionMap.get(baseVO.getPk_dataBase());
            }
            String url="jdbc:oracle:thin:@"+baseVO.getIp()+":"+baseVO.getPort()+"/"+baseVO.getInstance();
            Class.forName(driver);
            Connection connection= DriverManager.getConnection(url,baseVO.getUser(),baseVO.getPassword());
            connectionMap.put(baseVO.getPk_dataBase(),connection);
            return connection;
        } catch (Exception e) {
            throw new RunException(e);
        }
    }

    public synchronized void testConnection(DataBaseVO baseVO)throws RunException {

        if(baseVO==null){
           throw new RunException("DataBaseVO Must Not Be Null!");
        }

        //return null;
       makeConnection(baseVO);
    }
    public void colseConnection(DataBaseVO baseVO) throws RunException {
        if(baseVO==null||connectionMap.get(baseVO.getPk_dataBase())==null){
            return;
        }
        try {
            connectionMap.get(baseVO.getPk_dataBase()).close();
            connectionMap.remove(baseVO.getPk_dataBase());
        } catch (Exception e) {
            throw new RunException(e);
        }
    }
}
