package test;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.util.Properties;
import java.util.Set;

public class TestDesk {
    public static void main(String[] args) {
//      String s=  System.getProperty("java.library.path");

        Properties properties= System.getProperties();
        Set<String> stringSet= properties.stringPropertyNames();
        for (String str:stringSet){
            System.out.println(str);
        }
        System.out.println(properties.getProperty("user.home"));
        System.out.println(properties.get("java.ext.dirs"));
    /*    ActiveXComponent app = new ActiveXComponent("Word.Application");
//        app.setProperty("Visible", new Variant(false));
        Dispatch word = app.getProperty("Documents").toDispatch();
        Dispatch doc = Dispatch.invoke(word, "Open", Dispatch.Method, new Object[] {"C:\\Users\\Dell\\Documents\\进项改造未完成记录.docx", new Variant(false), new Variant(false) }, new int[1]).toDispatch();
    */}
}
