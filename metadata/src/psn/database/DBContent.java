package psn.database;

public class DBContent{
    private String userName;
    private String passWord;
    private String ip;
    private String port;
    private String dbInstance;
    private DatabaseType database;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbInstance() {
        return dbInstance;
    }

    public void setDbInstance(String dbInstance) {
        this.dbInstance = dbInstance;
    }

    public DatabaseType getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseType database) {
        this.database = database;
    }
}