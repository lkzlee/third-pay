package com.lkzlee.pay.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netease.lottery.base.exception.BusinessException;

/*******************************************************************************
 * AES加解密算法
 * 
 * @author arix04
 * 
 */

public class AESToolUtil
{
	private static Log log = LogFactory.getLog(AESToolUtil.class);

	// 加密
	public static String Encrypt(String sSrc, String sKey) throws Exception
	{
		if (sKey == null)
		{
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16)
		{
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());

		return Base64.encodeBase64String(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	// 解密
	public static String Decrypt(String sSrc, String sKey) throws Exception
	{
		try
		{
			// 判断Key是否正确
			if (sKey == null)
			{
				log.error("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16)
			{
				log.error("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用base64解密
			try
			{
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			}
			catch (Exception e)
			{
				log.error("解密异常，sKey：" + sKey, e);
				return null;
			}
		}
		catch (Exception ex)
		{
			log.error("解密异常，sSrc=" + sSrc + ",sKey=" + sKey, ex);
			return null;
		}
	}

	public static byte[] encrypt(byte[] src, String key) throws Exception
	{
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, securekey);//设置密钥和加密形式  
		return cipher.doFinal(src);
	}

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception
	{
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec securekey = new SecretKeySpec(key, "AES");//设置加密Key  
		cipher.init(Cipher.DECRYPT_MODE, securekey);//设置密钥和解密形式  
		return cipher.doFinal(src);
	}

	//	// 加密
	//	public static String EncryptHex(String sSrc, String sKey) throws Exception
	//	{
	//		if (sKey == null)
	//		{
	//			log.error("Key为空null");
	//			return null;
	//		}
	//		// 判断Key是否为16位
	//		if (sKey.length() != 16)
	//		{
	//			log.error("Key长度不是16位");
	//			return null;
	//		}
	//		byte[] raw = sKey.getBytes();
	//		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
	//		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
	//		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	//		byte[] encrypted = cipher.doFinal(CommonUtil.bytesToHexStr(sSrc.getBytes("UTF-8")).getBytes());
	//
	//		return Base64.encodeBase64String(encrypted).replaceAll("[\n\r]", "");//此处使用BASE64做转码功能，同时能起到2次加密的作用。
	//	}
	//
	//	// 解密
	//	public static String DecryptHex(String sSrc, String sKey) throws Exception
	//	{
	//		try
	//		{
	//			// 判断Key是否正确
	//			if (sKey == null)
	//			{
	//				log.error("Key为空null");
	//				return null;
	//			}
	//			// 判断Key是否为16位
	//			if (sKey.length() != 16)
	//			{
	//				log.error("Key长度不是16位");
	//				return null;
	//			}
	//			byte[] raw = sKey.getBytes("ASCII");
	//			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	//			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
	//			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	//			byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用base64解密
	//			try
	//			{
	//				byte[] original = cipher.doFinal(encrypted1);
	//				String originalString = new String(original);
	//				originalString = new String(CommonUtil.hexStrToBytes(originalString), "UTF-8");
	//				return originalString;
	//			}
	//			catch (Exception e)
	//			{
	//				log.error("解密异常，sSrc：" + sSrc + ",sKey:" + sKey, e);
	//				return null;
	//			}
	//		}
	//		catch (Exception ex)
	//		{
	//			log.error("解密异常，sSrc：" + sSrc + ",sKey:" + sKey, ex);
	//			return null;
	//		}
	//	}

	public static void main(String[] args) throws Exception
	{
		/*
		 * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
		 * 此处使用AES-128-CBC加密模式，key需要为16位。
		 */
		String cKey = "hongbao238712920";
		// 需要加密的字串
		String cSrc = "hongbao2 a一走开陈i看 +//8712920";
		System.out.println(cSrc);
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AESToolUtil.Encrypt(cSrc, cKey);
		System.out.println("加密后的字串是：" + enString);

		//		enString.replaceAll("[\r\n]", "");

		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = AESToolUtil.Decrypt(enString, cKey);
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
	}

	/**
	 * 数组转十六进制字符串
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 十六进制字符串转数组
	 * @param b
	 * @return
	 */
	public static byte[] hex2byte(String s)
	{
		byte[] b = s.getBytes();
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2)
		{
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return  返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] kv) throws Exception
	{
		SecretKey key = new SecretKeySpec(kv, "AES");

		long start = System.currentTimeMillis();
		//加密：
		Cipher cp = Cipher.getInstance("AES"); // 创建密码器 
		cp.init(Cipher.ENCRYPT_MODE, key); // 初始化
		byte[] ctext = cp.doFinal(src); // 加密
		return ctext;
	}

	/**
	 * @param sSrc
	 * @param sKey
	 * @param aesIv
	 * @param encoding
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public static String Decrypt(String sSrc, String sKey, String aesIv, String encoding) throws BusinessException,
			Exception
	{
		// 判断Key是否正确
		//		if (sKey == null)
		//		{
		//			throw new BusinessException("解密密钥为空");
		//		}
		//		// 判断Key是否为16位
		//		if (sKey.length() != 16)
		//		{
		//			throw new BusinessException("解密密钥不为16位");
		//		}
		//		byte[] raw = sKey.getBytes(encoding);
		//		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		//		IvParameterSpec iv = new IvParameterSpec(aesIv.getBytes(encoding));
		//		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		//		byte[] original = cipher.doFinal(CommonUtil.hexStrToBytes(sSrc));
		//		String originalString = new String(original);
		return null;
	}
}
