/*
package psn.cyf.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import psn.base.cache.Cache;
import psn.base.ctrl.PatchCtrl;
import psn.base.dao.BaseDAO;
import psn.base.exception.RunException;
import psn.base.ui.TextInputDialog;
import psn.base.ui.eventhandler.ExtractDemandEventHandler;
import psn.base.ui.eventhandler.ExtractProjectEventHandler;
import psn.base.vo.PatchVO;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;


public class PatchRightMenu extends ContextMenu {
    private static PatchRightMenu instance=null;
    private   String pk_patch;
    private PatchRightMenu(String pk_patch) {
        this.pk_patch=pk_patch;
        if(this.pk_patch==null||this.pk_patch.trim().length()==0){
            MenuItem extractProjectMenuItem=makeMenuItemItem("提取项目");
            MenuItem extractDemandMenuItem = makeMenuItemItem("提取需求补丁");
            extractProjectMenuItem.setOnAction(new ExtractProjectEventHandler());
            extractDemandMenuItem.setOnAction(new ExtractDemandEventHandler());
            getItems().add(extractProjectMenuItem);
            getItems().add(extractDemandMenuItem);
            return;
        }
        MenuItem openFileMenuItem = makeMenuItemItem("打开");
        MenuItem downloadFileMenuItem=makeMenuItemItem("下载");
        MenuItem renameFileMenuItem = makeMenuItemItem("重命名");
        MenuItem deleteFileMenuItem = makeMenuItemItem("删除");

        openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pk_patch= PatchRightMenu.super.getOwnerNode().getId();

                try {
                    PatchVO patchVO= (PatchVO) BaseDAO.getInstance().queryVOByPrimaryKey(PatchVO.class,pk_patch);
                    Desktop.getDesktop().open(new File(patchVO.getPath()));
                } catch (RunException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        downloadFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser=new FileChooser();
                fileChooser.setTitle("请选择");
                fileChooser.setInitialDirectory(
                        */
/*new File(System.getProperty("user.home"))//用户文件夹*//*

                        FileSystemView.getFileSystemView() .getHomeDirectory()//桌面
                );
                String pk_patch=  PatchRightMenu.super.getOwnerNode().getId();
                PatchVO currPatch=null;
                try {
                    currPatch  = (PatchVO) BaseDAO.getInstance().queryVOByPrimaryKey(PatchVO.class,pk_patch);
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ZIP","*.zip"));
                    fileChooser.setInitialFileName(currPatch.getName());
                    File selectedFile= fileChooser.showSaveDialog(Cache.STAGE.get(Cache.Name.PatchPage));
                    //showOpenMultipleDialog FileChooser 的这个方法可以选择多个文件
                    if(selectedFile==null){
                        return;
                    }
                    String newPath=selectedFile.getAbsolutePath();
                    if(selectedFile.exists()){
                        selectedFile.delete();
                    }
                    FileInputStream fis=new FileInputStream(new File(currPatch.getPath()));
                    FileOutputStream fos=new FileOutputStream(new File(newPath));
                    byte[] buff=new byte[1024];
                    int num=0;
                    while ((num=fis.read(buff))!=-1){
                        fos.write(buff,0,num);
                    }
                    fis.close();
                    fos.flush();
                    fos.close();
                    System.out.println(currPatch.getName()+" 下载至 "+newPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        renameFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    String pk_patch=  PatchRightMenu.super.getOwnerNode().getId();
                    PatchVO patchVO= (PatchVO) BaseDAO.getInstance().queryVOByPrimaryKey(PatchVO.class,pk_patch);
                    final String oldName= patchVO.getName();
                    TextInputDialog inputDialog=new TextInputDialog(oldName);
                    inputDialog.setTitle("重命名附件:"+oldName);
                    Optional result= inputDialog.showAndWait();
                    if(result.isPresent()) {
                        String new_name= (String) result.get();
                        if(!oldName.equals(new_name)){
                            patchVO.setName(new_name);
                            BaseDAO.getInstance().updateVO(patchVO);
                            ((PatchCtrl) Cache.CONTROLLER.get(Cache.Name.PatchCtrl)).updatePatchView();

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        //删除文件
        deleteFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pk_patch=  PatchRightMenu.super.getOwnerNode().getId();
                PatchCtrl ctrl= (PatchCtrl) Cache.CONTROLLER.get(Cache.Name.PatchCtrl);

                try {
                    String ziFileName=( (PatchVO) BaseDAO.getInstance().queryVOByPrimaryKey(PatchVO.class,pk_patch)).getPrimaryKey();
                    FileUtil.deleteFile(ziFileName+".zip");
                    BaseDAO.getInstance().deleteVOByPK(PatchVO.class,pk_patch);
                    ctrl.updatePatchView();
                } catch (RunException e) {
                    e.printStackTrace();
                }

            }
        });
        getItems().add(openFileMenuItem);
        getItems().add(downloadFileMenuItem);
        getItems().add(renameFileMenuItem);

        getItems().add(deleteFileMenuItem);

    }
    private MenuItem makeMenuItemItem(String name){
        MenuItem menuItem=new MenuItem(name);
        menuItem.setStyle(UIUtil.Style.Translucent);
        return  menuItem;

    }

    public static PatchRightMenu getInstance(String pk_patch){
        instance=null;


            instance=new PatchRightMenu(pk_patch);

        return instance;
    }

}
*/
