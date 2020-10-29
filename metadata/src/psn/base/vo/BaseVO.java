package psn.base.vo;

import psn.base.exception.RunException;

import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public abstract class BaseVO {
    /**
     * @return 返回主键值
     */
     public abstract String getPrimaryKey();

    /**
     * 预置为其他项目使用数据库做准备
     * @return 表名
     */
    public String getTableName(){
        return null;
    }

    /**
     * @return 主键名
     */
    public  abstract String getPkField();

    /**
     * @return 父类表名
     */
    public String getParentTableName(){
        return null;
    }

    /**
     * @return 主表主键名
     */
    public String getParentPkField(){
        return null;
    }

    public String[] getFieldNames(){
        Class cls=this.getClass();
        Field[] fields=cls.getDeclaredFields();
        if(fields==null){
            return null;
        }
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }
    public void setFieldValue(String fieldName, Object obj) throws   RunException {
        try {

            Class cls=this.getClass();
            Field field=cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            if(obj==null){
                field.set(this,null);
                return;
            }
//            System.out.println(field.getType());
//            System.out.println(obj.getClass());
            Object value=getSuitableValue(field.getType(),obj);
            field.set(this,value);
        }catch (Exception e){
            throw new RunException(e);
        }
    }
    private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd mm:hh:ss");
    private Object getSuitableValue(Class type,Object oldValue){


        if(oldValue==null){
            return null;
        }else if(String.class.equals(type)){
            return oldValue.toString();
        }else if(Integer.class.equals(type)){
            return new Integer(oldValue.toString());
        }else if(Double.class.equals(type)){
            return new Double(oldValue.toString());
        }else if(double.class.equals(type)) {
            return new Double(oldValue.toString()).doubleValue();
        }else if(int.class.equals(type)){
            return new Integer(oldValue.toString()).intValue();
        }else if(Date.class.equals(type)){
            try {
                return dateFormat.parse(oldValue.toString());
            } catch (ParseException e) {
                return null;
            }
        }else if(psn.lang.Boolean.class.equals(type)){
            return new psn.lang.Boolean(oldValue.toString());
        }else if(psn.lang.Date.class.equals(type)){
            return new psn.lang.Date(oldValue);

        }else{
            return oldValue;
        }



    }

    public Object getFieldValue(String fieldName) throws RunException {
        Class cls=this.getClass();
        try {
            Field field=cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            throw new RunException(e);
        }

    }

}
