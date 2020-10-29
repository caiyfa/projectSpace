package psn.cyf.utils;

import org.json.JSONObject;
import psn.cyf.exception.RunException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtil {
    /*private static FileUtil instance =null;
    public static FileUtil getInstance(){
        if (instance==null){
            instance=new FileUtil();
        }
        return instance;
    }*/
    public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream(getProjectPath()+"database.properties");
        Properties properties=new Properties();
        properties.load(fis);
        System.out.println(fis.available());
        properties.clear();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("dbUser","test1");
        jsonObject.put("password","12345");
        properties.put("HXGF",jsonObject.toString());
        properties.store(new FileOutputStream(getProjectPath()+"database.properties"),"这是一个测试");

        System.out.println(properties.get("userName"));
    }
    private FileUtil() {
    }

    /**
     * @return 编译后class所在的项目路径
     */
    public static String getProjectPath(){

        //该方式可以用于不打jar包的情况
//        return FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        //该方式用于可以解决打成jar的情况。但换路径之后就会造成缓存丢失的情况
//        return "./";
        //使用用户文件夹放置缓存
        return System.getProperty("user.home");
    }


    /**
     * @return Cache所在目录
     */
    public static String getCacheDir(){
        String cachePath=getProjectPath()+ File.separator+"cache";
        File  cacheDir=new File(cachePath);
        if(!cacheDir.exists()){
            cacheDir.mkdir();
        }
        return cachePath;
    }

    /**
     * @return 附件目录
     */
    public static String getAttachementDir(){
        String attachmentPath=getProjectPath()+"Attachment";
        File attacheDir=new File(attachmentPath);
        if(!attacheDir.exists()){
            attacheDir.mkdir();
        }
        return attachmentPath;
    }
    public static void deleteFile(String fileName){

        String filePath=FileUtil.getAttachementDir()+File.separator+fileName;
        File file=new File(filePath);
        if(file.exists()){
            file.delete();
            System.out.println("删除文件【"+fileName+"】成功");
        }else {
            System.out.println("【"+fileName+"】不存在");
        }

    }
    public static String uploadFile(File sourceFile,String uploadFileName) throws RunException {
        if(!sourceFile.exists()){
            return null;
        }
        try {
            String sourceFilePath=sourceFile.getAbsolutePath();
            FileInputStream fis=new FileInputStream(sourceFile);
            String uploadFilePath=FileUtil.getAttachementDir()+File.separator+uploadFileName+sourceFilePath.substring(sourceFilePath.lastIndexOf("."),sourceFilePath.length());
            FileOutputStream fos=new FileOutputStream(new File(uploadFilePath));
            byte[] buff=new byte[1024];
            int num=0;
            while ((num=fis.read(buff))!=-1){
                fos.write(buff,0,num);
            }
            fis.close();
            fos.flush();
            fos.close();
            System.out.println(sourceFile.getName()+" 上传至 "+uploadFilePath);
            return uploadFilePath;

        }catch (Exception e){
            throw new RunException(e);
        }
    }
}
