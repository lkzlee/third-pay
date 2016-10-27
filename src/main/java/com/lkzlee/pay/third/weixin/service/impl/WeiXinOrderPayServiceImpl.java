package com.lkzlee.pay.third.weixin.service.impl;

import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lkzlee.pay.bean.WeiXinConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.dto.AbstThirdPayDto;
import com.lkzlee.pay.enums.SignTypeEnum;
import com.lkzlee.pay.exceptions.BusinessException;
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
			WeiXinOrderResultDto weixinResult = sendWeiXinRequestXml(WEI_XIN_UNIFIED_ORDER_URL, weiXinDto,
					WeiXinOrderResultDto.class);
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
			TreeMap<String, String> paramMap = TreeMapUtil.getInitTreeMapAsc();
			setBaseInfo(refundWXDto, paramMap);
			paramMap.put("out_trade_no", refundWXDto.getOut_trade_no());
			paramMap.put("out_refund_no", refundWXDto.getOut_refund_no());
			paramMap.put("total_fee", refundWXDto.getTotal_fee() + "");
			paramMap.put("refund_fee", refundWXDto.getRefund_fee() + "");
			paramMap.put("refund_fee_type", refundWXDto.getRefund_fee_type());
			paramMap.put("op_user_id", refundWXDto.getOp_user_id());
			paramMap.put("refund_account", refundWXDto.getRefund_account());

			String source = TreeMapUtil.getTreeMapString(paramMap) + "&key="
					+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
			String sign = SignTypeEnum.MD5.sign(source, null);
			refundWXDto.setSign(sign);
			WeiXinRefundResultDto responseDto = sendWeiXinRequestXml(WEIXIN_REFUND_ORDER_URL, refundWXDto,
					WeiXinRefundResultDto.class);
			return responseDto;
		}
		catch (Exception e)
		{
			LOG.fatal("请求微信退款出错，异常原因：" + e.getMessage(), e);
		}
		return null;
	}

	private void setBaseInfo(WeiXinBaseDto baseDto, TreeMap<String, String> paramMap)
	{
		paramMap.put("appid", baseDto.getAppid());
		paramMap.put("mch_id", baseDto.getMch_id());
		/***
		 * PC网页或公众号内支付请传"WEB"
		 */
		paramMap.put("device_info", "WEB");
		String nonceStr = CommonUtil.generateUUID();
		baseDto.setNonce_str(nonceStr);
		paramMap.put("nonce_str", nonceStr);
	}

	private <T> T sendWeiXinRequestXml(String url, Object refundWXDto, Class clazz) throws Exception
	{
		String xmlText = XstreamUtil.toXml(refundWXDto);
		LOG.info("#请求微信地址 url:" + url + ",参数refundXml:" + xmlText);
		String responseXml = HttpClientUtil.post(url, xmlText);
		LOG.info("#请求微信返回responseXml:" + responseXml);
		T responseDto = XstreamUtil.fromXml(responseXml, clazz);
		return responseDto;
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
			paramMap.put("out_refund_no", refundWXDto.getOut_refund_no());
			String source = TreeMapUtil.getTreeMapString(paramMap) + "&key="
					+ WeiXinConfigBean.getPayConfigValue(ConfigConstant.WEIXIN_APP_KEY);
			String sign = SignTypeEnum.MD5.sign(source, null);
			refundWXDto.setSign(sign);
			WeiXinQueryRefundResultDto responseDto = sendWeiXinRequestXml(WEIXIN_QUERY_REFUND_ORDER_URL, refundWXDto,
					WeiXinQueryRefundResultDto.class);
			return responseDto;
		}
		catch (Exception e)
		{
			LOG.fatal("请求微信退款查询接口出错，异常原因：" + e.getMessage(), e);
		}
		return null;
	}
}
