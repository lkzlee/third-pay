/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2014 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.lkzlee.pay.wx.bean;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import com.lkzlee.pay.bean.WeiXinConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.utils.HttpKit;

/**
 * 微信Oauth和支付工具类
 *
 * @author L.cm
 * @date 2013-11-14 下午4:42:42
 */
public class Oauth
{

	private static final String CODE_URI = "http://open.weixin.qq.com/connect/oauth2/authorize";
	private static final String TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String REFRESH_TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

	private String appid;
	private String secret;

	public Oauth()
	{
		super();
		this.appid = WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_ID);
		this.secret = WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
	}

	public Oauth(String appid, String secret)
	{
		super();
		this.appid = appid;
		this.secret = secret;
	}

	/**
	 * 通过code 换取 access_token
	 * @param code
	 * @return
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public String getToken(String code) throws Exception
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", getAppid());
		params.put("secret", getSecret());
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		return HttpKit.get(TOKEN_URI, params);
	}

	/**
	 * 刷新 access_token
	 * @param refreshToken
	 * @return
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public String getRefreshToken(String refreshToken) throws Exception
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", getAppid());
		params.put("grant_type", "refresh_token");
		params.put("refresh_token", refreshToken);
		return HttpKit.get(REFRESH_TOKEN_URI, params);
	}

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	public String getSecret()
	{
		return secret;
	}

	public void setSecret(String secret)
	{
		this.secret = secret;
	}
}
