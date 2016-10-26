package com.lkzlee.pay.third.weixin.service;

import com.lkzlee.pay.dto.AbstThirdPayDto;
import com.lkzlee.pay.service.PayService;

/**
 * 微信统一下单service
 * @author lkzlee
 *
 */
public interface WeiXinOrderPayService extends PayService
{
	public Object queryRefundOrderService(AbstThirdPayDto refundParamDto);
}
