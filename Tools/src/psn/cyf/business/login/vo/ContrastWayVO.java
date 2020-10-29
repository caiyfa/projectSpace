package psn.cyf.business.login.vo;

import psn.cyf.base.vo.BaseVO;

public class ContrastWayVO extends BaseVO {
    private String contrastType;
    private String showName;

    public ContrastWayVO() {
    }

    public ContrastWayVO(String contrastType, String showName) {
        this.contrastType = contrastType;
        this.showName = showName;
    }

    public String getContrastType() {
        return contrastType;
    }

    public void setContrastType(String contrastType) {
        this.contrastType = contrastType;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Override
    public String toString() {
        return showName;
    }

    @Override
    public String getPrimaryKey() {
        return contrastType;
    }

    @Override
    public String getPkField() {
        return "contrastType";
    }


}
