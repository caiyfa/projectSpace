package psn.base.vo;
import psn.lang.Boolean;
import psn.lang.Date;


public class MDTableVO extends BaseVO {
    /** 创建时间*/
    private Date createtime;
    /**创建者*/
    private String creator;
    /***/
    private String databaseid;
    /**描述*/
    private String description;
    /**删除标志位*/
    private Integer dr=0;
    private Boolean fromsourcebmf;
    private String help;
    private String id;
    private Boolean industry;
    private Boolean isactive;
    private Boolean isextendtable;
    /**
     * 修改人
     */
    private String modifier;
    private psn.lang.Date modifytime;
    private String name;
    private String parenttable;
    private String resid;
    private String resmodule;
    private psn.lang.Date ts;
    private Integer versiontype;
    @Override
    public String getPrimaryKey() {
        return id;
    }

    @Override
    public String getPkField() {
        return "id";
    }

    @Override
    public String getTableName() {
        return "md_table";
    }
}
