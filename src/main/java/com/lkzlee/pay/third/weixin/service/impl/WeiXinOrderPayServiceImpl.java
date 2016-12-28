package com.lkzlee.pay.third.weixin.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lkzlee.pay.bean.WeiXinConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.enums.SignTypeEnum;
import com.lkzlee.pay.exceptions.BusinessException;
import com.lkzlee.pay.third.dto.AbstThirdPayDto;
import com.lkzlee.pay.third.weixin.dto.request.WeiXinBaseDto;
import com.lkzlee.pay.third.weixin.dto.request.WeiXinOrderDto;
import com.lkzlee.pay.third.weixin.dto.request.WeiXinQueryRefundDto;
import com.lkzlee.pay.third.weixin.dto.request.WeiXinRefundOrderDto;
import com.lkzlee.pay.third.weixin.dto.response.WeiXinOrderResultDto;
import com.lkzlee.pay.third.weixin.dto.response.WeiXinQueryRefundResultDto;
import com.lkzlee.pay.third.weixin.dto.response.WeiXinRefundResultDto;
import com.lkzlee.pay.third.weixin.service.WeiXinOrderPayService;
import com.lkzlee.pay.utils.CommonUtil;
import com.lkzlee.pay.utils.HttpClientUtil;
import com.lkzlee.pay.utils.TreeMapUtil;
import com.lkzlee.pay.utils.XstreamUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service("weiXinOrderPayService")
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
			TreeMap<String, String> paramMap = TreeMapUtil.getInitTreeMapAsc();
			setBaseInfo(weiXinDto, paramMap);
			paramMap.put("body", URLEncoder.encode(weiXinDto.getBody(), "UTF-8"));
			//			paramMap.put("detail", weiXinDto.getDetail());
			//			paramMap.put("attach", weiXinDto.getTotal_fee() + "");
			paramMap.put("out_trade_no", URLEncoder.encode(weiXinDto.getOut_trade_no(), "UTF-8"));
			paramMap.put("fee_type", URLEncoder.encode(weiXinDto.getFee_type(), "UTF-8"));
			paramMap.put("total_fee", URLEncoder.encode(weiXinDto.getTotal_fee() + "", "UTF-8"));
			paramMap.put("spbill_create_ip", URLEncoder.encode(weiXinDto.getSpbill_create_ip(), "UTF-8"));
			paramMap.put("time_start", URLEncoder.encode(weiXinDto.getTime_start(), "UTF-8"));
			paramMap.put("time_expire", URLEncoder.encode(weiXinDto.getTime_expire(), "UTF-8"));
			//			paramMap.put("goods_tag", weiXinDto.getRefund_account());
			paramMap.put("notify_url", URLEncoder.encode(weiXinDto.getNotify_url(), "UTF-8"));
			paramMap.put("trade_type", URLEncoder.encode(weiXinDto.getTrade_type(), "UTF-8"));
			paramMap.put("product_id", URLEncoder.encode(weiXinDto.getProduct_id(), "UTF-8"));
			//			paramMap.put("limit_pay", weiXinDto.getRefund_account());
			//			paramMap.put("openid", weiXinDto.getRefund_account());

			String source = TreeMapUtil.getTreeMapString(paramMap) + "&key="
					+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
			String sign = SignTypeEnum.MD5.sign(source, null);
			paramMap.put("sign", URLEncoder.encode(sign, "UTF-8"));
			WeiXinOrderResultDto weixinResult = sendWeiXinRequestXml(WEI_XIN_UNIFIED_ORDER_URL, paramMap,
					WeiXinOrderResultDto.class);
			monitorResponseText(weixinResult);
			return weixinResult;
		}
		catch (Exception e)
		{
			LOG.fatal("请求微信下单出错，异常原因：" + e.getMessage(), e);
		}
		return null;
	}

	private void monitorResponseText(Object weixinResult) throws IllegalArgumentException, IllegalAccessException
	{

		String sign = null;
		TreeMap<String, String> sourceMap = TreeMapUtil.getInitTreeMapAsc();
		TreeMapUtil.setFiledParamToMapInfo(weixinResult, sourceMap, weixinResult.getClass());
		LOG.info("解析的类Map=" + sourceMap);
		if (sourceMap.containsKey("sign"))
			sign = sourceMap.get("sign");
		sourceMap.remove("sign");
		String source = TreeMapUtil.getTreeMapString(sourceMap) + "&key="
				+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
		String calcSign = SignTypeEnum.MD5.sign(source, null);
		if (!calcSign.equals(sign))
			throw new BusinessException("校验返回签名错误，请查看，");

	}

	public Object refundToPayService(AbstThirdPayDto refundParamDto)
	{
		if (refundParamDto == null || !(refundParamDto instanceof WeiXinRefundOrderDto))
			throw new BusinessException("payParamDto 不能转换为微信dto，请检查");

		try
		{
			WeiXinRefundOrderDto refundWXDto = (WeiXinRefundOrderDto) refundParamDto;
			TreeMap<String, String> paramMap = TreeMapUtil.getInitTreeMapAsc();
			setBaseInfo(refundWXDto, paramMap);
			paramMap.put("out_trade_no", URLEncoder.encode(refundWXDto.getOut_trade_no(), "UTF-8"));
			paramMap.put("out_refund_no", URLEncoder.encode(refundWXDto.getOut_refund_no(), "UTF-8"));
			paramMap.put("total_fee", URLEncoder.encode(refundWXDto.getTotal_fee() + "", "UTF-8"));
			paramMap.put("refund_fee", URLEncoder.encode(refundWXDto.getRefund_fee() + "", "UTF-8"));
			paramMap.put("refund_fee_type", URLEncoder.encode(refundWXDto.getRefund_fee_type(), "UTF-8"));
			paramMap.put("op_user_id", URLEncoder.encode(refundWXDto.getOp_user_id(), "UTF-8"));
			//			paramMap.put("refund_account", refundWXDto.getRefund_account());

			String source = TreeMapUtil.getTreeMapString(paramMap) + "&key="
					+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
			String sign = SignTypeEnum.MD5.sign(source, null);
			paramMap.put("sign", URLEncoder.encode(sign, "UTF-8"));
			WeiXinRefundResultDto responseDto = sendWeiXinRequestXml(WEIXIN_REFUND_ORDER_URL, paramMap,
					WeiXinRefundResultDto.class);
			monitorResponseText(responseDto);
			return responseDto;
		}
		catch (Exception e)
		{
			LOG.fatal("请求微信退款出错，异常原因：" + e.getMessage(), e);
		}
		return null;
	}

	private void setBaseInfo(WeiXinBaseDto baseDto, TreeMap<String, String> paramMap)
			throws UnsupportedEncodingException
	{
		paramMap.put("appid",
				URLEncoder.encode(WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_ID), "UTF-8"));
		paramMap.put("mch_id",
				URLEncoder.encode(WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_MCH_ID), "UTF-8"));
		/***
		 * PC网页或公众号内支付请传"WEB"
		 */
		paramMap.put("device_info", "WEB");
		String nonceStr = CommonUtil.generateUUID();
		//		baseDto.setNonce_str(nonceStr);
		paramMap.put("nonce_str", URLEncoder.encode(nonceStr, "UTF-8"));
	}

	private <T> T sendWeiXinRequestXml(String url, Map<String, String> paramMap, Class clz) throws Exception
	{
		String xmlText = XstreamUtil.toXml(paramMap);
		LOG.info("#请求微信地址 url:" + url + ",参数wxXml:" + xmlText);
		String responseXml = HttpClientUtil.post(url, xmlText);
		LOG.info("#请求微信返回responseXml:" + responseXml);
		return XstreamUtil.fromXml(responseXml, clz);
	}

	public static void main(String[] args)
	{

		String response = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[appid参数长度有误]]></return_msg></xml>";
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", WeiXinOrderResultDto.class);
		WeiXinOrderResultDto responseDto = (WeiXinOrderResultDto) xstream.fromXML(response);
		System.out.println(responseDto);
	}

	public Object queryRefundOrderService(AbstThirdPayDto refundParamDto)
	{
		if (refundParamDto == null || !(refundParamDto instanceof WeiXinQueryRefundDto))
			throw new BusinessException("payParamDto 不能转换为微信dto，请检查");

		try
		{
			WeiXinQueryRefundDto refundWXDto = (WeiXinQueryRefundDto) refundParamDto;
			TreeMap<String, String> paramMap = TreeMapUtil.getInitTreeMapAsc();
			setBaseInfo(refundWXDto, paramMap);
			paramMap.put("out_refund_no", URLEncoder.encode(refundWXDto.getOut_refund_no(), "UTF-8"));
			String source = TreeMapUtil.getTreeMapString(paramMap) + "&key="
					+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
			String sign = SignTypeEnum.MD5.sign(source, null);
			paramMap.put("sign", URLEncoder.encode(sign, "UTF-8"));
			WeiXinQueryRefundResultDto responseDto = sendWeiXinRequestXml(WEIXIN_QUERY_REFUND_ORDER_URL, paramMap,
					WeiXinQueryRefundResultDto.class);
			monitorResponseText(responseDto);
			return responseDto;
		}
		catch (Exception e)
		{
			LOG.fatal("请求微信退款查询接口出错，异常原因：" + e.getMessage(), e);
		}
		return null;
	}
}
