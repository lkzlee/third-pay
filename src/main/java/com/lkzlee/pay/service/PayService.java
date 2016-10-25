package com.lkzlee.pay.service;

import com.lkzlee.pay.dto.AbstThirdPayDto;

public interface PayService
{
	public Object addThirdPayOrderService(AbstThirdPayDto payParamDto);

	public Object refundToPayService(AbstThirdPayDto refundParamDto);
}
