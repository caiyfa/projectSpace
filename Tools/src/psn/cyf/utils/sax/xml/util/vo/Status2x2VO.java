package psn.cyf.utils.sax.xml.util.vo;

import psn.cyf.base.vo.BaseVO;

public class Status2x2VO extends BaseVO {
    private String status;
    private String reason;

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

    @Override
    public String toString() {
        return "Status2x2VO{" +
                "status='" + status + '\'' +
                ", reason='" + reason + '\'' +
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
