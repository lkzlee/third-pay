package com.lkzlee.pay.third.alipay.service.impl;

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

	public Object addThirdPayOrderService(AbstThirdPayDto payParamDto)
	{
		if (payParamDto == null || !(payParamDto instanceof AliPayOrderDto))
			throw new BusinessException("payParamDto 不能转换为支付宝dto，请检查");
		return null;
	}

	public Object refundToPayService(AbstThirdPayDto refundParamDto)
	{

		return null;
	}
}
