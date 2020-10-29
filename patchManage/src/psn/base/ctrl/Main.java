package psn.base.ctrl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import psn.base.cache.Cache;
import psn.base.fxml.FxmlAnchor;
import psn.base.utils.UIUtil;
import psn.base.utils.FontawesomeWithJavaFX;
import test.EncryptUtil;

import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.Thread;


public class Main /*extends Application*/ {
    /*@Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent= FXMLLoader.load(FxmlAnchor.class.getResource(FxmlAnchor.mainPage));
        UIUtil.initStage(primaryStage,UIUtil.Title.Main,parent);

        primaryStage.show();
        Cache.STAGE.put(Cache.Name.MainPage,primaryStage);
    }*/
//    public static void main(String[] args) {
//       //异步加载耗时资源
//        Thread thread= new Thread(() -> FontawesomeWithJavaFX.initInfo());
//        thread.start();
//        Thread thread1=new Thread(()-> {
//            try {
//                long t1=System.currentTimeMillis();
//                EncryptUtil.getInstance();
//                long t2=System.currentTimeMillis();
//                System.out.println("初始化加密工具耗时："+(t2-t1)+"ms");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        thread1.start();
//        launch(args);
//    }
}
