package psn.base.vo;

public class FunctionAuthVO extends BaseVO {
    private String pk_functionAuth;
    private Boolean visable;
    private String controller;
    private String style;
    private String font;

    @Override
    public String getPrimaryKey() {
        return pk_functionAuth;
    }

    @Override
    public String getPkField() {
        return "pk_functionAuth";
    }

    public String getPk_functionAuth() {
        return pk_functionAuth;
    }

    public void setPk_functionAuth(String pk_functionAuth) {
        this.pk_functionAuth = pk_functionAuth;
    }

    public Boolean getVisable() {
        return visable;
    }

    public void setVisable(Boolean visable) {
        this.visable = visable;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

}
