package psn.cyf.utils;

import psn.cyf.business.multi.MainPageCtrl;

import java.net.URL;

public class FxmlUtils {
    private static String fxmlType=".fxml";
    public static URL getFxml(Class cls){
        if(cls==null){
            throw new NullPointerException("获取fxml文件时类参数不能为空");
        }
        return cls.getResource(cls.getSimpleName()+fxmlType);
    }
    public static URL getFxmlFromCtrlName(Class cls){
        if(cls==null){
            throw new NullPointerException("获取fxml文件时类参数不能为空");
        }

        return cls.getResource(cls.getSimpleName().substring(0,cls.getSimpleName().length()-4)+fxmlType);
    }

    /*public static void main(String[] args) {
        URL url=getFxmlFromCtrlName(MainPageCtrl.class);
        //System.out.println(url.getPath());
    }*/
}
