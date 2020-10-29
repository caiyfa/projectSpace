package psn.base.ui.eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import psn.base.cache.Cache;
import psn.base.ctrl.PatchCtrl;
import psn.base.dao.BaseDAO;
import psn.base.utils.EncodeUtil;
import psn.base.vo.DemandVO;
import psn.base.vo.PatchVO;
import psn.base.vo.ProjectVO;

import javax.swing.filechooser.FileSystemView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.System;
//import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class ExtractDemandEventHandler implements EventHandler<ActionEvent> {
    protected DemandVO selectedDemand=null;
    protected ProjectVO selectedProject=null;
    @Override
    public void handle(ActionEvent event) {

        try {
            //从缓存获取补丁界面控制器
            PatchCtrl patchCtrl= (PatchCtrl) Cache.CONTROLLER.get(Cache.Name.PatchCtrl);
            //初始化信息
            selectedDemand =(DemandVO) patchCtrl.demandCom.getSelectionModel().getSelectedItem();
            selectedProject= (ProjectVO) patchCtrl.projectCom.getSelectionModel().getSelectedItem();

            //获取当前需求所有的补丁
            List<ZipFile> zipFileList=this.getCurrZipList();
            if(zipFileList==null){
                return;
            }

           //使用文件选择器，提取全量补丁到下面的文件中
           File selectedFile= getOutPutZipFile();
           if(selectedFile==null){
               return;
           }
            System.out.println("提取"+selectedFile.getAbsolutePath());

            //初始化输出流
            ZipOutputStream zipOS=new ZipOutputStream(new FileOutputStream(selectedFile));
            BufferedOutputStream bufferOS=new BufferedOutputStream(zipOS);
            //执行提取方法 --递归
            try {
                doExtraction(zipFileList,0,zipOS,bufferOS);
            }catch (IOException e){
                throw new IOException(e);
            }finally {
                //关闭输出流
                zipOS.close();
                bufferOS.close();
            }

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("补丁已提取到："+selectedFile.getPath());
            alert.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Set<String> existedNameSet=new HashSet<>();
    /**
     * @param zipFileList  操作的ZIP文件
     * @param anchor 当前操作的ZIP文件坐标  zipFileList的索引   坐标不断变大直到遍历完zipFileList为止
     * @param zipOS ZIP文件输出流
     * @param bufferOS  输出缓冲流
     *
     */
    private void doExtraction(List<ZipFile> zipFileList,int anchor,ZipOutputStream zipOS,BufferedOutputStream bufferOS) throws IOException {
        ZipFile currZipFile=zipFileList.get(anchor);
        List<String> filePathLsit=this.getInnerZipFilePath(currZipFile);
        for(String pathName:filePathLsit){
            if(existedNameSet.contains(pathName)){
                continue;
            }
            //取得最新entry并放置在流中
            this.putTheLastZipEntry(zipOS,bufferOS,zipFileList,anchor,pathName);
        }
        //制造递归出口
        anchor=anchor+1;

        //递归
        if(anchor<(zipFileList.size()-1)){

            doExtraction(zipFileList,anchor,zipOS,bufferOS);
        }



    }
    private void putTheLastZipEntry(ZipOutputStream zipOS,BufferedOutputStream bufferOS,List<ZipFile> zipFileList,int anchor,String currPath) throws IOException {
        ZipFile currZipFile=zipFileList.get(anchor);
        ZipEntry currEntry=currZipFile.getEntry(currPath);

//        for(int i=anchor+1;i<zipFileList.size();i++){
//            ZipFile tmpZipFile=zipFileList.get(i);
//            ZipEntry tmpEntry=tmpZipFile.getEntry(currPath);
//            if(tmpEntry!=null&&currEntry.getLastModifiedTime().toMillis()<tmpEntry.getLastModifiedTime().toMillis()){
//                //时间戳大的entry修改时间越往后
//                currEntry=tmpEntry;
//                currZipFile=tmpZipFile;
//            }
//        }
        existedNameSet.add(currEntry.getName());
        try {
            zipOS.putNextEntry(currEntry);
        }catch (IOException e){
            System.out.println(currEntry.getName());
            throw new IOException(e);
        }

        //是目录只需要zipOS
        if (currEntry.isDirectory()){
            return;
        }
        //是文件写入到流
        InputStream is=currZipFile.getInputStream(currEntry);
        //设置缓冲
        byte[]buffer=new byte[1024];
        int len=0;
        while ((len=is.read(buffer))!=-1){
            bufferOS.write(buffer,0,len);
        }
        bufferOS.flush();
        is.close();

    }


    /**
     * 获取ZIP文件中所有文件的路径
     * @param zipFile
     * @return
     */
    private List<String> getInnerZipFilePath(ZipFile zipFile){
        List<String> filePathList=new ArrayList<>();
        for(Enumeration<? extends ZipEntry> entrys = zipFile.getEntries(); entrys.hasMoreElements();){
            ZipEntry entry=entrys.nextElement();
            filePathList.add(entry.getName());
//            System.out.println((entry.isDirectory()?"目录：":"文件：")+entry.getName());
        }
        return filePathList;
    }
    private File getOutPutZipFile(){
       /* DirectoryChooser directoryChooser=new DirectoryChooser();
        String path=FileSystemView.getFileSystemView() .getHomeDirectory().getAbsoluteFile()+File.separator+selectedProject.getName()+
                new SimpleDateFormat("yyyyMMddhhmm").format(new Date())+"_"+selectedDemand.getDemandName();
        File dir= new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        directoryChooser.setInitialDirectory(dir);
        return directoryChooser.showDialog(Cache.STAGE.get(Cache.Name.PatchPage));*/
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("请选择");
        fileChooser.setInitialDirectory(
//                new File(System.getProperty("user.home"))//用户文件夹
                FileSystemView.getFileSystemView() .getHomeDirectory()//桌面
        );
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ZIP","*.zip"));
        fileChooser.setInitialFileName(getExtractFileName());
        return fileChooser.showSaveDialog(Cache.STAGE.get(Cache.Name.PatchPage));
    }
    public String getExtractFileName(){
        return selectedProject.getName()+
                new SimpleDateFormat("yyyyMMddhhmm").format(new Date())+"_"+selectedDemand.getDemandName();
    }

    public List<ZipFile> getCurrZipList() throws Exception{

        System.out.println(selectedDemand.toString());
        //获取所有补丁文件
        List<PatchVO> patchVOList= (List<PatchVO>) BaseDAO.getInstance().queryAllVO(PatchVO.class);
        //筛选补丁
        List<ZipFile> currDemandPatchs=new ArrayList<>();
        for(PatchVO patchVO:patchVOList){
            if(patchVO.getPk_demand().equals(selectedDemand.getPrimaryKey())){
                String filePath=patchVO.getPath();
                String fileEncode = EncodeUtil.getEncode(filePath,true);
                currDemandPatchs.add(new ZipFile(new File(filePath), fileEncode));
            }
        }
        return currDemandPatchs;
    }
}
