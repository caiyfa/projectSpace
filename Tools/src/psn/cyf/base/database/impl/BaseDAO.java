package psn.cyf.base.database.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import psn.cyf.base.database.itf.IBaseDAO;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.exception.RunException;
import psn.cyf.utils.DataFileUtil;
import psn.cyf.utils.VOHelper;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO implements IBaseDAO {
    private BaseDAO() {
    }
    private static   BaseDAO baseDAO=null;
    public static BaseDAO getInstance(){
        if (baseDAO==null){
            baseDAO=new BaseDAO();

        }
        return baseDAO;
    }

    /**
     *  将类转换成JSON对象
     * @param baseVO
     * @return
     * @throws RunException
     */
    public JSONObject transferVOToJson(BaseVO baseVO) throws RunException {
        JSONObject jsonObject=new JSONObject();
        //获取所有字段名
        String[] fields=baseVO.getFieldNames();
        if(fields==null){
            return jsonObject;
        }
        //转换数据到JSON
        for(int i=0;i<fields.length;i++){
            jsonObject.put(fields[i],baseVO.getFieldValue(fields[i]));
        }

        return jsonObject;
    }

    /**
     * 将json转换成VO
     * @param voClass
     * @param jsonObject
     * @return
     * @throws RunException
     */
    public BaseVO transferJsonToVO(Class<? extends  BaseVO> voClass, JSONObject jsonObject) throws RunException {
        if(jsonObject==null){
            return null;
        }
        try {
            BaseVO baseVO=voClass.newInstance();
            for(String key:jsonObject.keySet()){
                Object value=jsonObject.get(key);
                baseVO.setFieldValue(key,value);
            }
            return baseVO;

        } catch (Exception e){
            throw new RunException(e);
        }



    }
    public JSONArray transferVOListToJsonArr(List<? extends BaseVO> list) throws RunException {
        JSONArray jsonArray=new JSONArray();
        for(BaseVO baseVO:list){
            JSONObject jsonObject=this.transferVOToJson(baseVO);
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
    public List<BaseVO> transferJsonArrToVoList(Class<? extends BaseVO> cls,JSONArray jsonArray) throws RunException {
        try {
            if(cls==null||jsonArray==null){
                return null;
            }
            List<BaseVO> res=new ArrayList<>(jsonArray.length());
            for(int i=0,len=jsonArray.length();i<len;i++){
                //从ARR中获取JSON
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                //转换为VO
                BaseVO baseVO= this.transferJsonToVO(cls,jsonObject);
                res.add(baseVO);
            }
            return res;
        }catch (Exception e){
            throw new RunException(e);
        }


    }
    private void preparePK(BaseVO baseVO) throws RunException {
        try {
            if(baseVO==null){
                return;
            }
            if(baseVO.getPrimaryKey()!=null&&baseVO.getPrimaryKey().trim().length()!=0){
                return;
            }
            //获取主键名
            String pk_field_name= baseVO.getPkField();
            if(pk_field_name==null){
                throw new RunException("PK_FIELD can not be null.");
            }
            //获取 UUID
            String pk=VOHelper.getUUID();
            //设置PK到对象中
            Field pk_field=baseVO.getClass().getDeclaredField(pk_field_name);
            pk_field.setAccessible(true);
            pk_field.set(baseVO,pk);


        }catch (Exception e){
            throw new RunException(e);
        }


    }
    @Override
    public BaseVO insertVO(BaseVO baseVO) throws RunException {
        if(baseVO==null){
            return null;
        }
        //使用UUID 创建主键
        preparePK(baseVO);
        //获取已存在的数据
        JSONArray jsonArray= DataFileUtil.getInstance().getJsonArr(baseVO.getClass().getSimpleName());
        //转换数据为JSON
        JSONObject jsonObject=this.transferVOToJson(baseVO);
        jsonArray.put(jsonObject);
        //固化数据到文件
        DataFileUtil.getInstance().curingData(baseVO.getClass().getSimpleName(),jsonArray);
        return baseVO;
    }



    @Override
    public int deleteVOByPK(Class<? extends BaseVO> cls, String pk) throws RunException {
        //获取所有VO
        if(cls==null){
            return 0;
        }
        List<BaseVO> baseVOS= (List<BaseVO>) this.queryAllVO(cls);
        //若取得的数据为空则返回0
        if(baseVOS==null||baseVOS.size()==0){
            return 0;
        }
        //遍历删除指定元素
        for(int i=0,len=baseVOS.size();i<len;i++){
            if(baseVOS.get(i).getPrimaryKey().equals(pk)){
                baseVOS.remove(i);
                i--;
                len--;
            }
        }

        DataFileUtil.getInstance().curingData(cls.getSimpleName(),this.transferVOListToJsonArr(baseVOS));
        return 1;
    }



    @Override
    public  List<?> queryAllVO(Class<? extends BaseVO> cls) throws RunException {
        if (cls==null){
            return null;
        }
        //从文件中获取JSON数据
        long t1=System.currentTimeMillis();
        JSONArray jsonArray= DataFileUtil.getInstance().getJsonArr(cls.getSimpleName());
        long t2=System.currentTimeMillis();
        //转换JSON为VO
        List<BaseVO> resList=this.transferJsonArrToVoList(cls,jsonArray);
        long t3=System.currentTimeMillis();
        System.out.println("从文件中读取耗时："+(t2-t1)+"ms");
        System.out.println("转换成"+cls.getName()+"耗时："+(t3-t2)+"ms");
        return resList;
    }
    @Override
    public BaseVO queryVOByPrimaryKey(Class<? extends BaseVO> cls,String key) throws RunException {
        if (key==null){
            return null;
        }
        List<BaseVO> list= (List<BaseVO>) queryAllVO(cls);
        for(BaseVO baseVO:list){
            if(key.equals(baseVO.getPrimaryKey())){
                return baseVO;
            }
        }
        return null;
    }

    @Override
    public int updateVO(BaseVO baseVO) throws RunException {
        if(baseVO==null){
            return 0;
        }
        deleteVOByPK(baseVO.getClass(),baseVO.getPrimaryKey());
        insertVO(baseVO);

        return 1;
    }

    public void curringData(String fileName, String data) throws RunException {
        DataFileUtil.getInstance().curringNomalData(fileName,data);
    }


    public String getData(String fileName) throws RunException {

        return  DataFileUtil.getInstance().readNomalData(fileName);
    }
}
