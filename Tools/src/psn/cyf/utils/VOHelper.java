package psn.cyf.utils;

import java.util.UUID;

public class VOHelper {
    public static String getUUID(){
//        long t1=System.currentTimeMillis();
        String pk= UUID.randomUUID().toString().replaceAll("-","");
//        System.out.println(pk);
//        long t2=System.currentTimeMillis();
//        System.out.println(t2-t1);

        return pk;
    }
}
