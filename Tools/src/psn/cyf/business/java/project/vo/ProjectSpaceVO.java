package psn.cyf.business.java.project.vo;

import psn.cyf.base.vo.BaseVO;

public class ProjectSpaceVO extends BaseVO {
    private String projectPath;
    private String projectName;
    private String projectId;

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String getPrimaryKey() {
        return projectId;
    }

    @Override
    public String getPkField() {
        return "projectId";
    }

    @Override
    public String toString() {
        return projectName;
    }
}
