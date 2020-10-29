package psn.base.utils;


import com.sun.istack.internal.NotNull;
import org.json.JSONArray;
import psn.base.exception.RunException;
import test.EncryptUtil;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Exception;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.Thread;
import java.util.HashSet;
import java.util.Set;

public class JsonUtil {

    private static JsonUtil instance=null;
    /**
     * 为避免脏数据,文件同时只能读或取做一样操作
     */
    private static Set<String> usingFile=new HashSet<>();

    private JsonUtil() {
    }
    public static JsonUtil getInstance(){
        if(instance==null){
            instance=new JsonUtil();
        }
        return instance;
    }


    /**
     * @param jsonName json文件名
     * @return JSONArr数组
     * @throws IOException
     */
    public JSONArray getJsonArr(String jsonName) throws RunException {
        try {
            String jsonUrl=FileUtil.getCacheDir()+ File.separator+jsonName+".json";
            //若当前文件被占用则睡眠1毫秒
            if(usingFile.contains(jsonUrl)){
                Thread.sleep(1);
                return getJsonArr(jsonName);
            }
            File jsonFile=new File(jsonUrl);
            if(!jsonFile.exists()){
                jsonFile.createNewFile();
                return new JSONArray();
            }
            usingFile.add(jsonUrl);
            StringBuilder stringBuilder=new StringBuilder();
            BufferedReader reader=new BufferedReader(new FileReader(jsonFile));
            String tmpStr="";
            while ((tmpStr=reader.readLine())!=null){
                stringBuilder.append(tmpStr);
            }

            reader.close();
            usingFile.remove(jsonUrl);
            if(stringBuilder.toString().trim().length()==0){
                return new JSONArray();
            }
            long t1=System.currentTimeMillis();
            String decodeStr=EncryptUtil.getInstance().decode(stringBuilder.toString());
            long t2=System.currentTimeMillis();
            System.out.println("解密耗时："+jsonName+(t2-t1)+"ms");
            return new JSONArray(decodeStr);
//            return new JSONArray(stringBuilder.toString());
        }catch (Exception e){
            throw new RunException(e);
        }


    }

    public void curingData(String jsonName,@NotNull JSONArray jsonArray) throws   RunException {
        try {
            String jsonUrl=FileUtil.getCacheDir()+ File.separator+jsonName+".json";
            if(usingFile.contains(jsonUrl)){
                Thread.sleep(1);
                curingData(jsonName,jsonArray);
            }
            usingFile.add(jsonUrl);


            FileWriter fileWriter=new FileWriter(jsonUrl);
            PrintWriter printWriter=new PrintWriter(fileWriter);
            //加密字符串
            String encodeStr= EncryptUtil.getInstance().encode(jsonArray.toString());
            printWriter.write(encodeStr);
//            printWriter.write(jsonArray.toString());
            printWriter.flush();//刷新到文件中
            printWriter.close();
            fileWriter.close();
            usingFile.remove(jsonUrl);
        }catch (Exception e){
            throw new RunException(e);
        }



    }
}
