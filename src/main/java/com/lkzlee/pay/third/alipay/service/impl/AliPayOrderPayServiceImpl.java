package com.lkzlee.pay.third.alipay.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lkzlee.pay.bean.AlipayConfigBean;
import com.lkzlee.pay.constant.ConfigConstant;
import com.lkzlee.pay.enums.SignTypeEnum;
import com.lkzlee.pay.exceptions.BusinessException;
import com.lkzlee.pay.third.alipay.dto.request.AliPayOrderDto;
import com.lkzlee.pay.third.alipay.dto.request.AliPayRefundOrderDto;
import com.lkzlee.pay.third.alipay.service.AliPayOrderPayService;
import com.lkzlee.pay.third.dto.AbstThirdPayDto;
import com.lkzlee.pay.utils.CommonUtil;
import com.lkzlee.pay.utils.HttpClientUtil;
import com.lkzlee.pay.utils.TreeMapUtil;

@Service("aliPayOrderPayService")
public class AliPayOrderPayServiceImpl implements AliPayOrderPayService
{
	private final static Log LOG = LogFactory.getLog(AliPayOrderPayServiceImpl.class);
	/**
	* 支付宝提供给商户的服务接入网关URL(新)
	*/
	private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do";

	/***
	 * 支付宝下单，拼接一个下单url，让用户访问这个url
	 */
	public Object addThirdPayOrderService(AbstThirdPayDto payParamDto) throws UnsupportedEncodingException
	{
		if (payParamDto == null || !(payParamDto instanceof AliPayOrderDto))
			throw new BusinessException("payParamDto 不能转换为支付宝dto，请检查");
		AliPayOrderDto aliPayDto = (AliPayOrderDto) payParamDto;
		TreeMap<String, String> paramTreeMap = TreeMapUtil.getInitTreeMapAsc();
		paramTreeMap.put("service", "create_direct_pay_by_user");
		paramTreeMap.put("partner",
				AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_PARTNER, "2088102168716583"));
		paramTreeMap.put("_input_charset", "UTF-8");
		paramTreeMap.put("notify_url", URLEncoder.encode(aliPayDto.getNotify_url(), "UTF-8"));
		paramTreeMap.put("return_url", URLEncoder.encode(aliPayDto.getReturn_url(), "UTF-8"));
		paramTreeMap.put("out_trade_no", URLEncoder.encode(aliPayDto.getOut_trade_no(), "UTF-8"));
		paramTreeMap.put("subject", URLEncoder.encode(aliPayDto.getSubject(), "UTF-8"));
		paramTreeMap.put("payment_type", "1");
		paramTreeMap.put("total_fee", URLEncoder.encode(CommonUtil.formatMoney(aliPayDto.getTotal_fee()), "UTF-8"));
		paramTreeMap.put("seller_id",
				AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_SELLER_ID, "2088102168716583"));
		//signTreeMap.put("seller_email=" + aliPayDto.getService());
		//signTreeMap.put("seller_account_name=" + aliPayDto.getService());
		//signTreeMap.put("buyer_id=");
		//signTreeMap.put("buyer_email=" + aliPayDto.getService());
		//signTreeMap.put("buyer_account_name=" + aliPayDto.getService());
		//signTreeMap.put("price=" + aliPayDto.getService());
		//signTreeMap.put("quantity=" + aliPayDto.getService());
		paramTreeMap.put("body", URLEncoder.encode(aliPayDto.getBody(), "UTF-8"));
		//signTreeMap.put("show_url=");
		paramTreeMap.put("paymethod", "directPay");
		String enable_paymethod = AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_ENABLE_PAY_METHOD,
				"directPay^bankPay^cartoon^cash");
		paramTreeMap.put("enable_paymethod", URLEncoder.encode(enable_paymethod, "UTF-8"));
		//signTreeMap.put("anti_phishing_key=" + aliPayDto.getService());
		paramTreeMap.put("exter_invoke_ip", URLEncoder.encode(aliPayDto.getExter_invoke_ip(), "UTF-8"));
		//signTreeMap.put("extra_common_param=" + aliPayDto.getService());
		/**
		 * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。该参数数值不接受小数点，如1.5h，可转换为90m。
		 */
		String it_b_pay = AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_IT_PAY_TIMEOUT, "1h");
		paramTreeMap.put("it_b_pay", it_b_pay);
		//		signTreeMap.put("token=" + aliPayDto.getService());
		//		signTreeMap.put("qr_pay_mode=" + aliPayDto.getService());
		//		signTreeMap.put("qrcode_width=" + aliPayDto.getService());
		//		signTreeMap.put("need_buyer_realnamed=" + aliPayDto.getService());
		//		signTreeMap.put("hb_fq_param=" + aliPayDto.getService());
		/***
		 * 商品类型：1表示实物类商品 0表示虚拟类商品
		 */
		paramTreeMap.put("goods_type", "0");
		paramTreeMap.put("sign_type", SignTypeEnum.MD5.getSignType());
		String source = TreeMapUtil.getTreeMapString(paramTreeMap);
		String privateKey = AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_PRIVATE_KEY);
		String signResult = SignTypeEnum.MD5.sign(source, privateKey);
		paramTreeMap.put("sign", signResult);
		String payUrl = ALIPAY_GATEWAY_NEW + "?" + TreeMapUtil.getTreeMapString(paramTreeMap);
		LOG.info("@@支付宝下单支付url=" + payUrl);
		return payUrl;
	}

	/***
	 * 支付宝退款url，异步通知退款
	 */
	public Object refundToPayService(AbstThirdPayDto paramDto) throws UnsupportedEncodingException
	{

		if (paramDto == null || !(paramDto instanceof AliPayOrderDto))
			throw new BusinessException("payParamDto 不能转换为支付宝dto，请检查");
		AliPayRefundOrderDto refundParamDto = (AliPayRefundOrderDto) paramDto;
		TreeMap<String, String> paramTreeMap = TreeMapUtil.getInitTreeMapAsc();
		paramTreeMap.put("service", "refund_fastpay_by_platform_pwd");
		paramTreeMap.put("partner",
				AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_PARTNER, "2088102168716583"));
		paramTreeMap.put("_input_charset", "UTF-8");

		paramTreeMap.put("notify_url", URLEncoder.encode(refundParamDto.getNotify_url(), "UTF-8"));

		//		paramTreeMap.put("seller_email=" + aliPayDto.getService());
		paramTreeMap.put("seller_user_id",
				AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_SELLER_ID, "2088102168716583"));
		/***
		 * 	
		退款请求的当前时间。格式为：yyyy-MM-dd HH:mm:ss。
		 */
		paramTreeMap.put("refund_date=", URLEncoder.encode(refundParamDto.getRefund_date(), "UTF-8"));
		paramTreeMap.put("batch_no=", URLEncoder.encode(refundParamDto.getBatch_no(), "UTF-8"));
		paramTreeMap.put("batch_num=", URLEncoder.encode(refundParamDto.getBatch_num(), "UTF-8"));
		paramTreeMap.put("detail_data=", URLEncoder.encode(refundParamDto.getRefund_date(), "UTF-8"));
		paramTreeMap.put("sign_type", SignTypeEnum.MD5.getSignType());
		String source = TreeMapUtil.getTreeMapString(paramTreeMap);
		String privateKey = AlipayConfigBean.getPayConfigValue(ConfigConstant.ALIPAY_PRIVATE_KEY);
		String signResult = SignTypeEnum.MD5.sign(source, privateKey);
		paramTreeMap.put("sign", signResult);

		try
		{
			LOG.info("@@支付宝退款请求参数url=" + ALIPAY_GATEWAY_NEW + " | param=" + TreeMapUtil.getTreeMapString(paramTreeMap));
			String respConetent = HttpClientUtil.post(ALIPAY_GATEWAY_NEW, paramTreeMap);
			LOG.info("@@支付宝退款请求同步响应返回结果respConetent=" + respConetent);
			return respConetent;
		}
		catch (Exception e)
		{
			LOG.fatal("请求支付宝退款异常，异常原因:" + e.getMessage() + ",参数：" + paramTreeMap + ",url=" + ALIPAY_GATEWAY_NEW);
		}
		return null;
	}
}
