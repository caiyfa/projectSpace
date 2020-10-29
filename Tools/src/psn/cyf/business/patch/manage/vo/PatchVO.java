package psn.cyf.business.patch.manage.vo;

import psn.cyf.base.vo.BaseVO;

import java.lang.Override;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatchVO extends BaseVO {
    private String pk_patch;
    private String pk_project;
    private String pk_demand;
    private String name;
    private  String path;
    /**
     * 版本
     */
    private String version;
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }



    public String getPk_demand() {
        return pk_demand;
    }

    public void setPk_demand(String pk_demand) {
        this.pk_demand = pk_demand;
    }

    public String getPk_patch() {
        return pk_patch;
    }

    public void setPk_patch(String pk_patch) {
        this.pk_patch = pk_patch;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
        try {
            if(version !=null&&version.trim().length()!=0){
            Date date=sdf.parse(version);
            sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
            String transferStr=sdf.format(date);
            return name + " \t" + transferStr ;
        }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  name + " \t" + version ;
    }

    @Override
  public   String getPrimaryKey() {
        return pk_patch;
    }


    @Override
    public  String getPkField() {
        return "pk_patch";
    }
}
