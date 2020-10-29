package psn.cyf.freemarker;

import com.alibaba.fastjson.JSON;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import psn.cyf.base.database.impl.BaseDAO;
import psn.cyf.base.database.vo.DataBaseVO;
import psn.cyf.exception.RunException;
import psn.cyf.utils.FileUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogToHtmlPlugin {
    public static void main(String[] args) throws IOException, RunException {
        LogToHtmlPlugin.getInstance().logToHtml();
    }

    private static LogToHtmlPlugin plugin;
    private Configuration freemarkerConfiguration;
    private String separator=File.separator;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS");
    public void logToHtml() throws RunException {
//        LogToHtmlPlugin.getInstance().templateFileToCacheDirectory(templateFileName);
        //初始化日志缓存目录

        try {
            File logFileDir=new File( FileUtil.getCacheDir()+ File.separator+"LogHtml");
            if(!logFileDir.exists()){
                logFileDir.mkdir();
            }
            File htmlFile=new File(logFileDir.getPath()+File.separator+"QueryLog_"+sdf.format(new Date())+".html");
            if(!htmlFile.exists()){
                htmlFile.createNewFile();
            }
//            System.out.println(System.getProperty("os.name"));
            String str= BaseDAO.getInstance().getData("queryCache");

            LogVO logVO= JSON.toJavaObject(JSON.parseObject(str), LogVO.class);
//            Map<DataBaseVO,Map<String, Map<String,String>>>
            Map<String,Object> valueMap=new HashMap<>();


            valueMap.put("logVO",logVO);
            toHtml(valueMap,htmlFile);
            System.out.println(htmlFile.getPath());
            Runtime.getRuntime().exec( "C:/Windows/System32/cmd.exe /k start "+htmlFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
         throw new RunException(e);
        }
    }

    private void toHtml(Object cache,File htmlFile) throws IOException, TemplateException {
        Template template= LogToHtmlPlugin.getInstance(). getTemplate("ShowLog.html");
        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(htmlFile);
        // 第七步：调用模板对象的process方法输出文件。
        template.process(cache, out);
        // 第八步：关闭流。
        out.close();
    }
    public static LogToHtmlPlugin getInstance(){
        return plugin==null?plugin=new LogToHtmlPlugin():plugin;
    }
    private Template getTemplate(String templateName) throws IOException {
        //加载模板文件至缓存文件夹
        templateFileToCacheDirectory(templateName);
        //设置字符集
        getFreemarkerConfiguration().setDefaultEncoding("utf-8");
        //设置模板文件目录
        getFreemarkerConfiguration().setDirectoryForTemplateLoading(new File(getTemplatePath()));


        return getFreemarkerConfiguration().getTemplate(templateName);
    }

    /**
     * @return 缓存文件夹
     */
    private String getTemplatePath(){
        File dir=new File(FileUtil.getCacheDir()+separator+"freemarkerTemplate");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir.getPath();
    }
    private String getLogFilePath(){
        File dir=new File(FileUtil.getCacheDir()+separator+"logFiles");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir.getPath();
    }

    /**
     * 将工程下的模板更新到程序目录中
     * @param templateName  模板名称
     */
    private void templateFileToCacheDirectory(String templateName) throws IOException {
        String templateFilePath=getTemplatePath()+separator+templateName;
        File templateFile=new File(templateFilePath);
        if(!templateFile.exists()){
            InputStream stream= this.getClass().getResourceAsStream(templateName);
            if(stream==null){
                return;
            }
            FileOutputStream outputStream=new FileOutputStream( templateFile);
            byte[] buffer=new byte[1024];
            int index=0;
            while ((index=stream.read(buffer))!=-1){
                outputStream.write(buffer,0,index);
                outputStream.flush();
            }
            stream.close();
            outputStream.close();
        }else{
            //存在就删除文件重新调用该方法
            templateFile.delete();
            templateFileToCacheDirectory(templateName);
        }


    }
    private Configuration getFreemarkerConfiguration(){

        return freemarkerConfiguration==null?freemarkerConfiguration=new Configuration(Configuration.getVersion()):freemarkerConfiguration;
    }
    private LogToHtmlPlugin() {

    }


}
