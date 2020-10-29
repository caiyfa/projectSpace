package psn.cyf.test.lambda;

import java.io.File;

public class TestLambda {
    public static void main(String[] args) {
        String dir="C:\\Users\\Dell\\Desktop\\WORK\\patch\\国盛证券65\\20191024NC65设备卡片多使用部门补丁";
        File base =new File(dir);
       File[] childs= base.listFiles((f)->{
           String n=f.getAbsolutePath();
            return !f.isDirectory()&&!n.endsWith("zip");
        });
       for(File f: childs)
        System.out.println(f.getName());
    }
}
