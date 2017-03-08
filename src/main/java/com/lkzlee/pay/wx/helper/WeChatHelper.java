package com.lkzlee.pay.wx.helper;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lkzlee.pay.bean.WeiXinConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.utils.HttpKit;

public class WeChatHelper
{
	private static long time = System.currentTimeMillis();
	private static String token = null;
	private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	/**
	 * 获取access_token
	 *
	 * @return
	 * @throws Exception
	 */
	public static String refreshAccessTokenByWx() throws Exception
	{
		String appid = WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_ID);
		String secret = WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
		String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
		Map<String, Object> map = JSONObject.parseObject(jsonStr);
		return map.get("access_token").toString();
	}

	public static synchronized String getAccessToken() throws Exception
	{
		long now = System.currentTimeMillis();
		if (token == null)
		{
			token = refreshAccessTokenByWx();
		}
		else
		{
			if ((now - time) > 7000000)
			{
				time = now;
				token = refreshAccessTokenByWx();
			}
		}
		return token;
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println(getAccessToken());
	}

	public static synchronized void refreshAccessToken() throws Exception
	{
		token = refreshAccessTokenByWx();
		time = System.currentTimeMillis();
	}

}
