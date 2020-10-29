package psn.cyf.img;


import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestScrollBar extends Application {
    final ScrollBar scrollBar = new ScrollBar();
//    final Image[] images = new Image[5];
//    final ImageView[] pics = new ImageView[5];
    final VBox vBox = new VBox();
//    DropShadow shadow = new DropShadow();
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 180);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Scrollbar");

        for(int i=0;i<20;i++){
            Button button=new Button(i+"");
            root.getChildren().addAll(button);
        }
        root.getChildren().addAll(vBox, scrollBar);

//        shadow.setColor(Color.GREY);
//        shadow.setOffsetX(2);
//        shadow.setOffsetY(2);

        vBox.setLayoutX(5);
        vBox.setSpacing(10);

        scrollBar.setLayoutX(scene.getWidth() - scrollBar.getWidth());//设置滚动条横向位置
        scrollBar.setMin(0);
        scrollBar.setOrientation(Orientation.VERTICAL);//设置滚动条垂直方向
        scrollBar.setPrefHeight(100);//滚动条长短
        scrollBar.setMax(3000);

//        scrollBar.setUnitIncrement(100.0);
//        scrollBar.setBlockIncrement(50.0);

        for (int i = 0; i < 50; i++) {
//            final Image image = images[i] = new Image(getClass()
//                    .getResourceAsStream(getImage(i)));
//            final ImageView pic = pics[i] = new ImageView(images[i]);
//            pic.setEffect(shadow);
//            vb.getChildren().add(pics[i]);
            Button button =new Button(i+"");
            vBox.getChildren().add(button);

        }

        //在滚动条移动时移动vBox   滚动条的灵魂
        scrollBar.valueProperty().addListener((ov, old_val, new_val) -> {
            vBox.setLayoutY(-new_val.doubleValue());
        });

        stage.show();

    }
    public String getImage(int i){
        switch (i){
            case 0:{
                return "321762.jpg";
            }
            case 1:{
                return "1000579.jpg";
            }
            case 2:{
                return "folder.jpg";
            }
            case 3:{
                return "ironBtn.jpg";
            }
            case 4:{
                return "folder2.jpg";
            }
        }
        return null;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
