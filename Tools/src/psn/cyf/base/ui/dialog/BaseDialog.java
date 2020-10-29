package psn.cyf.base.ui.dialog;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import psn.cyf.cache.Cache;
import psn.cyf.img.ResourceAnchor;
import psn.cyf.utils.UIUtil;

public class BaseDialog<T> extends Dialog<T>  {
    private GridPane grid=null;
    private Text text =null;
    protected void initPage( ) {
        final DialogPane dialogPane = getDialogPane();
        Stage stage= (Stage) dialogPane.getScene().getWindow();
        stage.setTitle("提示");
        //设置所属页面
        stage.initOwner(Cache.STAGE.get(Cache.Name.WholePage));
        stage.getIcons().add(UIUtil.getImage(ResourceAnchor.icon));
        dialogPane.setStyle(UIUtil.Style.Colors);
        stage.initStyle(StageStyle.UTILITY);


        this.grid = new GridPane();

        this.grid.setHgap(10);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER);

         //默认OK和关闭按钮
        dialogPane.getButtonTypes().addAll(ButtonType.OK,ButtonType.CLOSE);
    }
    public void setDialogWidth(double width){
        getDialogPane().setPrefWidth(width);
    }
    public void setDialogTitle(String title){
        ((Stage)getDialogPane().getScene().getWindow()).setTitle(title);
    }

    public void setButtonTypes(ButtonType ... buttonTypes){
        getDialogPane().getButtonTypes().clear();
        getDialogPane().getButtonTypes().addAll(buttonTypes);
    }
    protected void updateGrid( ){
        grid.add(text, 0, 0);
        getDialogPane().setContent(grid);
    }
    public  void setMessage(String message){
        text=new Text(message);
        updateGrid( );
    }

    /**
     * 清洗页面
     */
    public void clearPane(){
        grid.getChildren().clear();
    }

    protected GridPane getGrid() {
        return grid;
    }

    public Text getText() {
        return text;
    }
}
