package com.lkzlee.pay.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * RSA签名工具类
 * @author lkzlee
 *
 */
public class RSASignature
{
	private static final Log LOG = LogFactory.getLog(RSASignature.class);

	/**
	 * 签名算法 
	 */
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	 * RSA签名 
	 * @param source 待签名数据
	 * @param privateKey 商户私钥 
	 * @param encode 字符集编码 
	 * @return 签名值
	 */
	public static String sign(String source, String privateKey, String encode)
	{
		try
		{
			Signature sigEng = Signature.getInstance("SHA1withRSA");
			byte[] pribyte = Base64.decode(privateKey);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pribyte);
			KeyFactory fac = KeyFactory.getInstance("RSA");

			RSAPrivateKey priKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
			sigEng.initSign(priKey);
			sigEng.update(source.getBytes(encode));

			byte[] signature = sigEng.sign();
			return new String(Base64.encode(signature));
		}
		catch (Exception e)
		{
			LOG.error("#Sign error.Cause:" + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 签名算法
	 * @param content
	 * @param privateKey
	 * @return
	 */
	public static String sign(String content, String privateKey)
	{
		try
		{
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initSign(priKey);
			signature.update(content.getBytes());
			byte[] signed = signature.sign();
			return new String(Base64.encode(signed));
		}
		catch (Exception e)
		{
			LOG.error("#Sign error.Cause:" + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * RSA验签名检查
	 * @param content 待签名数据
	 * @param sign 签名值
	 * @param publicKey 分配给开发商公钥
	 * @param encode 字符集编码
	 * @return 布尔值
	 */
	public static boolean doCheck(String content, String sign, String publicKey, String encode)
	{
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(encode));

			boolean bverify = signature.verify(Base64.decode(sign));
			return bverify;

		}
		catch (Exception e)
		{
			LOG.error("#DoCheck sign error.Cause:" + e.getMessage(), e);
		}

		return false;
	}

	public static boolean doCheck(String content, String sign, String publicKey)
	{
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes());

			boolean bverify = signature.verify(Base64.decode(sign));
			return bverify;

		}
		catch (Exception e)
		{
			LOG.error("#DoCheck sign error.Cause:" + e.getMessage(), e);
		}

		return false;
	}

}
