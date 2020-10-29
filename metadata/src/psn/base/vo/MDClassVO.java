package psn.base.vo;
import psn.lang.Boolean;
import java.util.Date;

public class MDClassVO extends BaseVO {
    /** 类名 */
    private String accessorClassName;
    /** 业务接口实现类 */
    private String bizitfimpClassName;
    /** 数据类型 */
    private Integer classType;
    /** 组件ID */
    private String componentId;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String creator;
    /** 默认表名 */
    private String defaultTableName;
    /** 描述 */
    private String description;
    /** 显示名称 */
    private String displayName;
    /** 删除标志位 */
    private Integer dr;
    /** 是否定长 */
    private Boolean fixedLength;
    /** 类限定名 */
    private String fullClassName;
    /** 帮助信息 */
    private String help;
    /** 主键 */
    private String id;
    /***/
    private String industry;
    /** 是否启用 */
    private Boolean isactive;
    /** 是否安全授权 */
    private Boolean isauthen;
    /***/
    private Boolean iscreatesql;
    /***/
    private Boolean isextendbean;
    /** 是否主实体*/
    private Boolean isprimary;
    /** 主键属性*/
    private String keyattribute;
    /** 修改人*/
    private String modifier;
    /**修改时间*/
    private Date modifytime;
    /** 元数据动态修改类*/
    private String modInfoClassName;
    /**名称*/
    private String name;
    /**父实体ID*/
    private String parentClassId;
    /**精度*/
    private Double precise;
    /**参照名称*/
    private String refmodelName;
    /**名称资源ID*/
    private String resid;
    /**返回类型*/
    private String returntype;
    /***/
    private String stereotype;
    /**时间戳*/
    private Date ts;
    /***/
    private String userdefclassname;
    /**版本*/
    private Double versiontype;
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
        return "md_class";
    }

    public String getAccessorClassName() {
        return accessorClassName;
    }

    public void setAccessorClassName(String accessorClassName) {
        this.accessorClassName = accessorClassName;
    }

    public String getBizitfimpClassName() {
        return bizitfimpClassName;
    }

    public void setBizitfimpClassName(String bizitfimpClassName) {
        this.bizitfimpClassName = bizitfimpClassName;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDefaultTableName() {
        return defaultTableName;
    }

    public void setDefaultTableName(String defaultTableName) {
        this.defaultTableName = defaultTableName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Boolean getFixedLength() {
        return fixedLength;
    }

    public void setFixedLength(Boolean fixedLength) {
        this.fixedLength = fixedLength;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Boolean getIsauthen() {
        return isauthen;
    }

    public void setIsauthen(Boolean isauthen) {
        this.isauthen = isauthen;
    }

    public Boolean getIscreatesql() {
        return iscreatesql;
    }

    public void setIscreatesql(Boolean iscreatesql) {
        this.iscreatesql = iscreatesql;
    }

    public Boolean getIsextendbean() {
        return isextendbean;
    }

    public void setIsextendbean(Boolean isextendbean) {
        this.isextendbean = isextendbean;
    }

    public Boolean getIsprimary() {
        return isprimary;
    }

    public void setIsprimary(Boolean isprimary) {
        this.isprimary = isprimary;
    }

    public String getKeyattribute() {
        return keyattribute;
    }

    public void setKeyattribute(String keyattribute) {
        this.keyattribute = keyattribute;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getModInfoClassName() {
        return modInfoClassName;
    }

    public void setModInfoClassName(String modInfoClassName) {
        this.modInfoClassName = modInfoClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentClassId() {
        return parentClassId;
    }

    public void setParentClassId(String parentClassId) {
        this.parentClassId = parentClassId;
    }

    public Double getPrecise() {
        return precise;
    }

    public void setPrecise(Double precise) {
        this.precise = precise;
    }

    public String getRefmodelName() {
        return refmodelName;
    }

    public void setRefmodelName(String refmodelName) {
        this.refmodelName = refmodelName;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public String getReturntype() {
        return returntype;
    }

    public void setReturntype(String returntype) {
        this.returntype = returntype;
    }

    public String getStereotype() {
        return stereotype;
    }

    public void setStereotype(String stereotype) {
        this.stereotype = stereotype;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getUserdefclassname() {
        return userdefclassname;
    }

    public void setUserdefclassname(String userdefclassname) {
        this.userdefclassname = userdefclassname;
    }

    public Double getVersiontype() {
        return versiontype;
    }

    public void setVersiontype(Double versiontype) {
        this.versiontype = versiontype;
    }
}
