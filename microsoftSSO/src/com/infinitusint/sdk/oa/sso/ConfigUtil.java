package com.infinitusint.sdk.oa.sso;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取工具类
 */
public class ConfigUtil {
    private static final Properties properties = new Properties();
    //配置文件名
    private static final  String config = "sso.properties";
    //登录url
    private static final  String SSO_URL = "ssoUrl";
    //获到用户信息
    private static final  String TOKEN_URL= "tokenUrl";
    //退出登录
    private static final  String LOGOUT_URL= "logoutUrl";
    //应用ID
    private static final  String APP_ID = "appId";
    //应用密钥
    private static final  String APP_KEY = "appKey";

    static {
//       final InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
        final InputStream resourceAsStream = ConfigUtil.class.getResourceAsStream(config);

        try {
            if(resourceAsStream==null)
                throw new RuntimeException("未找到"+ config + "文件");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getPropertie(String key){
        return (String)properties.getProperty(key);
    }

    public static String getSSOUrl(){
        return getPropertie(SSO_URL);
    }

    public static String getTokenUrl(){
        return getPropertie(TOKEN_URL);
    }

    public static String getAppID(){
        return getPropertie(APP_ID);
    }

    public static String getAppKey(){
        return getPropertie(APP_KEY);
    }

    public static String getLogoutUrl(){
        return getPropertie(LOGOUT_URL);
    }

}
