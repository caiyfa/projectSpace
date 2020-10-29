package psn.cyf.business.project.vo;

import psn.cyf.base.vo.BaseVO;

import java.lang.Override;
import java.lang.String;

public class ProjectVO  extends BaseVO {
    private String pk_project;
    private String name;
    private String code;
    private String birthday;
    private String lastEditDate;

    public ProjectVO() {
    }

    public ProjectVO(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(String lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getPk_project() {
        return pk_project;
    }

    public void setPk_project(String pk_project) {
        this.pk_project = pk_project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name+" "+code;
    }

    @Override
    public String getPrimaryKey() {
        return pk_project;
    }


    @Override
    public String getPkField() {
        return "pk_project";
    }

}
