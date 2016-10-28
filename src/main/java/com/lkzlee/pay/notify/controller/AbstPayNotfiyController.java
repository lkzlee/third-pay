package com.lkzlee.pay.notify.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lkzlee.pay.third.dto.AbstThirdPayDto;

/***
 * 支付通知业务抽象处理类
 * @author lkzlee
 *
 */
public abstract class AbstPayNotfiyController
{
	private final static Log LOG = LogFactory.getLog(AbstPayNotfiyController.class);

	/**
	 * 默认isDoBusiness false
	 * @param request
	 * @param response
	 * @param payNotifyDto
	 * @param isDoBusiness
	 * @return
	 */
	public String payNotifyHandler(HttpServletRequest request, HttpServletResponse response,
			AbstThirdPayDto payNotifyDto, boolean isDoBusiness)
	{
		try
		{
			if (!isSuccessOrder(payNotifyDto))
			{
				LOG.error("订单通知失败，请检查该笔订单，参数payNotifyDto=" + payNotifyDto);
				return getFailMsgResponse();
			}
			if (!isSignRight(payNotifyDto))
			{
				LOG.fatal("验签不通过，请检查，参数payNotifyDto=" + payNotifyDto);
				return getFailMsgResponse();
			}
			if (isDoBusiness)
			{
				handlerServerPayNotify(payNotifyDto);
			}
			return getSuccessMsgResponse(payNotifyDto);
		}
		catch (Exception e)
		{
			LOG.fatal("系统处理异常，请检查，msg：" + e.getMessage(), e);
			return getFailMsgResponse();
		}
	}

	protected abstract boolean isSuccessOrder(AbstThirdPayDto payNotifyDto);

	protected abstract void handlerServerPayNotify(AbstThirdPayDto payNotifyDto);

	protected abstract String getSuccessMsgResponse(AbstThirdPayDto payNotifyDto);

	protected abstract String getFailMsgResponse();

	protected abstract boolean isSignRight(AbstThirdPayDto payNotifyDto) throws IllegalAccessException;
}
