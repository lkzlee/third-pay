package com.lkzlee.pay.enums;

import org.apache.commons.lang.StringUtils;

import com.lkzlee.pay.utils.MD5Utils;
import com.lkzlee.pay.utils.RSASignature;

/**
 * 签名方式枚举
 * @author lkzlee
 *
 */
public enum SignTypeEnum
{
	MD5("MD5", "MD5签名算法")
	{
		@Override
		public String sign(String source, String privateKey)
		{
			if (StringUtils.isNotBlank(privateKey))
			{
				source += privateKey;
			}
			return MD5Utils.getMD5(source).toUpperCase();
		}

		@Override
		public boolean verifySign(String source, String privateKey, String sign)
		{
			if (StringUtils.isNotBlank(privateKey))
			{
				source += privateKey;
			}
			String calcSign = MD5Utils.getMD5(source).toUpperCase();
			return calcSign.equalsIgnoreCase(sign);
		}
	},
	RSA("RSA", "RSA签名算法")
	{
		@Override
		public String sign(String source, String privateKey)
		{
			return RSASignature.sign(source, privateKey, "UTF-8");
		}

		@Override
		public boolean verifySign(String source, String publicKey, String sign)
		{
			return RSASignature.doCheck(source, sign, publicKey, "UTF-8");
		}
	};
	private String signType;
	private String signDesc;

	private SignTypeEnum(String signType, String signDesc)
	{
		this.signType = signType;
		this.signDesc = signDesc;
	}

	public String sign(String source, String privateKey)
	{
		return null;
	}

	public boolean verifySign(String source, String privateKey, String sign)
	{
		return false;
	}

	public String getSignType()
	{
		return signType;
	}

	public void setSignType(String signType)
	{
		this.signType = signType;
	}

	public String getSignDesc()
	{
		return signDesc;
	}

	public void setSignDesc(String signDesc)
	{
		this.signDesc = signDesc;
	}
}
