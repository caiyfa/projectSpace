package psn.cyf.business.login.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import psn.cyf.business.login.ctrl.LoginHelperCtrl;
import psn.cyf.cache.Cache;
import psn.cyf.utils.FileUtil;


import java.io.*;
import java.util.List;

public class LoginHelperCSVDragDroppedHandler implements EventHandler {
    public static String cacheCSVFileName= FileUtil.getCacheDir()+File.separator+ LoginHelperCtrl.CSVFileName;
    @Override
    public void handle(Event event) {
        DragEvent dragEvent= (DragEvent) event;
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragEvent.isAccepted()) {
            List<File> fileList = dragboard.getFiles(); //获取拖入的文件
            if(fileList==null||fileList.size()==0){
                return;
            }
            File file=new File(cacheCSVFileName);
            if(file.exists()){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("是否覆盖？");

                if(alert.showAndWait().get().equals(ButtonType.OK)){
                    file.delete();
                    copyFile(fileList.get(0));
                }

            }else{
                copyFile(fileList.get(0));
            }

        }
        System.out.println("LoginHelperCSVDragDroppedHandler");

    }
    public void copyFile(File srcFile){
        FileInputStream fileInputStream=null;
        FileOutputStream fileOutputStream=null;
        try {
              fileInputStream=new FileInputStream(srcFile);
             fileOutputStream=new FileOutputStream(new File(cacheCSVFileName));
            byte[] buffer=new byte[1024*8];
            int len=0;
            while ((len=fileInputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer,0,len);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

                try {
                    if(fileInputStream!=null){
                    fileInputStream.close();
                    }
                    if(fileOutputStream!=null){
                        fileOutputStream.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
             //清理CSV缓存
            ((LoginHelperCtrl) Cache.CONTROLLER.get(Cache.Name.LoginHelperCtrl)).clearCSVCache();

        }

    }
}
