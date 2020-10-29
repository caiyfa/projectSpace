package psn.cyf.utils.file;

import org.apache.tools.ant.util.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileCompare {


    public static void main(String[] args) {
          File rootFile=new File("D:\\NCHomes\\nc65home\\国盛\\yonyou\\modules");


        File compareFile=new File("D:\\NCHomes\\nc65home\\国盛\\nc65home0805\\nc65home\\home\\modules");


    }
    static  List<String> searchFile(File rootFile,File compareFile){
        //获取被对比文件列表
        List<String> rootFileList=new ArrayList<>();
        outFile(rootFile,rootFileList,rootFile);
        //获取对比文件列表
        List<String> compareFileList=new ArrayList<>();
        outFile(compareFile,compareFileList,compareFile);
        //将两者进行筛选
        List<String> resList=new ArrayList<>();
        rootFileList.forEach((fileName)->{
            if(!compareFileList.contains(fileName)){
                resList.add(fileName);
            }
        });


        return resList;
    }
    static void outFile(File file,List<String> fileList,File baseFile){
        if(file.isDirectory()){
            File[] childs=file.listFiles();

             for(File child:childs){
                 outFile(child,fileList,baseFile);
             }

        }else{
            fileList.add(file.getPath().substring(baseFile.getPath().length(),file.getPath().length()));
        }

    }
}
