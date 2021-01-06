package psn.cyf.test;

import java.io.File;
import java.util.StringTokenizer;

public class TestSwitch {
    public static void main(String[] args) {
        String value="ABD";
        String formula="select * from sm_user where user_code=#";
        System.out.println(value.split("B").length);
        File[]  files=getExtDirs();
    }
    //ExtClassLoader类中获取路径的代码
    private static File[] getExtDirs() {
        //加载<JAVA_HOME>/lib/ext目录中的类库
        String s = System.getProperty("java.ext.dirs");
        File[] dirs;
        if (s != null) {
            StringTokenizer st =
                    new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
            }
        } else {
            dirs = new File[0];
        }
        return dirs;
    }


}
