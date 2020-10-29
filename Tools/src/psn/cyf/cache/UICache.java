package psn.cyf.cache;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;
import psn.cyf.base.ctrl.SuperCtrl;
import psn.cyf.exception.RunException;
import psn.cyf.utils.DataFileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class UICache {
    private   static  Map<String, Object> map = new HashMap<>();
    private static String UICACHE_JSON_FILE="uiCache";

    private static boolean isUseable=false;
    public static void main(String[] args) {
        loadCacheMap();
        for(String key:map.keySet()){
            System.out.println(map.get(key));
        }
    }
    public static  Map<String, Object> getMap(){
        if(isUseable){
            return map;
        }else {
            try {
//                System.out.println("等待装载界面缓存十毫秒");
                TimeUnit.MILLISECONDS.sleep(20);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getMap();
        }
    }
    public static  void putValue(SuperCtrl ctrl,String field,Object val){
        getMap().put(ctrl.getClass().getName()+"#"+field,val);
    }
    public static void toFile(){
        try {
            DataFileUtil.getInstance().writeMap(UICACHE_JSON_FILE,map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void loadCacheMap(){
        try {
            map=DataFileUtil.getInstance().readMap(UICACHE_JSON_FILE).toMap();
            isUseable=true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

