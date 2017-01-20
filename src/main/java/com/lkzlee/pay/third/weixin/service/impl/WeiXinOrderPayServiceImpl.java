package com.lkzlee.pay.third.weixin.service.impl;

import java.io.UnsupportedEncodingException;
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
import com.lkzlee.pay.utils.HttpKit;
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

	@Override
	public Object addThirdPayOrderService(AbstThirdPayDto payParamDto)
	{
		if (payParamDto == null || !(payParamDto instanceof WeiXinOrderDto))
			throw new BusinessException("payParamDto 不能转换为微信dto，请检查");

		try
		{
			WeiXinOrderDto weiXinDto = (WeiXinOrderDto) payParamDto;
			TreeMap<String, String> paramMap = TreeMapUtil.getInitTreeMapAsc();
			setBaseInfo(weiXinDto, paramMap);
			paramMap.put("body", weiXinDto.getBody());
			//			paramMap.put("detail", weiXinDto.getDetail());
			//			paramMap.put("attach", weiXinDto.getTotal_fee() + "");
			paramMap.put("out_trade_no", weiXinDto.getOut_trade_no());
			paramMap.put("fee_type", weiXinDto.getFee_type());
			paramMap.put("total_fee", weiXinDto.getTotal_fee() + "");
			paramMap.put("spbill_create_ip", weiXinDto.getSpbill_create_ip());
			paramMap.put("time_start", weiXinDto.getTime_start());
			paramMap.put("time_expire", weiXinDto.getTime_expire());
			//			paramMap.put("goods_tag", weiXinDto.getRefund_account());
			paramMap.put("notify_url", weiXinDto.getNotify_url());
			paramMap.put("trade_type", weiXinDto.getTrade_type());
			paramMap.put("product_id", weiXinDto.getProduct_id());
			//			paramMap.put("limit_pay", weiXinDto.getRefund_account());
			//			paramMap.put("openid", weiXinDto.getRefund_account());

			String source = TreeMapUtil.getTreeMapString(paramMap) + "&key="
					+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_PAY_SIGN_KEY);
			String sign = SignTypeEnum.MD5.sign(source, null);
			paramMap.put("sign", sign);
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
				+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_PAY_SIGN_KEY);

		String calcSign = SignTypeEnum.MD5.sign(source, null);
		LOG.info("验签获取的source =" + source + ",calcSign=" + calcSign + ",sign=" + sign);
		if (!calcSign.equals(sign))
			throw new BusinessException("校验返回签名错误，请查看，");

	}

	@Override
	public Object refundToPayService(AbstThirdPayDto refundParamDto)
	{
		if (refundParamDto == null || !(refundParamDto instanceof WeiXinRefundOrderDto))
			throw new BusinessException("payParamDto 不能转换为微信dto，请检查");

		try
		{
			WeiXinRefundOrderDto refundWXDto = (WeiXinRefundOrderDto) refundParamDto;
			TreeMap<String, String> paramMap = TreeMapUtil.getInitTreeMapAsc();
			setBaseInfo(refundWXDto, paramMap);
			paramMap.put("out_trade_no", refundWXDto.getOut_trade_no());
			paramMap.put("out_refund_no", refundWXDto.getOut_refund_no());
			paramMap.put("total_fee", refundWXDto.getTotal_fee() + "");
			paramMap.put("refund_fee", refundWXDto.getRefund_fee() + "");
			paramMap.put("refund_fee_type", refundWXDto.getRefund_fee_type());
			paramMap.put("op_user_id", refundWXDto.getOp_user_id());
			//			paramMap.put("refund_account", refundWXDto.getRefund_account());

			String source = TreeMapUtil.getTreeMapString(paramMap) + "&key="
					+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_PAY_SIGN_KEY);
			String sign = SignTypeEnum.MD5.sign(source, null);
			paramMap.put("sign", sign);
			WeiXinRefundResultDto responseDto = sendWeiXinRequestXmlSSL(WEIXIN_REFUND_ORDER_URL, paramMap,
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
		paramMap.put("appid", WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_ID));
		paramMap.put("mch_id", WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_MCH_ID));
		/***
		 * PC网页或公众号内支付请传"WEB"
		 */
		paramMap.put("device_info", "WEB");
		String nonceStr = CommonUtil.generateUUID();
		//		baseDto.setNonce_str(nonceStr);
		paramMap.put("nonce_str", nonceStr);
	}

	private <T> T sendWeiXinRequestXmlSSL(String url, TreeMap<String, String> paramMap, Class clz) throws Exception
	{
		String xmlText = XstreamUtil.toXml(paramMap);
		LOG.info("#请求微信地址ssl url:" + url + ",参数wxXml:" + xmlText);
		String caPath = WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_PAY_CERT_PATH);
		String caPasswd = WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_MCH_ID);
		String responseXml = HttpKit.postXmlWthCaByPKCS12(url, xmlText, caPath, caPasswd);
		LOG.info("#请求微信返回ssl responseXml:" + responseXml);
		return XstreamUtil.fromXml(responseXml, clz);
	}

	private <T> T sendWeiXinRequestXml(String url, Map<String, String> paramMap, Class clz) throws Exception
	{
		String xmlText = XstreamUtil.toXml(paramMap);
		LOG.info("#请求微信地址 url:" + url + ",参数wxXml:" + xmlText);
		String responseXml = HttpKit.postXml(url, xmlText);
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

	@Override
	public Object queryRefundOrderService(AbstThirdPayDto refundParamDto)
	{
		if (refundParamDto == null || !(refundParamDto instanceof WeiXinQueryRefundDto))
			throw new BusinessException("payParamDto 不能转换为微信dto，请检查");

		try
		{
			WeiXinQueryRefundDto refundWXDto = (WeiXinQueryRefundDto) refundParamDto;
			TreeMap<String, String> paramMap = TreeMapUtil.getInitTreeMapAsc();
			setBaseInfo(refundWXDto, paramMap);
			paramMap.put("out_refund_no", refundWXDto.getOut_refund_no());
			String source = TreeMapUtil.getTreeMapString(paramMap) + "&key="
					+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_PAY_SIGN_KEY);
			String sign = SignTypeEnum.MD5.sign(source, null);
			paramMap.put("sign", sign);
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
