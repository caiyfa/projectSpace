package psn.cyf.utils.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCount {
    public static void main(String[] args) {
        List<String> fileList1=new ArrayList<>();
        File file1=new File("D:\\NCHomes\\nc65home\\国盛\\yonyou\\modules\\ampub");
        List<String> fileList2=new ArrayList<>();
        File file2=new File("D:\\NCHomes\\nc65home\\国盛\\ampub");
        outFile(file1,fileList1);

        outFile(file2,fileList2);
        fileList2.forEach((fileName)->{
            if(!fileList1.contains(fileName)){
                System.out.println(fileName);
            }
        });


    }
    static void outFile(File file, List<String> fileList){
        outFile(file,fileList,file);
    }

    static void outFile(File file, List<String> fileList, File baseFile){
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
