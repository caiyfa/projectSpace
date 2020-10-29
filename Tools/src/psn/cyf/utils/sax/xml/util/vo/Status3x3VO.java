package psn.cyf.utils.sax.xml.util.vo;

import psn.cyf.base.vo.BaseVO;

public class Status3x3VO extends BaseVO {
    private String status;
    private String reason;
    private  String place;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Status3x3VO{" +
                "status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", place='" + place + '\'' +
                '}';
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
