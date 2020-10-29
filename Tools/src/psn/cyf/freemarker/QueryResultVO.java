package psn.cyf.freemarker;

public class QueryResultVO {
    public QueryResultVO() {
    }

    public QueryResultVO(Integer count, String tableName, String querySQL) {
        this.count = count;
        this.tableName = tableName;
        this.querySQL = querySQL;
    }

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }



    private String tableName;
    private String querySQL;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getQuerySQL() {
        return querySQL;
    }

    public void setQuerySQL(String querySQL) {
        this.querySQL = querySQL;
    }
}
