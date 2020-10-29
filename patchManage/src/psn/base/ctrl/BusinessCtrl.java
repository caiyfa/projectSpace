package psn.base.ctrl;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import psn.base.cache.Cache;
import psn.base.eventhandler.MouseClickHandler;
import psn.base.eventhandler.MouseEnterHandler;
import psn.base.eventhandler.MouseExitEventHandler;
import psn.base.img.ResourceAnchor;
import psn.base.utils.UIUtil;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BusinessCtrl implements Initializable {
    private ProgressIndicator progressIndicator;
    private Stage dialogStage;
    public void showLoading(){
        if(dialogStage==null){
            dialogStage=new Stage();
            progressIndicator = new ProgressIndicator();
            // 窗口父子关系
            dialogStage.initOwner(Cache.STAGE.get(Cache.Name.WholePage));
            dialogStage.initStyle(StageStyle.UNDECORATED);
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            // progress bar
            Label label = new Label("数据加载中, 请稍后...");
            label.setTextFill(Color.BLUE);
            progressIndicator.setProgress(-1F);
            VBox vBox = new VBox();
            vBox.setSpacing(10);
            vBox.setBackground(Background.EMPTY);
            vBox.getChildren().addAll(progressIndicator,label);
            Scene scene = new Scene(vBox);
            scene.setFill(null);
            dialogStage.setScene(scene);
        }
        dialogStage.show();
    }
    public void closeLoading(){
        if(dialogStage!=null){
            dialogStage.close();
        }
    }
    /**
     * 设置需要被修饰的Node
     * @return
     */
    protected abstract Node[] getDecorateArr();
    protected abstract Pane getPane();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(getPane()!=null)
            getPane().setBackground(UIUtil.getBackground(ResourceAnchor.bacImg));
        if(getDecorateArr()==null){
            return;
        }
        for(Node node:getDecorateArr()){
            node.setStyle(UIUtil.Style.Translucent);
            if(node.getClass().isAssignableFrom(Button.class)){
                node.setOnMouseEntered(new MouseEnterHandler());
                node.setOnMouseExited(new MouseExitEventHandler());
                node.setOnMouseClicked(new MouseClickHandler());
            }

        }

    }
}
