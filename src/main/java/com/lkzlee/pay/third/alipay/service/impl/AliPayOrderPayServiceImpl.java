package com.lkzlee.pay.third.alipay.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.lkzlee.pay.dto.AbstThirdPayDto;
import com.lkzlee.pay.exceptions.BusinessException;
import com.lkzlee.pay.third.alipay.dto.AliPayOrderDto;
import com.lkzlee.pay.third.alipay.service.AliPayOrderPayService;

public class AliPayOrderPayServiceImpl implements AliPayOrderPayService
{
	/**
	* 支付宝提供给商户的服务接入网关URL(新)
	*/
	private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

	public Object addThirdPayOrderService(AbstThirdPayDto payParamDto) throws UnsupportedEncodingException
	{
		if (payParamDto == null || !(payParamDto instanceof AliPayOrderDto))
			throw new BusinessException("payParamDto 不能转换为支付宝dto，请检查");
		AliPayOrderDto aliPayDto = (AliPayOrderDto) payParamDto;
		StringBuffer payUrl = new StringBuffer(ALIPAY_GATEWAY_NEW);
		payUrl.append("service=" + URLEncoder.encode(aliPayDto.getService(), "UTF-8"));
		payUrl.append("partner=" + URLEncoder.encode(aliPayDto.getService(), "UTF-8"));
		payUrl.append("_input_charset=" + aliPayDto.getService());
		payUrl.append("sign_type=" + aliPayDto.getService());
		payUrl.append("sign=" + aliPayDto.getService());
		payUrl.append("notify_url=" + aliPayDto.getService());
		payUrl.append("return_url=" + aliPayDto.getService());
		payUrl.append("out_trade_no=" + aliPayDto.getService());
		payUrl.append("subject=" + aliPayDto.getService());
		payUrl.append("payment_type=" + aliPayDto.getService());
		payUrl.append("total_fee=" + aliPayDto.getService());
		payUrl.append("seller_id=" + aliPayDto.getService());
		//		payUrl.append("seller_email=" + aliPayDto.getService());
		//		payUrl.append("seller_account_name=" + aliPayDto.getService());
		payUrl.append("buyer_id=" + aliPayDto.getService());
		payUrl.append("buyer_email=" + aliPayDto.getService());
		//		payUrl.append("buyer_account_name=" + aliPayDto.getService());
		payUrl.append("price=" + aliPayDto.getService());
		payUrl.append("quantity=" + aliPayDto.getService());
		payUrl.append("body=" + aliPayDto.getService());
		payUrl.append("show_url=" + aliPayDto.getService());
		payUrl.append("paymethod=" + aliPayDto.getService());
		payUrl.append("enable_paymethod=" + aliPayDto.getService());
		payUrl.append("anti_phishing_key=" + aliPayDto.getService());
		payUrl.append("exter_invoke_ip=" + aliPayDto.getService());
		payUrl.append("extra_common_param=" + aliPayDto.getService());
		payUrl.append("it_b_pay=" + aliPayDto.getService());
		payUrl.append("token=" + aliPayDto.getService());
		payUrl.append("qr_pay_mode=" + aliPayDto.getService());
		payUrl.append("qrcode_width=" + aliPayDto.getService());
		payUrl.append("need_buyer_realnamed=" + aliPayDto.getService());
		payUrl.append("hb_fq_param=" + aliPayDto.getService());
		payUrl.append("goods_type=" + aliPayDto.getService());
		return payUrl.toString();
	}

	public Object refundToPayService(AbstThirdPayDto refundParamDto) throws UnsupportedEncodingException
	{

		if (refundParamDto == null || !(refundParamDto instanceof AliPayOrderDto))
			throw new BusinessException("payParamDto 不能转换为支付宝dto，请检查");
		AliPayOrderDto aliPayDto = (AliPayOrderDto) refundParamDto;
		StringBuffer payUrl = new StringBuffer(ALIPAY_GATEWAY_NEW);
		payUrl.append("service=refund_fastpay_by_platform_pwd");
		payUrl.append("partner=" + URLEncoder.encode(aliPayDto.getService(), "UTF-8"));
		payUrl.append("_input_charset=UTF-8");
		payUrl.append("sign_type=MD5" + aliPayDto.getService());
		payUrl.append("sign=" + aliPayDto.getService());
		payUrl.append("notify_url=" + aliPayDto.getService());

		payUrl.append("seller_email=" + aliPayDto.getService());
		payUrl.append("seller_user_id=" + aliPayDto.getService());
		payUrl.append("refund_date=" + aliPayDto.getService());
		payUrl.append("batch_no=" + aliPayDto.getService());
		payUrl.append("batch_num=" + aliPayDto.getService());

		payUrl.append("detail_data=" + aliPayDto.getService());

		return payUrl.toString();
	}
}
