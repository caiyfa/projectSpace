package psn.cyf.utils.sax.xml.util.vo;

import psn.cyf.base.vo.BaseVO;

public class ParcelVO extends BaseVO {
    private String shipmentID;
    private String barcodeType;
    private String ref1;
    private String ref2;
    private EventVO event;

    public ParcelVO() {
        event=new EventVO();
    }

    public String getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(String shipmentID) {
        this.shipmentID = shipmentID;
    }

    public String getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(String barcodeType) {
        this.barcodeType = barcodeType;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public EventVO getEvent() {
        return event;
    }

    public void setEvent(EventVO event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "ParcelVO{" +
                "shipmentID='" + shipmentID + '\'' +
                ", barcodeType='" + barcodeType + '\'' +
                ", ref1='" + ref1 + '\'' +
                ", ref2='" + ref2 + '\'' +
                ", event=" + event.toString() +
                '}';
    }

    @Override
    public String getPrimaryKey() {
        return shipmentID;
    }

    @Override
    public String getPkField() {
        return "shipmentID";
    }
}
