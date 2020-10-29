package psn.cyf.utils;

import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import psn.cyf.base.css.CSSAnchor;
import psn.cyf.img.ResourceAnchor;

import java.net.URL;

public class UIUtil {
    /**
     * 样式
     */
    public static class Style{
        /**
         * 半透明
         */
        public static String Translucent="-fx-background-color: rgba(255,255,255,0.5)";
        /**
         * 渐变彩色
         */
        public static  String  Colors="-fx-background-color: linear-gradient(to right,#53bdff,#ffbf5a);";
    }

    /**
     * 标题
     */
    public static class Title{
        public static String Main="工具箱";
        public static String Project="项目管理";
        public static String Patch="补丁管理";
        public static String Demand="需求管理";

        public static String MultiPage="Tools";

    }

    /**
     * 根据图片名获取
     * @param imageName
     * @return
     */
    public static Image getImage(String imageName){
        URL url= ResourceAnchor.class.getResource(imageName);
//        String str=url.getFile();
//        File file = new File(str);
//        boolean flag=file.exists();
//        String localUrl = file.toURI().toString();
//        Image image=new Image(localUrl,false);
        return new Image(url.toString(),false);
    }

    /**
     * 统一风格设置
     * @param stage
     * @param title
     * @param parent
     */
    public static void initStage(Stage stage, String title, Parent parent){
        Scene scene=new Scene(parent);
        //设置光标图片
        scene.setCursor(new ImageCursor(UIUtil.getImage(ResourceAnchor.cursorImg),20,20));
        scene.getStylesheets().add(CSSAnchor.class.getResource("TabPane.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle(title);

//        stage.setWidth(660.0);
//        stage.setHeight(369.0);
        stage.setResizable(false);
//        stage.initStyle(StageStyle.UTILITY);

        Image image= UIUtil.getImage("title.png");
        stage.getIcons().add(image);//设置工具图标
    }
    public static Background getBackground(String img){
        Image image = UIUtil.getImage(img);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return new Background(myBI);
    }

}

