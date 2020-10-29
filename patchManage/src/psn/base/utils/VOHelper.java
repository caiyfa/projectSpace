package psn.base.utils;

import java.lang.String;
import java.util.UUID;

public class VOHelper {
    public static void main(String[] args) {
        System.out.println(getUUID().length());
    }
    public static String getUUID(){
//        long t1=System.currentTimeMillis();
        String pk= UUID.randomUUID().toString().replaceAll("-","");
//        System.out.println(pk);
//        long t2=System.currentTimeMillis();
//        System.out.println(t2-t1);

        return pk;
    }
}
