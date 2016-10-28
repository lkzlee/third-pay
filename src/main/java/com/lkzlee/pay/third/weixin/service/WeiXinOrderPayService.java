package com.lkzlee.pay.third.weixin.service;

import com.lkzlee.pay.service.PayService;
import com.lkzlee.pay.third.dto.AbstThirdPayDto;

/**
 * 微信统一下单service
 * @author lkzlee
 *
 */
public interface WeiXinOrderPayService extends PayService
{
	public Object queryRefundOrderService(AbstThirdPayDto refundParamDto);
}
