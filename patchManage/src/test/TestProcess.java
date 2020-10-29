package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestProcess {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("tasklist");
            System.out.println(p);
            //这里新建输入流的时候需要设置编码方式，否则会中文乱码
            BufferedReader bw = new BufferedReader(new InputStreamReader(p
                    .getInputStream(),"GBk"));
            String s;
            while ((s=bw.readLine())!=null) {
                System.out.println(new String (s.getBytes()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
