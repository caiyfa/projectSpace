package psn.cyf.test;

import java.util.ArrayList;
import java.util.List;

public class TestArr {
    public static void main(String[] args) {
        for(int a=0;a<10;a++){
            List<Long> isNotList=new ArrayList<>();
            List<Long> isList=new ArrayList<>();
            double times=100.0;
            for(int time=0;time<times;time++){
//                System.out.println("第"+time+"轮");
                long t1=System.currentTimeMillis();
                int len=10000000;

                List<Integer> ar2=new ArrayList<>();
                for(int i=0;i<len;i++){
                    ar2.add(Integer.valueOf(i));
                }
                long t2=System.currentTimeMillis();
//            System.out.println("非自动拆装箱耗时"+(t2-t1)+"ms");
                isNotList.add((t2-t1));
                long t3=System.currentTimeMillis();
                List<Integer> arr=new ArrayList<>();
                for(int i=0;i<len;i++){
                    arr.add(i);
                }
                long t4=System.currentTimeMillis();
//            System.out.println("自动拆装箱耗时"+(t4-t3)+"ms");
                isList.add((t4-t3));

            }

            Long is=Long.valueOf(0);
            for(Long l:isList){
                is=is+l;
            }
            System.out.println("is:"+is+"ms  平均每轮"+is/times+"ms");

            Long isNot=Long.valueOf(0);
            for(Long l:isNotList){
                isNot=isNot+l;
            }
            System.out.println("isNot:"+isNot+"ms  平均每轮" +isNot/times+"ms");
        }

    }
}
