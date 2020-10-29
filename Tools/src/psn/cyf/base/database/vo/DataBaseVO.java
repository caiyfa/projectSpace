package psn.cyf.base.database.vo;

import psn.cyf.base.vo.BaseVO;

public class DataBaseVO extends BaseVO {
    private String sourceName;
    private String pk_dataBase;
    private String ip;
    private String port;
    private String user;
    private String password;
    private String instance;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getPk_dataBase() {
        return pk_dataBase;
    }

    public void setPk_dataBase(String pk_dataBase) {
        this.pk_dataBase = pk_dataBase;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPrimaryKey() {
        return pk_dataBase;
    }

    @Override
    public String getPkField() {
        return "pk_dataBase";
    }

    @Override
    public String toString() {
        return sourceName;
    }
    public String toShowString(){
        return ip+":"+port+" "+instance+"@"+user;
    }
}
