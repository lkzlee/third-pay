package com.lkzlee.pay.third.alipay.dto.request;

import com.lkzlee.pay.third.dto.AbstThirdPayDto;

/***
 * 支付宝即时支付参数定义
 * @author lkzlee
 *
 */
public class AliPayOrderDto extends AbstThirdPayDto
{
	private static final long serialVersionUID = 1L;

	private String notify_url;
	private String return_url;
	private String exter_invoke_ip;
	private String out_trade_no;
	private String subject;
	private String total_fee;
	private String body;

	public String getNotify_url()
	{
		return notify_url;
	}

	public void setNotify_url(String notify_url)
	{
		this.notify_url = notify_url;
	}

	public String getReturn_url()
	{
		return return_url;
	}

	public void setReturn_url(String return_url)
	{
		this.return_url = return_url;
	}

	public String getExter_invoke_ip()
	{
		return exter_invoke_ip;
	}

	public void setExter_invoke_ip(String exter_invoke_ip)
	{
		this.exter_invoke_ip = exter_invoke_ip;
	}

	public String getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(String total_fee)
	{
		this.total_fee = total_fee;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	@Override
	public String toString()
	{
		return "AliPayOrderDto [notify_url=" + notify_url + ", return_url=" + return_url + ", exter_invoke_ip="
				+ exter_invoke_ip + ", out_trade_no=" + out_trade_no + ", subject=" + subject + ", total_fee="
				+ total_fee + ", body=" + body + "]";
	}

}
