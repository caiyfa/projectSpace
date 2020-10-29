package psn.cyf.base.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import psn.base.cache.Cache;
import psn.base.ctrl.DemandCtrl;
import psn.base.dao.BaseDAO;
import psn.base.exception.RunException;
import psn.base.ui.TextInputDialog;
import psn.base.utils.FileUtil;
import psn.base.utils.UIUtil;
import psn.base.vo.DemandAttachementVO;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Optional;

//import javafx.scene.control.TextInputDialog;

public class AttachementRightMenu extends ContextMenu{

//    /**
//     * 单例
//     */
//    private   AttachementRightMenu INSTANCE = null;

    private String ctrlName;
    private String pageName;

    public String getCtrlName() {
        return ctrlName;
    }

    public void setCtrlName(String ctrlName) {
        this.ctrlName = ctrlName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    private MenuItem makeMenuItemItem(String name){
        MenuItem menuItem=new MenuItem(name);
        menuItem.setStyle(UIUtil.Style.Translucent);
        return  menuItem;

    }

    /**
     * 私有构造函数
     */
    private AttachementRightMenu()
    {
        MenuItem openFileMenuItem = makeMenuItemItem("打开");
        MenuItem downloadFileMenuItem=makeMenuItemItem("下载");
        MenuItem renameFileMenuItem = makeMenuItemItem("重命名");
        MenuItem deleteFileMenuItem = makeMenuItemItem("删除");


        openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pk_DemAtt=  AttachementRightMenu.super.getOwnerNode().getId();
                try {
                    DemandAttachementVO currDem= (DemandAttachementVO) BaseDAO.getInstance().queryVOByPrimaryKey(DemandAttachementVO.class,pk_DemAtt);
                  String file=  currDem.getTruePath();
                    Desktop.getDesktop().open(new File(file));
                } catch (Exception e) {
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
                        /*new File(System.getProperty("user.home"))//用户文件夹*/
                        FileSystemView.getFileSystemView() .getHomeDirectory()//桌面
                );
                String pk_DemAtt=  AttachementRightMenu.super.getOwnerNode().getId();
                DemandAttachementVO currDem=null;
                try {
                    currDem  = (DemandAttachementVO) BaseDAO.getInstance().queryVOByPrimaryKey(DemandAttachementVO.class,pk_DemAtt);
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(currDem.getFileSuffix().substring(currDem.getFileSuffix().lastIndexOf(".")+1,currDem.getFileSuffix().length()),"*"+currDem.getFileSuffix()));
                    fileChooser.setInitialFileName(currDem.getAttachementName());
                    File selectedFile= fileChooser.showSaveDialog(Cache.STAGE.get(pageName));
                    //showOpenMultipleDialog FileChooser 的这个方法可以选择多个文件
                    if(selectedFile==null){
                        return;
                    }
                    String newPath=selectedFile.getAbsolutePath();
                    if(selectedFile.exists()){
                        selectedFile.delete();
                    }
                    FileInputStream fis=new FileInputStream(new File(currDem.getTruePath()));
                    FileOutputStream fos=new FileOutputStream(new File(newPath));
                    byte[] buff=new byte[1024];
                    int num=0;
                    while ((num=fis.read(buff))!=-1){
                        fos.write(buff,0,num);
                    }
                    fis.close();
                    fos.flush();
                    fos.close();
                    System.out.println(currDem.getAttachementName()+" 下载至 "+newPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        renameFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    String pk_DemAtt=  AttachementRightMenu.super.getOwnerNode().getId();
                    DemandAttachementVO attachementVO= (DemandAttachementVO) BaseDAO.getInstance().queryVOByPrimaryKey(DemandAttachementVO.class,pk_DemAtt);
                   final String oldName= attachementVO.getAttachementName();
                    TextInputDialog inputDialog=new TextInputDialog(attachementVO.getAttachementName());
                    inputDialog.setTitle("重命名附件:"+oldName);
                   Optional result= inputDialog.showAndWait();
                   if(result.isPresent()) {
                       String new_name= (String) result.get();
                        if(!oldName.equals(new_name)){
                            attachementVO.setAttachementName(new_name);
                            BaseDAO.getInstance().updateVO(attachementVO);
                            ((DemandCtrl) Cache.CONTROLLER.get(ctrlName)).refreshDetail();

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
                String pk_DemAtt=  AttachementRightMenu.super.getOwnerNode().getId();
                DemandCtrl ctrl= (DemandCtrl) Cache.CONTROLLER.get(ctrlName);

                try {
                    String showFileName=( (DemandAttachementVO) BaseDAO.getInstance().queryVOByPrimaryKey(DemandAttachementVO.class,pk_DemAtt)).getAttachementName();
                    FileUtil.deleteFile(pk_DemAtt+showFileName.substring(showFileName.lastIndexOf("."),showFileName.length()));
                    BaseDAO.getInstance().deleteVOByPK(DemandAttachementVO.class,pk_DemAtt);
                    ctrl.refreshDetail();
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

    /**
     * 获取实例
     * @return GlobalMenu
     */
    public static AttachementRightMenu getInstance(String ctrlName, String pageName)
    {
        AttachementRightMenu menu=new AttachementRightMenu();
        menu.setCtrlName(ctrlName);
        menu.setPageName(pageName);

        return  menu;
    }
}
