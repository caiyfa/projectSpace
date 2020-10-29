package psn.cyf.base.ui.eventhandler;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.util.List;

public class FileDragOverEventHandler implements EventHandler {
    String[] fileTypes=null;

    public FileDragOverEventHandler(String[] fileTypes) {
        this.fileTypes = fileTypes;
    }

    @Override
    public void handle(Event event) {
        DragEvent dragEvent= (DragEvent) event;
        Dragboard dragboard=dragEvent.getDragboard();
        if(dragboard.hasFiles()){
            List<File> fileList = dragboard.getFiles();
            boolean flag=true;
            for(File file:fileList){
                flag=false;
                for(String types:fileTypes){
                    if ((file.getAbsolutePath().endsWith(types))) { //用来过滤拖入类型
                        flag=true;

                    }
                }

            }
            if(fileList.size()!=1){
                flag=false;
//            Alert alert=new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("每次只允许插入一条数据");
//            alert.showAndWait();
            }
            if(flag){
                dragEvent.acceptTransferModes(TransferMode.COPY);//接受拖入文件
            }
        }
    }
}
