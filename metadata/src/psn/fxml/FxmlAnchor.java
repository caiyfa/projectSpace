package psn.fxml;

public enum  FxmlAnchor {
    MainPage("main.fxml")
   ;
    private String  fxmlName;

    FxmlAnchor(String fxmlName) {
        this.fxmlName = fxmlName;
    }

    @Override
    public String toString() {
        return fxmlName;
    }
}
