package psn.cyf.thread.interruped;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        long t1=System.currentTimeMillis();
            Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                 while (true){
                        //sleep是可中断方法它会捕捉到中断信号并擦除中断标志
                         TimeUnit.MINUTES.sleep(1);

                 } } catch (InterruptedException e) {
                    System.out.printf("线程被阻断：%s \n",isInterrupted());
                }
            }
        };
            //设置线程为守护线程，随主线程的结束而自动结束
        //守护线程在JVM中没有非守护线程的时候会自动退出
        //应用场景：适合做后台数据处理，在主线程关闭的时候对应后台数据处理也不需要执行的情况
//        thread.setDaemon(true);
        thread.start();
        //shortly block make sure the thread is started.
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("线程是否中断： ? %s\n",thread.isInterrupted());
        thread.interrupt();
        //线程被中断后第一次调用isInterrupted 方法会是true，随后被sleep方法捕获并复位，
        //随后除非再次被中断，否则就会一直是false
        System.out.printf("线程是否中断： ? %s\n",thread.isInterrupted());
        System.out.printf("线程是否中断： ? %s\n",thread.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("线程是否中断： ? %s\n",thread.isInterrupted());

        long t2=System.currentTimeMillis();
        System.out.println("耗时："+(t2-t1)/1000+"s");

    }
}
