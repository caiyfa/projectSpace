package psn.base.ctrl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import psn.base.cache.Cache;
import psn.base.fxml.FxmlAnchor;
import psn.base.utils.UIUtil;
import psn.base.utils.VOHelper;
import test.EncryptUtil;

public class MultiPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent= FXMLLoader.load(FxmlAnchor.class.getResource(FxmlAnchor.multiPage));
        UIUtil.initStage(primaryStage,UIUtil.Title.MultiPage,parent);

        primaryStage.show();
        loadResource();
        Cache.STAGE.put(Cache.Name.WholePage,primaryStage);

    }
    private void loadResource(){
//在获取dao实例的时候另起线程获取一次UUID。为接下来的准备PK提高效率
        Thread thread= new Thread(() -> VOHelper.getUUID());
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EncryptUtil.getInstance().encode("");
                    EncryptUtil.getInstance().decode("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
thread1.start();
        thread.start();
    }
    public static void main(String[] args) {

        launch(args);
    }
}
