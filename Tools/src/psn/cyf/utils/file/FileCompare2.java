package psn.cyf.utils.file;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.*;

public class FileCompare2 {
    public static void main(String[] args) {
        //找出compareFile下的模块相较于rootFile里面没有的jar包， _src.jar除外
        File compareFile=new File("D:\\NCHomes\\nc65home\\国盛\\yonyou\\modules");
        File rootFile=new File("D:\\NCHomes\\nc65home\\国盛\\nc65home080506\\nc65home\\home\\modules");
        String targetPath="D:\\NCHomes\\nc65home\\国盛\\0806";


        //获取对比文件下的模块
        List<String> modules= Arrays.asList(compareFile.listFiles()).stream().collect(()->new ArrayList<String>(),(list,moduleFile)->{
            if(moduleFile.isDirectory()){
                list.add(moduleFile.getName());
            }
            },(list1,list2)-> list1.addAll(list2));
       /* modules.forEach((filename)->{
            System.out.println(filename);
        });*/
        List<String> resFilePath=new ArrayList<>();
        Map<String, List<String>> fileMap=new HashMap<>();
        modules.forEach((module)->{
            File root=new File(rootFile.getPath()+"\\"+module);
            File compare=new File(compareFile.getPath()+"\\"+module);
            List<String> res=searchFile(root,compare);
            if(res.size()!=0)
            fileMap.put(module,res);
        });
        fileMap.keySet().forEach((module)->{
            fileMap.get(module).forEach((file )->{
                 String tmpPath=module+file;
                 File sourceFile=new File(rootFile.getPath()+"\\"+tmpPath);
                File targetFile=new File(targetPath+"\\"+tmpPath);
               /* try {
                    copyFileThrowFileChannel(sourceFile ,targetFile );
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                System.out.println(rootFile.getPath()+"\\"+tmpPath);
            });

        });









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
            if(!compareFileList.contains(fileName)&&!fileName.endsWith("src.jar")){
                resList.add(fileName);
            }
        });


        return resList;
    }
    static void copyFileThrowFileChannel(File source,File dest) throws IOException {
        FileChannel input=null;
        FileChannel output=null;
        try {
            if( !dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            if( dest.isFile()){
                dest.createNewFile();
            }
            if(!source.exists()){
                return;
            }
            input=new FileInputStream(source).getChannel();
            output=new FileOutputStream(dest).getChannel();
            output.transferFrom(input,0,input.size());
        }catch (Exception e){
            System.out.println("源文件:"+source.getPath()+" 目标文件："+dest.getPath());
            e.printStackTrace();
        } finally{
            if(input!=null)
            input.close();
            if(output!=null)
            output.close();
        }

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
