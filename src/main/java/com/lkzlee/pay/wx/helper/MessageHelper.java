package com.lkzlee.pay.wx.helper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lkzlee.pay.utils.HttpKit;
import com.lkzlee.pay.wx.bean.TemplateMsgDataDto;

public class MessageHelper
{
	private static final String TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

	/**
	 * 发送模板消息
	 *
	 * @param accessToken
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static JSONObject templateSend(TemplateMsgDataDto data) throws Exception
	{
		String accessToken = WeChatHelper.getAccessToken();
		String result = HttpKit.post(TEMPLATE_SEND_URL.concat(accessToken), JSONObject.toJSONString(data));
		if (StringUtils.isNotEmpty(result))
		{
			return JSONObject.parseObject(result);
		}
		return null;
	}

	/*public static String refreshAccessTokenByWx() throws Exception
	{
		String appid = "wx46701bcc055a3dec";
		String secret = "6fa0f1008ba747a43ab728971d8f3254";
		String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
				.concat("&appid=") + appid + "&secret=" + secret);
		Map<String, Object> map = JSONObject.parseObject(jsonStr);
		return map.get("access_token").toString();
	}

	public static void main(String[] args) throws Exception
	{
		String access_token = refreshAccessTokenByWx();
		TemplateMsgDataDto data = new TemplateMsgDataDto("oGBeWt-feUNN9UJzt7YHJM3VnzKc",
				"aDH9zj-MdvRTOiDFrA_U2YeM5R1QTMLU5eLQs0jHD8c", "http://qg.igalaxy.com.cn/wxwap/my_order.jsp");
		data.push("first", "尊敬的客户");
		data.push("orderNo", "20170312xx32143");
		data.push("orderStatus", "待审核");
		data.push("time", "2017-03-12 19:47:23");
		data.push("money", "120");
		data.push("remark", "我们已收到您的订单，请耐心等待审核");
		JSONObject result = templateSend(data);
		System.out.println(result);
	}*/
}
