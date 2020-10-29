package psn.cyf.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 加密/解密工具
 * @author ershuai
 * @date 2017年4月18日 上午11:27:36
 */
public class EncryptUtil {
 
	private final byte[] DESIV = new byte[] { 0x12, 0x34, 0x56, 120, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };// 向量
 
	private AlgorithmParameterSpec iv = null;// 加密算法的参数接口
	private Key key = null;
	
	private static  String CharSet = "utf-8";
	private static EncryptUtil instcnce=null;
	private  static  String DeKey="9ba45bfd500642328ec03ad8ef1b6e75";

	public synchronized static EncryptUtil getInstance(String deSkey, String charset)throws Exception{
		if(instcnce==null||DeKey==null||!DeKey.equals(deSkey)||!CharSet.equals(charset)){
			instcnce=new EncryptUtil(deSkey,CharSet);
		}

		return  instcnce;
	}
	public synchronized static EncryptUtil getInstance()throws Exception{
		if(instcnce==null){
			instcnce=new EncryptUtil(DeKey,CharSet);
		}
		return instcnce;
	}
	/**
	 * 初始化
	 * @param deSkey	密钥
	 * @throws Exception
	 */
	private EncryptUtil(String deSkey, String CharSet) throws Exception {
		if (StringUtils.isNotBlank(CharSet)) {
			this.CharSet = CharSet;
		}
		DESKeySpec keySpec = new DESKeySpec(deSkey.getBytes(this.CharSet));// 设置密钥参数
		iv = new IvParameterSpec(DESIV);// 设置向量
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
		key = keyFactory.generateSecret(keySpec);// 得到密钥对象
	}
	
	/**
	 * 加密
	 * @author ershuai
	 * @date 2017年4月19日 上午9:40:53
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public  synchronized String encode(String data) throws Exception {
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
		enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
		byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(pasByte);
	}
	
	/**
	 * 解密
	 * @author ershuai
	 * @date 2017年4月19日 上午9:41:01
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public synchronized String decode(String data) throws Exception {
		Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		deCipher.init(Cipher.DECRYPT_MODE, key, iv);
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
		return new String(pasByte, "UTF-8");
	}
	
	public static void main(String[] args) {
		try {
			String test = "ershuai";
			String key = "9ba45bfd500642328ec03ad8ef1b6e75";// 自定义密钥
			long t1=System.currentTimeMillis();
			EncryptUtil des = new EncryptUtil(key, "utf-8");

			long t2=System.currentTimeMillis();
			System.out.println("实例化耗时："+(t2-t1)+"ms");
			System.out.println("加密前的字符：" + test);
			long t3=System.currentTimeMillis();
			System.out.println("加密后的字符：" + des.encode(test));
			long t4=System.currentTimeMillis();
			System.out.println(" 加密耗时:"+(t4-t3)+"ms");
			System.out.println("解密后的字符：" + des.decode(des.encode(test)));
			long t5=System.currentTimeMillis();
			System.out.println(" 解密耗时："+(t5-t4)+"ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}