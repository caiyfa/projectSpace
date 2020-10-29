package psn.cyf.business.demand.ctrl;


import psn.cyf.base.vo.BaseVO;

/**
 * 需求附件
 */
public class DemandAttachementVO extends BaseVO {
    private String pk_attachement;
    private String attachementName;
    /**
     * 源文件路径
     */
    private String sourceFilePath;
    private String pk_demand;
    /**
     * 文件后缀
     */
    private String fileSuffix;
    /**
     * 真实路径
     */
    private String truePath;

    public String getTruePath() {
        return truePath;
    }

    public void setTruePath(String truePath) {
        this.truePath = truePath;
    }

    /**
     * 上传时间
     */
    private String uploadTime;

    public String getPk_attachement() {
        return pk_attachement;
    }

    public void setPk_attachement(String pk_attachement) {
        this.pk_attachement = pk_attachement;
    }

    public String getAttachementName() {
        return attachementName;
    }

    public void setAttachementName(String attachementName) {
        this.attachementName = attachementName;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getPk_demand() {
        return pk_demand;
    }

    public void setPk_demand(String pk_demand) {
        this.pk_demand = pk_demand;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "DemandAttachementVO{" +
                "pk_attachement='" + pk_attachement + '\'' +
                ", attachementName='" + attachementName + '\'' +
                ", sourceFilePath='" + sourceFilePath + '\'' +
                ", pk_demand='" + pk_demand + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }

    @Override
    public String getPrimaryKey() {
        return pk_attachement;
    }



    @Override
    public String getPkField() {
        return "pk_attachement";
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }
}
