package com.lkzlee.pay.third.alipay.dto.response;

import com.lkzlee.pay.third.dto.AbstThirdPayDto;

/***
 * 支付宝即时支付通知返回参数定义
 * @author lkzlee
 *
 */
public class AbstAliPayBaseDto extends AbstThirdPayDto
{
	private static final long serialVersionUID = 1L;

	private String notify_time;
	private String notify_type;
	private String notify_id;
	private String sign_type;
	private String sign;

	public String getNotify_time()
	{
		return notify_time;
	}

	public void setNotify_time(String notify_time)
	{
		this.notify_time = notify_time;
	}

	public String getNotify_type()
	{
		return notify_type;
	}

	public void setNotify_type(String notify_type)
	{
		this.notify_type = notify_type;
	}

	public String getNotify_id()
	{
		return notify_id;
	}

	public void setNotify_id(String notify_id)
	{
		this.notify_id = notify_id;
	}

	public String getSign_type()
	{
		return sign_type;
	}

	public void setSign_type(String sign_type)
	{
		this.sign_type = sign_type;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	@Override
	public String toString()
	{
		return "AbstAliPayDto [notify_time=" + notify_time + ", notify_type=" + notify_type + ", notify_id="
				+ notify_id + ", sign_type=" + sign_type + ", sign=" + sign + "]";
	}

}
