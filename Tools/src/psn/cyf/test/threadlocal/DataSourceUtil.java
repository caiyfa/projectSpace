package psn.cyf.test.threadlocal;

public class DataSourceUtil {
    private static final ThreadLocal<DataSource> dataSource=new ThreadLocal<>();
    public static DataSource getDataSource(){
        if(dataSource.get()==null){
            DataSource instance=new DataSource("default"+Thread.currentThread().getId());
            dataSource.set(instance);
        }
        return dataSource.get();
    }
}
