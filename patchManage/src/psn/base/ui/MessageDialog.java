package psn.base.ui;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import psn.base.cache.Cache;
import psn.base.img.ResourceAnchor;
import psn.base.utils.UIUtil;

public class MessageDialog extends Dialog<String> {
    //public Label messageLabel=null;
    private GridPane grid=null;
    private   Label label=null;
    public MessageDialog(String defaultValue) {
        final DialogPane dialogPane = getDialogPane();
        Stage stage= (Stage) dialogPane.getScene().getWindow();
       stage.initOwner(Cache.STAGE.get(Cache.Name.WholePage));//设置所属页面
        stage.getIcons().add(UIUtil.getImage(ResourceAnchor.icon));
        dialogPane.setStyle(UIUtil.Style.Colors);
       stage.initStyle(StageStyle.UTILITY);

        // -- label
        label = new Label(defaultValue);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);
        label.getStyleClass().add("content");
      //  label.setWrapText(true);
        //label.setPrefWidth(360);
       // label.setPrefWidth(Region.USE_COMPUTED_SIZE);
       // label.textProperty().bind(dialogPane.contentTextProperty());

        this.grid = new GridPane();

        this.grid.setHgap(10);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER);
        updateGrid(defaultValue);
        //dialogPane.contentTextProperty().addListener(o -> updateGrid(defaultValue));
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
    }
    private void updateGrid(String msg){
        grid.getChildren().clear();

        grid.add(label, 0, 0);
        getDialogPane().setContent(grid);
    }


}
