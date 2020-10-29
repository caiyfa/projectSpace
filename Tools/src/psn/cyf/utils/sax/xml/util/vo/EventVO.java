package psn.cyf.utils.sax.xml.util.vo;

import psn.cyf.base.vo.BaseVO;

public class EventVO extends BaseVO {
    private String eventTimestamp;
    private Status3x3VO status3x3;
    private Status2x2VO status2x2;
    private ScanningUnitVO scanningUnit;
    private String tour;

    public EventVO() {
        status3x3=new Status3x3VO();
        status2x2=new Status2x2VO();
        scanningUnit=new ScanningUnitVO();
    }

    public String getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Status3x3VO getStatus3x3() {
        return status3x3;
    }

    public void setStatus3x3(Status3x3VO status3x3) {
        this.status3x3 = status3x3;
    }

    public Status2x2VO getStatus2x2() {
        return status2x2;
    }

    public void setStatus2x2(Status2x2VO status2x2) {
        this.status2x2 = status2x2;
    }

    public ScanningUnitVO getScanningUnit() {
        return scanningUnit;
    }

    public void setScanningUnit(ScanningUnitVO scanningUnit) {
        this.scanningUnit = scanningUnit;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "EventVO{" +
                "eventTimestamp='" + eventTimestamp + '\'' +
                ", status3x3=" + status3x3.toString() +
                ", status2x2=" + status2x2.toString() +
                ", scanningUnit=" + scanningUnit.toString() +
                ", tour='" + tour + '\'' +
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
