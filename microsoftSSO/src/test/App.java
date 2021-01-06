package test;

import com.infinitusint.sdk.oa.sso.SecurityDESUtil;
/*import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 * Created by Administrator on 2016/9/5.
 */
public class App {

    static    String text = "P+2i+wPpFWBGXeEPbYY1B8O/OJEgvV5C+bzQ+tsJIcWr5RFdk2LlgRJguqvdfs9eMGP55DhwkjcBVMCmglO8A9l4a/sbum77qwIwl0e0TEF48mUrGXFJazwE7xTGFtsKP0WD1Gey8LhXV++Jpu1n+lcQYwb89RjeX6dfD6OYWmldRLLaK2VT2+tCYCcEcMZxGo+DxqV15p4l3YnPBIjtNN5qizTXOghgNZrLsJkV4rFkdOObfN96ecO29YIwcpu6";

   static String token = "3029DE0B07E4488F19427F62BE57EC2A";

    /*public static void main(String[] args) throws IOException {
    String url = "https://api.infinitus-int.com/public/sso/generateSession?app_signature=mobileApp_1476303263241_406ED6E3429C715BFF54BED4581E48CC";
        String body = HttpUtil.post(url);
        System.out.println(body);

    }*/

   /* public static void main(String[] args) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException, CertificateException {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "    Mozilla/5.0 (Windows NT 6.2; rv:18.0) Gecko/20100101 Firefox/18.0");
        String PostFir = "https://api.infinitus-int.com/public/message?app_signature=mobileApp_1476306300945_8563688F90014401B0EA2AE38F6A2FEC";
        //获得密匙库
        KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(new File("C:/Users/zhoucheng/steven.keystore"));
        //密匙库的密码
        trustStore.load(instream, "123456".toCharArray());
        //注册密匙库
        SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
        //不校验域名
        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme sch = new Scheme("https", 443, socketFactory);
        client.getConnectionManager().getSchemeRegistry().register(sch);
        HttpPost httppost1 = new HttpPost(PostFir);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("userId", "test@abc.com"));
        nameValuePairs.add(new BasicNameValuePair("employeeNumber", "111"));
        httppost1.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response1 = (HttpResponse) client.execute(httppost1);
        HttpEntity resEntity1 = response1.getEntity();
        System.out.println(EntityUtils.toString(resEntity1,"UTF-8"));

    }*/

    public static void main(String[] args) {
        String str = SecurityDESUtil.decrypt(token, text);
        System.out.println(str);

    }

}
