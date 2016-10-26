package com.lkzlee.pay.third.weixin.dto.request;

import com.lkzlee.pay.dto.AbstThirdPayDto;

public class WeiXinQueryRefundDto extends AbstThirdPayDto
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	/***
	 * 微信订单号	transaction_id	四选一
	 * 商户订单号	out_trade_no	
	 * 商户退款单号	out_refund_no	
	 * 微信退款单号	refund_id		
	微信生成的退款单号，在申请退款接口有返回
	 */

	private String transaction_id;
	private String out_trade_no;
	private String out_refund_no;
	private String refund_id;

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	public String getMch_id()
	{
		return mch_id;
	}

	public void setMch_id(String mch_id)
	{
		this.mch_id = mch_id;
	}

	public String getDevice_info()
	{
		return device_info;
	}

	public void setDevice_info(String device_info)
	{
		this.device_info = device_info;
	}

	public String getNonce_str()
	{
		return nonce_str;
	}

	public void setNonce_str(String nonce_str)
	{
		this.nonce_str = nonce_str;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	public String getTransaction_id()
	{
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id)
	{
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no()
	{
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no)
	{
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id()
	{
		return refund_id;
	}

	public void setRefund_id(String refund_id)
	{
		this.refund_id = refund_id;
	}

	@Override
	public String toString()
	{
		return "WeiXinRefundQueryDto [appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info
				+ ", nonce_str=" + nonce_str + ", sign=" + sign + ", transaction_id=" + transaction_id
				+ ", out_trade_no=" + out_trade_no + ", out_refund_no=" + out_refund_no + ", refund_id=" + refund_id
				+ "]";
	}

}
