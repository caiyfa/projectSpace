package psn.cyf.test.threadlocal;

public class TestThreadLocal {
    public static void main(String[] args) {
        System.out.println("当前线程"+Thread.currentThread().getId()+" 数据源："+DataSourceUtil.getDataSource().getName());
        new Thread(()->{
            System.out.println("当前线程"+Thread.currentThread().getId()+" 数据源："+DataSourceUtil.getDataSource().getName());
        }).start();
        new Thread(()->{
            System.out.println("当前线程"+Thread.currentThread().getId()+" 数据源："+DataSourceUtil.getDataSource().getName());
        }).start();
    }
}
