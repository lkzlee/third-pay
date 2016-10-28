package com.lkzlee.pay.notify.controller;

import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lkzlee.pay.bean.WeiXinConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.enums.SignTypeEnum;
import com.lkzlee.pay.third.dto.AbstThirdPayDto;
import com.lkzlee.pay.third.weixin.dto.response.WeiXinPayNotifyResultDto;
import com.lkzlee.pay.utils.TreeMapUtil;

/***
 * 微信支付通知业务抽象处理类-如果处理微信支付通知，只需要继承该类实现相关业务处理即可
 * @author lkzlee
 *
 */
public abstract class WeiXinPayNotfiyController extends AbstPayNotfiyController
{
	private final static Log LOG = LogFactory.getLog(WeiXinPayNotfiyController.class);
	// 微信返回 fail 失败，success 成功
	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_FAIL = "FAIL";

	@Override
	protected boolean isSuccessOrder(AbstThirdPayDto payNotifyDto)
	{
		WeiXinPayNotifyResultDto wxPayNotifyDto = (WeiXinPayNotifyResultDto) payNotifyDto;
		if (STATUS_FAIL.equals(wxPayNotifyDto.getResult_code()) || STATUS_FAIL.equals(wxPayNotifyDto.getReturn_code()))
		{
			return false;
		}
		return true;
	}

	@Override
	protected String getSuccessMsgResponse(AbstThirdPayDto payNotifyDto)
	{
		return getWeiXinPayNotifyMsg(STATUS_SUCCESS);
	}

	private String getWeiXinPayNotifyMsg(String status)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<return_code><![CDATA[" + status + "]]></return_code>");
		sb.append("<return_msg><![CDATA[OK]]></return_msg>");
		sb.append("</xml>");
		return sb.toString();
	}

	@Override
	protected String getFailMsgResponse()
	{
		return getWeiXinPayNotifyMsg(STATUS_FAIL);
	}

	@Override
	protected boolean isSignRight(AbstThirdPayDto payNotifyDto) throws IllegalAccessException
	{
		WeiXinPayNotifyResultDto wxPayNotifyDto = (WeiXinPayNotifyResultDto) payNotifyDto;
		TreeMap<String, String> sourceMap = TreeMapUtil.getInitTreeMapAsc();
		TreeMapUtil.setFiledParamToMapInfo(wxPayNotifyDto, sourceMap, wxPayNotifyDto.getClass());
		String sign = null;
		if (sourceMap.containsKey("sign"))
			sign = sourceMap.get("sign");
		sourceMap.remove("sign");
		String source = TreeMapUtil.getTreeMapString(sourceMap) + "&key="
				+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
		String calcSign = SignTypeEnum.MD5.sign(source, null);
		if (!calcSign.equals(sign))
		{
			LOG.fatal("验签不通过，请检查，calcSign=" + calcSign + ",sign=" + sign);
			return false;
		}
		return true;
	}

}
