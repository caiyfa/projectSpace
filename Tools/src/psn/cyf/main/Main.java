
package psn.cyf.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.cache.Cache;
import psn.cyf.cache.UICache;
import psn.cyf.utils.FxmlUtils;
import psn.cyf.utils.UIUtil;
import psn.cyf.utils.VOHelper;
import psn.cyf.utils.EncryptUtil;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent= new MultiCtrl().loadFXML(MultiCtrl.class);
        UIUtil.initStage(primaryStage,UIUtil.Title.MultiPage,parent);
        primaryStage.show();
        loadResource();
        Cache.STAGE.put(Cache.Name.WholePage,primaryStage);


    }
    private void loadResource(){
//在获取dao实例的时候另起线程获取一次UUID。为接下来的准备PK提高效率
         new Thread(() -> VOHelper.getUUID()).start();
         new Thread( () -> {
            try {
                EncryptUtil.getInstance().encode("");
                EncryptUtil.getInstance().decode("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ).start();
         //装载界面缓存
         new Thread(()->UICache.loadCacheMap()).start();
         //装载程序结束的钩子
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            UICache.toFile();
        }));

    }
    public static void main(String[] args) {

        launch(args);
    }
}
