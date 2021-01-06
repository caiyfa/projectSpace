package com.infinitusint.sdk.oa.sso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HttpUtil 请求工具类
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    /***
     * 初始化证书管理
     */
    static {
        try {
            TrustManager tm = new SSLTrustManager();
            TrustManager[] trustAllCerts = new TrustManager[]{tm};
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((HostnameVerifier) tm);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    public static String get(String strUrl) throws IOException {
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader= null;
        BufferedReader bufferedReader = null;
        try{
            URL url = new URL(strUrl);
            logger.info("http get:"+url);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.setDoInput(true);
            int code = connect.getResponseCode();
            logger.info("responseCode :"+code);
            if (code==200){
                // 将返回的输入流转换成字符串
                inputStream = connect.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                // 释放资源
                inputStream.close();
                inputStream = null;
                connect.disconnect();
                return buffer.toString();
            }
            return  null;
        } finally {
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStreamReader!=null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String post(String strUrl) throws IOException {
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader= null;
        BufferedReader bufferedReader = null;
        try{
            URL url = new URL(strUrl);
            logger.info("http get:"+url);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("POST");
            connect.setDoInput(true);
            int code = connect.getResponseCode();
            logger.info("responseCode :"+code);
            if (code==200){
                // 将返回的输入流转换成字符串
                inputStream = connect.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                // 释放资源
                inputStream.close();
                inputStream = null;
                connect.disconnect();
                return buffer.toString();
            }
            return  null;
        } finally {
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStreamReader!=null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
