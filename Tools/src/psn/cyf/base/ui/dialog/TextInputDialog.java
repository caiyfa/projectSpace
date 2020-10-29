package psn.cyf.base.ui.dialog;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import psn.cyf.img.ResourceAnchor;
import psn.cyf.utils.UIUtil;

public class TextInputDialog extends Dialog<String> {
    private GridPane grid = null;
    private Label label = null;
    private Text text = null;
    private TextField textField = null;

    public TextInputDialog setReadOnly() {
        if (grid != null) {

            grid.setStyle(UIUtil.Style.Colors);

        }
        return this;
    }
public void setDialogWidth(double width){
    getDialogPane().setPrefWidth(width);
}
    public TextInputDialog(String title) {
        final DialogPane dialogPane = getDialogPane();
        //设置文件弹出框宽度
        dialogPane.setPrefWidth(500);
        // Get the Stage.
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.setTitle(title);
        //设置角标
        stage.getIcons().add(UIUtil.getImage(ResourceAnchor.icon));

        this.textField = new TextField();
        this.textField.setMaxWidth(Double.MAX_VALUE);
        this.textField.setPrefWidth(50);

        GridPane.setHgrow(textField, Priority.ALWAYS);
        GridPane.setFillWidth(textField, true);
        // -- label
        label = new Label();
        label.setText("请输入");

        label.getStyleClass().add("content");
        label.setWrapText(true);
        label.setPrefWidth(30);
//        label.setPrefWidth(Region.USE_COMPUTED_SIZE);
        label.textProperty().bind(dialogPane.contentTextProperty());
        text = new Text();
        text.setText("请输入"+title);
        this.grid = new GridPane();
        this.grid.setHgap(10);
        this.grid.setPrefWidth(100);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER);
        dialogPane.contentTextProperty().addListener(o -> updateGrid());
//        dialogPane.getStyleClass().add("text-input-dialog");
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        updateGrid();
        setResultConverter((dialogButton) -> {
            ButtonBar.ButtonData data = dialogButton == null ? null : dialogButton.getButtonData();
            return data == ButtonBar.ButtonData.OK_DONE ? textField.getText() : null;
        });
    }

    public String getData() {
        return this.textField.getText();
    }

    private void updateGrid() {
        grid.getChildren().clear();

        grid.add(text, 0, 0);
        grid.add(textField, 1, 0);
        getDialogPane().setContent(grid);

        Platform.runLater(() -> textField.requestFocus());
    }
}
