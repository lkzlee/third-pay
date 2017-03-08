package com.lkzlee.pay.wx.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.lkzlee.pay.utils.HttpKit;

public class MenuOperationHelper
{
	private static StringBuffer menuJson = new StringBuffer();
	private static Logger LOG = LoggerFactory.getLogger(MenuOperationHelper.class);
	static
	{
		try
		{
			InputStream ins = MenuOperationHelper.class.getResourceAsStream("/wechat.config");
			BufferedReader br = new BufferedReader(new InputStreamReader(ins));
			String msg = null;
			do
			{
				msg = br.readLine();
				if (msg != null)
				{
					menuJson.append(msg);
				}
			}
			while (msg != null);
			LOG.debug("加载的菜单内容为:" + menuJson);
			ins.close();
			br.close();
		}
		catch (Exception e)
		{
			LOG.error("加载wechat.config配置文件出错！", e);
		}
	}

	/**
	* 创建菜单
	* @throws IOException 
	* @throws NoSuchProviderException 
	* @throws NoSuchAlgorithmException 
	* @throws KeyManagementException 
	*/
	public static boolean createMenu(String accessToken, String params) throws InterruptedException,
			ExecutionException, IOException
	{
		String jsonStr = HttpKit.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken,
				params);
		Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
		return "0".equals(map.get("errcode").toString());
	}

	/**
	 * 查询菜单
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static Map<String, Object> getMenuInfo(String accessToken) throws InterruptedException, ExecutionException,
			NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException
	{
		String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
		Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
		return map;
	}

	/**
	 * 删除自定义菜单
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static boolean deleteMenu(String accessToken) throws InterruptedException, ExecutionException,
			NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException
	{
		String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
		Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
		return "0".equals(map.get("errcode").toString());
	}

	public static boolean createMenuByConfig() throws Exception
	{
		String access_token = WeChatHelper.getAccessToken();
		return createMenu(access_token, menuJson.toString());
	}

	public static boolean deleteMenuByConfig() throws Exception
	{
		String access_token = WeChatHelper.getAccessToken();
		return deleteMenu(access_token);
	}

	public static Map<String, Object> getMenuInfoByConfig() throws Exception
	{
		String access_token = WeChatHelper.getAccessToken();
		Map<String, Object> result = getMenuInfo(access_token);
		return result;
	}

	public static void main(String[] args) throws Exception
	{
		String access_token = WeChatHelper.getAccessToken();
		Map<String, Object> result = getMenuInfo(access_token);
		LOG.info("创建菜单结果：" + result);
		//		boolean dflag=deleteMenuByConfig();
		//		LOG.info("删除菜单结果："+dflag);
	}
}
