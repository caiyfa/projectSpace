package psn.cyf.thread.interruped;

import java.util.concurrent.TimeUnit;

public class Interrupted2 {
    public static void main(String[] args) {
        //判断当前线程是否被中断
        System.out.printf("Main thread is interrupted ? %s \n",Thread.interrupted());
        //中断当前线程
        Thread.currentThread().interrupt();
        //判断当前线程是否已被中断
        System.out.printf("Main thread is interrupted ? %s  \n",Thread.currentThread().interrupted());
        //当前线程执行可中断方法
        try {
//            Thread.currentThread().interrupt();
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("已被中断");
        }


    }
}
