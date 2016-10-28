package com.lkzlee.pay.service;

import java.io.UnsupportedEncodingException;

import com.lkzlee.pay.third.dto.AbstThirdPayDto;

public interface PayService
{
	public Object addThirdPayOrderService(AbstThirdPayDto payParamDto) throws UnsupportedEncodingException;

	public Object refundToPayService(AbstThirdPayDto refundParamDto) throws UnsupportedEncodingException;
}
