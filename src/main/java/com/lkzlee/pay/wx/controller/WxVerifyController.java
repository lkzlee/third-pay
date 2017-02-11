package com.lkzlee.pay.wx.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lkzlee.pay.bean.WeiXinConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.utils.SHA1Util;

/***
 * 微信开发者认证接口
 * @author lkzlee
 *
 */
public abstract class WxVerifyController
{
	private static Log log = LogFactory.getLog(WxVerifyController.class);

	public String checkSignature(String signature, String echostr, String timestamp, String nonce)
	{
		log.info("--微信调用，传参：signature=" + signature + "|timestamp=" + timestamp + "|nonce=" + nonce + "|echostr="
				+ echostr);
		String token = WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_TOKEN);
		if (SHA1Util.checkSignature(token, signature, timestamp, nonce))
		{
			return echostr;
		}
		return "";
	}
}
