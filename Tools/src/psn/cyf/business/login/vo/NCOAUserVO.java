package psn.cyf.business.login.vo;

import psn.cyf.base.vo.BaseVO;

public class NCOAUserVO extends BaseVO {
    private String ncCode;
    private String oaCode;
    private String userName;

    public String getNcCode() {
        return ncCode;
    }

    public void setNcCode(String ncCode) {
        this.ncCode = ncCode;
    }

    public String getOaCode() {
        return oaCode;
    }

    public void setOaCode(String oaCode) {
        this.oaCode = oaCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPrimaryKey() {
        return null;
    }

    @Override
    public String getPkField() {
        return null;
    }
}
