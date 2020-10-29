package psn.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionCenter {

    public static Map<String,Connection> cache=new HashMap<>();





    private ConnectionCenter(){
    }
    private static ConnectionCenter connInstance=null;
    public static ConnectionCenter getInstance(){
        if(connInstance==null){
            connInstance=new ConnectionCenter();
        }
        return connInstance;
    }
    public Connection getConnecction(String dbName,DBContent content){
        Connection connection=null;
        try {
        if(cache.containsKey(dbName)){
            if(!cache.get(dbName).isClosed()){
                return cache.get(dbName);
            }
        }
        String url="jdbc:oracle:thin:@"+content.getIp()+":"+content.getPort()+":"+content.getDbInstance();

//            Class.forName(database.driver);
            long t1=System.currentTimeMillis();
            connection= DriverManager.getConnection(url,content.getUserName(),content.getPassWord());
            cache.put(dbName,connection);//将数据连接存放到缓存中
            long t2=System.currentTimeMillis();
            System.out.println("初始化"+dbName+"数据源,耗时："+(t2-t1)+"ms.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public boolean testConnection(DBContent content){
        Connection connection=null;
        try {
            String url="jdbc:oracle:thin:@"+content.getIp()+":"+content.getPort()+":"+content.getDbInstance();
//            Class.forName(database.driver);
            connection= DriverManager.getConnection(url,content.getUserName(),content.getPassWord());
            System.out.println(connection);
            return true;
        } catch (Exception e) {
            return false;
        }finally {
            if(connection!=null){
                try {
                    System.out.println(connection+"释放连接");
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
