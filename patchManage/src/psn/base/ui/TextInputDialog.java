package psn.base.ui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import psn.base.img.ResourceAnchor;
import psn.base.utils.UIUtil;

import java.lang.Double;
import java.lang.String;

public class TextInputDialog extends Dialog<String> {
    private   GridPane grid=null;
    private   Label label=null;
    private   TextField textField=null;
    public TextInputDialog setReadOnly(){
        if(grid!=null){

            grid.setStyle(UIUtil.Style.Colors);

        }
        return this;
    }

    public TextInputDialog(String defaultValue) {
        final DialogPane dialogPane = getDialogPane();
        // Get the Stage.
        Stage stage = (Stage) getDialogPane().getScene().getWindow();

// Add a custom icon.
        stage.getIcons().add(UIUtil.getImage(ResourceAnchor.icon));

        this.textField = new TextField(defaultValue);
        this.textField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        GridPane.setFillWidth(textField, true);
        // -- label
        label = new Label(defaultValue);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);
        label.getStyleClass().add("content");
        label.setWrapText(true);
        label.setPrefWidth(360);
        label.setPrefWidth(Region.USE_COMPUTED_SIZE);
        label.textProperty().bind(dialogPane.contentTextProperty());

        this.grid = new GridPane();
        this.grid.setHgap(10);
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
    private void updateGrid() {
        grid.getChildren().clear();

        grid.add(label, 0, 0);
        grid.add(textField, 1, 0);
        getDialogPane().setContent(grid);

        Platform.runLater(() -> textField.requestFocus());
    }
}
