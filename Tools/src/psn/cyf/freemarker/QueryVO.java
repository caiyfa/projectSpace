package psn.cyf.freemarker;

import java.util.List;

public class QueryVO {
    private  String queryString ;
    private List<QueryResultVO> tableWithQuerySqlVOS;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public List<QueryResultVO> getTableWithQuerySqlVOS() {
        return tableWithQuerySqlVOS;
    }

    public void setTableWithQuerySqlVOS(List<QueryResultVO> tableWithQuerySqlVOS) {
        this.tableWithQuerySqlVOS = tableWithQuerySqlVOS;
    }
}
