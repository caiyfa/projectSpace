package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TestWeatherAPI {
    public static void main(String[] args) throws Exception {
        json();
    }
    public static void json() throws Exception{
        //参数url化
        String city = java.net.URLEncoder.encode("北京", "utf-8");

        //拼地址
        String apiUrl = String.format("http://www.weather.com.cn/data/sk/101190408.html",city);
//        String apiUrl="http://www.weather.com.cn/data/sk/101190408.html";
        //开始请求
        URL url= new URL(apiUrl);
        URLConnection open = url.openConnection();
        InputStream input = open.getInputStream();
        //这里转换为String，带上包名，怕你们引错包
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(input,"utf-8"));
        StringBuilder stringBuilder=new StringBuilder();
        String tmp=null;
        while((tmp=bufferedReader.readLine())!=null){
            stringBuilder.append(tmp);
        }
        bufferedReader.close();
        System.out.println(stringBuilder.toString());



    }
}
