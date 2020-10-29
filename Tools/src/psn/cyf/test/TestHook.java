package psn.cyf.test;

import java.util.concurrent.TimeUnit;

/**
 * 程序结束前执行特定操作
 */
public class TestHook {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{

            System.out.println("程序结束"+Thread.currentThread().getId());
            System.out.println(TestHook.content);
        }));
    }
    static String content=null;
    public static void main(String[] args) throws InterruptedException {

        System.out.println("睡两秒"+Thread.currentThread().getId());
        content="存储";
        TimeUnit.SECONDS.sleep(2);
    }
}
