package psn.cyf.utils;


import com.alibaba.fastjson.JSON;
import com.sun.istack.internal.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import psn.cyf.exception.RunException;


import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataFileUtil {

    private static DataFileUtil instance=null;
    /**
     * 为避免脏数据,文件同时只能读或取做一样操作
     */
    private static Set<String> usingFile=new HashSet<>();

    private DataFileUtil() {
    }
    public static DataFileUtil getInstance(){
        if(instance==null){
            instance=new DataFileUtil();
        }
        return instance;
    }
    public void writeMap(String jsonName, Map content) throws RunException{
       try {
           FileWriter fileWriter=getFileWriter(jsonName);
           PrintWriter printWriter=new PrintWriter(fileWriter);
           //加密字符串
           String encodeStr= EncryptUtil.getInstance().encode((new JSONObject(content)).toString());
           printWriter.write(encodeStr);
//            printWriter.write(jsonArray.toString());
           printWriter.flush();//刷新到文件中
           printWriter.close();
           fileWriter.close();
           usingFile.remove(jsonName);
       }catch (Exception e){
           throw new RunException(e);
       }
    }
    public String readNomalData(String fileName)throws RunException{
        try {
            StringBuilder stringBuilder=new StringBuilder();
            BufferedReader reader=getFileBufferedReader(fileName);
            String tmpStr="";
            while ((tmpStr=reader.readLine())!=null){
                stringBuilder.append(tmpStr);
            }

            reader.close();
            return stringBuilder.toString();
        }catch (Exception e){
            throw new RunException(e);
        }
    }
    public JSONObject readMap(String jsonName) throws RunException{
        try {

            StringBuilder stringBuilder=new StringBuilder();
            BufferedReader reader=getFileBufferedReader(jsonName);
            String tmpStr="";
            while ((tmpStr=reader.readLine())!=null){
                stringBuilder.append(tmpStr);
            }

            reader.close();
            usingFile.remove(jsonName);
            if(stringBuilder.toString().trim().length()==0){
                return new JSONObject();
            }
            long t1=System.currentTimeMillis();
            String decodeStr=EncryptUtil.getInstance().decode(stringBuilder.toString());
            long t2=System.currentTimeMillis();
//            System.out.println("解密耗时："+jsonName+(t2-t1)+"ms");
//            System.out.println("读取:【"+decodeStr+"】");


            return new JSONObject(decodeStr);
        }catch (Exception e){
        throw new RunException(e);
        }

    }


    /**
     * @param jsonName json文件名
     * @return JSONArr数组
     * @throws IOException
     */
    public JSONArray getJsonArr(String jsonName) throws RunException {
        try {

            StringBuilder stringBuilder=new StringBuilder();
            BufferedReader reader=getFileBufferedReader(jsonName);
            String tmpStr="";
            while ((tmpStr=reader.readLine())!=null){
                stringBuilder.append(tmpStr);
            }

            reader.close();
            usingFile.remove(jsonName);
            if(stringBuilder.toString().trim().length()==0){
                return new JSONArray();
            }
//            long t1=System.currentTimeMillis();
            String decodeStr=EncryptUtil.getInstance().decode(stringBuilder.toString());
//            long t2=System.currentTimeMillis();
//            System.out.println("解密耗时："+jsonName+(t2-t1)+"ms");
            return new JSONArray(decodeStr);
//            return new JSONArray(stringBuilder.toString());
        }catch (Exception e){
            throw new RunException(e);
        }


    }
    public void  curringNomalData(String fileName,String data) throws RunException {
        try {
            FileWriter fileWriter=getFileWriter(fileName);
            PrintWriter printWriter=new PrintWriter(fileWriter);
            printWriter.write(data);
            printWriter.flush();//刷新到文件中
            printWriter.close();
            fileWriter.close();
        }catch (Exception e){
            throw new RunException(e);
        }

    }


    public void curingData(String jsonName,@NotNull JSONArray jsonArray) throws   RunException {
        try {



            FileWriter fileWriter=getFileWriter(jsonName);
            PrintWriter printWriter=new PrintWriter(fileWriter);
            //加密字符串
            String encodeStr= EncryptUtil.getInstance().encode(jsonArray.toString());
            printWriter.write(encodeStr);
//            printWriter.write(jsonArray.toString());
            printWriter.flush();//刷新到文件中
            printWriter.close();
            fileWriter.close();
            usingFile.remove(jsonName);
        }catch (Exception e){
            throw new RunException(e);
        }



    }
    private FileWriter getFileWriter(String jsonName) throws   RunException{
        try {
            String jsonUrl=FileUtil.getCacheDir()+ File.separator+jsonName+".json";
            if(usingFile.contains(jsonUrl)){
                Thread.sleep(1);
                return getFileWriter(jsonName);
            }
            usingFile.add(jsonName);
            return new FileWriter(jsonUrl);
        }catch (Exception e){
            throw new RunException(e);
        }

    }
    private BufferedReader getFileBufferedReader(String fileName) throws RunException {
        try {
            String jsonUrl=FileUtil.getCacheDir()+ File.separator+fileName+".json";
            //若当前文件被占用则睡眠1毫秒
            if(usingFile.contains(jsonUrl)){
                Thread.sleep(1);
                return getFileBufferedReader(fileName);
            }
            File jsonFile=new File(jsonUrl);
            if(!jsonFile.exists()){
                jsonFile.createNewFile();
                return  getFileBufferedReader(fileName);
            }
            usingFile.add(fileName);
            return   new BufferedReader(new FileReader(jsonFile));
        }catch (Exception e){
            throw new RunException(e);
        }

    }
}
