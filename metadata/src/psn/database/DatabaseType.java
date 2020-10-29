package psn.database;

public enum DatabaseType {
    Oracle11G("oracle.jdbc.OracleDriver");
    public  String driver;
    DatabaseType(String driver){
        this.driver=driver;
    }
    @Override
    public String toString() {
        return driver;
    }
}
