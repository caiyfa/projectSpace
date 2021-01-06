package com.infinitusint.sdk.oa.sso;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Security;

/**
 *  解密工具类
 *  将AppKey转换为byte[]
 *   将Token转换为向量IV
 *  使用3des解密，运算模式CBC,填充模式PKCS7。获得解密后是byte[],使用utf8编码格式转换为字符串
 */
public final class SecurityDESUtil {
    private static final String SECRET_KEY = "DESede";
    //3des解密，运算模式CBC,填充模式PKCS7
    private static final String CIPHER = "DESede/CBC/PKCS5Padding";
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final Logger logger = LoggerFactory.getLogger(SecurityDESUtil.class);
    private static  Cipher cipher = null;
    private static  SecretKey key = null;

    static  {
        try {
            synchronized(SecurityDESUtil.class){
                byte[] tmpKey = getAppKey();
                key = new SecretKeySpec(tmpKey, SECRET_KEY);
                cipher = Cipher.getInstance(CIPHER);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private static byte[] getAppKey() {
       final byte[] appKey = parseHexStr2Byte(ConfigUtil.getAppKey());
       final byte[] tmpkey ={ 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7 };
        for (int ii = 0; ii < tmpkey.length; ii++)
        {
            tmpkey[ii] = appKey[ii];
        }
        return tmpkey;
    }

    /**
     * 加密
     * @param iv
     * @param content
     * @return
     */
    public synchronized static String encrypt(String iv, String content){
        try {
            logger.info("encrypt:"+content);
            final byte[] tmpiv = getIv(iv);
            final byte[] contentBytes = content.getBytes(CHARSET);
            final  IvParameterSpec ivParameterSpec = new IvParameterSpec(tmpiv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            final byte[] bytes = cipher.doFinal(contentBytes);
            return new String ( Base64.encodeBase64(bytes),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] getIv(String iv) {
        final  byte[] byteIv = parseHexStr2Byte(iv);
        byte[] tmpiv ={ 0, 1, 2, 3, 4, 5, 6, 7 };
        for (int i = 0; i < tmpiv.length; i++)
        {
            tmpiv[i] = byteIv[i];
        }
        return tmpiv;
    }

    /**
     * 解密
     * @param iv
     * @param content
     * @return
     */
    public synchronized static String decrypt(String iv, String content){
        try {
            logger.info("decrypt:"+content);
            final byte[] byteIv = getIv(iv);
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(byteIv);
            // CBC requires an initialization vector
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            final byte[] decoderStr = Base64.decodeBase64(content.getBytes("UTF-8"));
            final byte[] bytes = cipher.doFinal(decoderStr,0,decoderStr.length);
            return new String(bytes,CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
           return null;
        }

    }

    /**
     * 将二进制转换成16进制
     * @param buf
     * @return  String
     */
    public synchronized static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * 将16进制转换为二进制
     * @param hexStr
     * @return  byte[]
     */
    public synchronized static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


}
