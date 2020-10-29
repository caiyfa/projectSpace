package psn.cyf.utils.file;

import java.io.File;

public class Mk {
    public static void main(String[] args) {
        File file=new File("D:\\NCHomes\\nc65home\\国盛\\0806\\ps\\client\\lib\\uips_apply.jar");
        System.out.println(file.exists());
        System.out.println(file.getParentFile().exists());
        System.out.println(file.getParentFile().mkdirs());
    }
}
