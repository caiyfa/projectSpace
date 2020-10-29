package psn.cyf.utils;

public class StringUtils {
    public static boolean isNullWithTrim(String str){
        return (str==null||str.trim().length()==0)?true:false;
    }
    public static boolean isNotNullWithTrim(String str){
        return !isNullWithTrim(str);
    }
}
