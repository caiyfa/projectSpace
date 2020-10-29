package psn.cyf.base.vo;

import psn.cyf.exception.RunException;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public  class BaseVO {
    /**
     * @return 返回主键值
     */
     public   String getPrimaryKey(){
      return null;
     }



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
    public    String getPkField(){
        return null;
    };

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
    public Class getFileType(String fieldName){
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.getName().equals(fieldName)){
                return field.getType();
            }
        }
        return null;
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
        }else if(double.class.equals(oldValue)) {
            return new Double(oldValue.toString()).doubleValue();
        }else if(int.class.equals(oldValue)){
            return new Integer(oldValue.toString()).intValue();
        }else if(Date.class.equals(oldValue)){
            try {
                return dateFormat.parse(oldValue.toString());
            } catch (ParseException e) {
                return null;
            }
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

    public boolean compareWith(BaseVO baseVO){
        //传参为空不比较
        if(baseVO==null){
            return false;
        }
        //类不同不比较
         if(!this.getClass().equals(baseVO.getClass())){
             return false;
         }
         //因为是同一个类只要获取本方字段就行
         String[] fields=this.getFieldNames();
         for(String field :fields){
             try {
                Object obj= this.getFieldValue(field);
                Object otherObj=baseVO.getFieldValue(field);
                if(obj==null&&otherObj!=null){
                    return false;
                }else if(obj!=null&&otherObj==null){
                    return false;
                }else if(obj==null&&otherObj==null){
                    continue;
                }else  if(!obj.equals(otherObj)){
                    return false;
                }
             } catch (RunException e) {
                 return false;
             }
         }
        return true;
    }

}
