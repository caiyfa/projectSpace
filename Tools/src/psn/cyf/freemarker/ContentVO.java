package psn.cyf.freemarker;

import psn.cyf.base.database.vo.DataBaseVO;

import java.util.List;

public class ContentVO {
    private DataBaseVO dbInfo;

    List<QueryVO> queryVOS;

    public DataBaseVO getDbInfo() {
        return dbInfo;
    }

    public void setDbInfo(DataBaseVO dbInfo) {
        this.dbInfo = dbInfo;
    }

    public List<QueryVO> getQueryVOS() {
        return queryVOS;
    }

    public void setQueryVOS(List<QueryVO> queryVOS) {
        this.queryVOS = queryVOS;
    }
}
