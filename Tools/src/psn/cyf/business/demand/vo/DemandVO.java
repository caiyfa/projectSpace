package psn.cyf.business.demand.vo;

import psn.cyf.base.vo.BaseVO;

import java.lang.Override;
import java.lang.String;

/**
 * demand  : 需求
 */
public class DemandVO extends BaseVO {
    private String pk_demand;
    /**
     * 提出日期
     */
    private String createTime;
    private String demandName;
    private String pk_project;
    /**
     * 需求描述
     */
    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPk_demand() {
        return pk_demand;
    }

    public void setPk_demand(String pk_demand) {
        this.pk_demand = pk_demand;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public String getPk_project() {
        return pk_project;
    }

    public void setPk_project(String pk_project) {
        this.pk_project = pk_project;
    }

    @Override
    public String getPrimaryKey() {
        return pk_demand;
    }



    @Override
    public String getPkField() {
        return "pk_demand";
    }

    @Override
    public String toString() {
        return demandName;
    }
}
