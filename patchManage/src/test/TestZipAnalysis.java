package test;



//import org.apache.commons.compress.archivers.ArchiveEntry;
//import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
//import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import psn.base.utils.EncodeUtil;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.File;

import java.util.Enumeration;


public class TestZipAnalysis {
    public static void main(String[] args) throws Exception {
        System.out.println("&amp;ndash;");

//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        String filePath="C:\\Users\\Dell\\Desktop\\WORK\\patch\\光大证券\\20181116-光大德勤进项税对接功能-zhangmeng4.zip";
        //获取文件的编码格式
        String fileEncode = EncodeUtil.getEncode(filePath,true);
      /*  //使用指定编码格式打开文件否则有可能会导致中文乱码
        ZipArchiveInputStream zipInputStream=new ZipArchiveInputStream(new FileInputStream(new File(filePath)),fileEncode);

        ZipArchiveEntry entry=null;
        while ((entry= (ZipArchiveEntry) zipInputStream.getNextEntry())!=null){
            System.out.println(entry.getName());
            zipInputStream.read();
        }*/

        ZipFile zipFile=new ZipFile(new File(filePath),fileEncode);
        for(Enumeration<ZipEntry> entrys=zipFile.getEntries();entrys.hasMoreElements();){
            ZipEntry zipEntry=entrys.nextElement();

        }




    }
}
