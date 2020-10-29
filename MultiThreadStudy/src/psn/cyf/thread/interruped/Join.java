package psn.cyf.thread.interruped;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import  static java.util.stream.Collectors.toList;
public class Join {
    public static void main(String[] args) throws InterruptedException {
        //定义两个线程
        /*
        这个::是java 8里引入lambda后的一种用法，表示引用，比如静态方法的引用String::valueOf;
比如构造器的引用，ArrayList::new。
        */
        List<Thread>  threads=IntStream.range(1,3).mapToObj(Join::create).collect(toList());
        //启动这两个线程
        threads.forEach(Thread::start);
        //执行这两个线程的join方法
        /*
        join方法被主线程调用，因此在两个被调用线程执行完之前，主线程的输出不会执行
        两个子线程交替输出。
        若不调用下面的join方法则三个线程交替输出
        */
        for(Thread thread:threads){
            thread.join();
        }
        //main线程循环输出
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"#"+i);
            stortSleep();
        }

    }
    //构造两个线程
    private static Thread create(int seq){
        return new Thread(()->{
           for(int i=0;i<10;i++){
               System.out.println(Thread.currentThread().getName()+"#"+i);
             stortSleep();
           }
        },String.valueOf(seq));
    }
    private static void stortSleep(){
        try {
            //休眠一秒
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
