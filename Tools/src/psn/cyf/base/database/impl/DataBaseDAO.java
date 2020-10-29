package psn.cyf.base.database.impl;



import psn.cyf.base.database.connection.DBConnection;
import psn.cyf.base.database.itf.IDataBase;
import psn.cyf.base.database.vo.DataBaseVO;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.exception.RunException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class DataBaseDAO implements IDataBase {
    private DataBaseDAO() {
    }
    private static DataBaseDAO instance;
    private DataBaseVO sourceVO;
    public static DataBaseDAO getInstance(){
        if(instance==null){
            return instance=new DataBaseDAO();
        }
        return instance;
    }
    public DataBaseDAO setDataSource(DataBaseVO dataSource){
        sourceVO=dataSource;
        return this;
    }


    @Override
    public  List<? extends BaseVO> query(String sql, Class<? extends BaseVO> voClass) throws RunException {
            Connection connection= DBConnection.getInstance().getConnection(this.sourceVO);
        try {

            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            List<BaseVO > resList=new ArrayList<>();
            while (resultSet.next()){
                BaseVO tmp=voClass.newInstance();
               String[] fields= tmp.getFieldNames();
                ResultSetMetaData metaData=resultSet.getMetaData();
                for(int i=1,len=metaData.getColumnCount();i<=len;i++){
                   String la= metaData.getColumnLabel(i);
                        String columnName=metaData.getColumnName(i);
                        Object value=resultSet.getObject(i);
                        for(String field:fields){
                            if(field.equals(columnName)){
                                tmp.setFieldValue(field,getSuitableValue(tmp.getFileType(field),value));
                                break;
                            }
                        }
                }
                resList.add(tmp);

            }
            return resList;
        } catch (Exception e) {
           throw new RunException(e);
        }

    }
    private Object getSuitableValue(Class cls,Object value){
         if(String.class.equals(cls)){
             return value==null?null:value.toString();
         }
         if(Integer.class.equals(cls)){
             return value==null?null:new Integer(value.toString());
         }
        return value;
    }
}
