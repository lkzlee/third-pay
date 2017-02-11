package com.lkzlee.pay.wx.helper;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.lkzlee.pay.wx.bean.Oauth;

public class WeixinOauthHelper
{
	private static Log log = LogFactory.getLog(WeixinOauthHelper.class);
	private static Gson gson = new Gson();

	public static String oauthAndLogin(String code)
	{
		try
		{
			Oauth oauth = new Oauth();
			String token = oauth.getToken(code);
			log.info("----查询得到用户token=" + token);
			Map<String, String> resultMap = gson.fromJson(token, new TypeToken<Map<String, String>>() {
			}.getType());
			log.info("----查询得到用户resultMap=" + resultMap);
			return resultMap.get("openid");
		}
		catch (Exception e)
		{
			log.error("微信认证异常", e);
		}
		return null;
	}
}
