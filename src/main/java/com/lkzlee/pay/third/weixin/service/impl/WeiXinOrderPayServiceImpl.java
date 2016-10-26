package com.lkzlee.pay.third.weixin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lkzlee.pay.dto.AbstThirdPayDto;
import com.lkzlee.pay.exceptions.BusinessException;
import com.lkzlee.pay.third.weixin.dto.request.WeiXinOrderDto;
import com.lkzlee.pay.third.weixin.dto.request.WeiXinQueryRefundDto;
import com.lkzlee.pay.third.weixin.dto.request.WeiXinRefundOrderDto;
import com.lkzlee.pay.third.weixin.dto.response.WeiXinOrderResultDto;
import com.lkzlee.pay.third.weixin.dto.response.WeiXinQueryRefundResultDto;
import com.lkzlee.pay.third.weixin.dto.response.WeiXinRefundResultDto;
import com.lkzlee.pay.third.weixin.service.WeiXinOrderPayService;
import com.lkzlee.pay.utils.HttpClientUtil;
import com.lkzlee.pay.utils.XstreamUtil;

public class WeiXinOrderPayServiceImpl implements WeiXinOrderPayService
{
	private final static String WEI_XIN_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private final static String WEIXIN_REFUND_ORDER_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	private final static String WEIXIN_QUERY_REFUND_ORDER_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	private final static Log LOG = LogFactory.getLog(WeiXinOrderPayServiceImpl.class);

	public Object addThirdPayOrderService(AbstThirdPayDto payParamDto)
	{
		if (payParamDto == null || !(payParamDto instanceof WeiXinOrderDto))
			throw new BusinessException("payParamDto 不能转换为微信dto，请检查");

		try
		{
			WeiXinOrderDto weiXinDto = (WeiXinOrderDto) payParamDto;
			String xmlText = XstreamUtil.toXml(weiXinDto);
			LOG.info("#请求微信下单地址url:" + WEI_XIN_UNIFIED_ORDER_URL + ",参数paramXml:" + xmlText);
			String responseXml = HttpClientUtil.post(WEI_XIN_UNIFIED_ORDER_URL, xmlText);
			LOG.info("#请求微信下单返回 orderResponseXml:" + responseXml);
			WeiXinOrderResultDto weixinResult = XstreamUtil.fromXml(responseXml, WeiXinOrderResultDto.class);
			return weixinResult;
		}
		catch (Exception e)
		{
			LOG.fatal("请求微信下单出错，异常原因：" + e.getMessage(), e);
		}
		return null;
	}

	public Object refundToPayService(AbstThirdPayDto refundParamDto)
	{
		if (refundParamDto == null || !(refundParamDto instanceof WeiXinRefundOrderDto))
			throw new BusinessException("payParamDto 不能转换为微信dto，请检查");

		try
		{
			WeiXinRefundOrderDto refundWXDto = (WeiXinRefundOrderDto) refundParamDto;
			String xmlText = XstreamUtil.toXml(refundWXDto);
			LOG.info("#请求微信下单地址refund url:" + WEIXIN_REFUND_ORDER_URL + ",参数refundXml:" + xmlText);
			String responseXml = HttpClientUtil.post(WEIXIN_REFUND_ORDER_URL, xmlText);
			LOG.info("#请求微信退款返回 refundResponseXml:" + responseXml);
			WeiXinRefundResultDto responseDto = XstreamUtil.fromXml(responseXml, WeiXinRefundResultDto.class);
			return responseDto;
		}
		catch (Exception e)
		{
			LOG.fatal("请求微信退款出错，异常原因：" + e.getMessage(), e);
		}
		return null;
	}

	public Object queryRefundOrderService(AbstThirdPayDto refundParamDto)
	{
		if (refundParamDto == null || !(refundParamDto instanceof WeiXinQueryRefundDto))
			throw new BusinessException("payParamDto 不能转换为微信dto，请检查");

		try
		{
			WeiXinQueryRefundDto refundWXDto = (WeiXinQueryRefundDto) refundParamDto;
			String xmlText = XstreamUtil.toXml(refundWXDto);
			LOG.info("#请求微信退款查询refund query url:" + WEIXIN_QUERY_REFUND_ORDER_URL + ",参数refundXml:" + xmlText);
			String responseXml = HttpClientUtil.post(WEIXIN_QUERY_REFUND_ORDER_URL, xmlText);
			LOG.info("#请求微信退款查询返回 refundResponseXml:" + responseXml);
			WeiXinQueryRefundResultDto responseDto = XstreamUtil.fromXml(responseXml, WeiXinQueryRefundResultDto.class);
			return responseDto;
		}
		catch (Exception e)
		{
			LOG.fatal("请求微信退款查询接口出错，异常原因：" + e.getMessage(), e);
		}
		return null;
	}
}
