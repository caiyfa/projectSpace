package psn.cyf.test.combobox;

public class ComboCheckBoxEntry {
    boolean checked;
    boolean state;
    String uniqueCode;
    String value;

    public ComboCheckBoxEntry() {
        this.checked=false;
        this.state=true;
        this.uniqueCode="-1";
        this.value="Empty Entry";
    }

    public ComboCheckBoxEntry(String uniqueCode, String value) {
        this.checked=false;
        this.state=true;
        this.uniqueCode = uniqueCode;
        this.value = value;
    }

    public ComboCheckBoxEntry(boolean checked, String uniqueCode, String value) {
        this.checked = checked;
        this.state=true;
        this.uniqueCode = uniqueCode;
        this.value = value;
    }

    public ComboCheckBoxEntry(boolean checked, boolean state, String uniqueCode, String value) {
        this.checked = checked;
        this.state = state;
        this.uniqueCode = uniqueCode;
        this.value = value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
