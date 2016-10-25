package com.lkzlee.pay.third.weixin.dto.response;

public class WeiXinRefundResultDto extends WeiXinBaseResultDto
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
	 * 二选一transaction_id out_trade_no
	 */
	private String transaction_id;
	private String out_trade_no;
	private String out_refund_no;
	private String refund_id;
	private String refund_channel;
	private Integer refund_fee;
	private Integer settlement_refund_fee;
	private Integer total_fee;
	private Integer settlement_total_fee;
	private String fee_type;
	private Integer cash_fee;
	private Integer cash_refund_fee;

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

	public Integer getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee)
	{
		this.total_fee = total_fee;
	}

	public Integer getRefund_fee()
	{
		return refund_fee;
	}

	public void setRefund_fee(Integer refund_fee)
	{
		this.refund_fee = refund_fee;
	}

	public String getRefund_id()
	{
		return refund_id;
	}

	public void setRefund_id(String refund_id)
	{
		this.refund_id = refund_id;
	}

	public String getRefund_channel()
	{
		return refund_channel;
	}

	public void setRefund_channel(String refund_channel)
	{
		this.refund_channel = refund_channel;
	}

	public Integer getSettlement_refund_fee()
	{
		return settlement_refund_fee;
	}

	public void setSettlement_refund_fee(Integer settlement_refund_fee)
	{
		this.settlement_refund_fee = settlement_refund_fee;
	}

	public Integer getSettlement_total_fee()
	{
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(Integer settlement_total_fee)
	{
		this.settlement_total_fee = settlement_total_fee;
	}

	public String getFee_type()
	{
		return fee_type;
	}

	public void setFee_type(String fee_type)
	{
		this.fee_type = fee_type;
	}

	public Integer getCash_fee()
	{
		return cash_fee;
	}

	public void setCash_fee(Integer cash_fee)
	{
		this.cash_fee = cash_fee;
	}

	public Integer getCash_refund_fee()
	{
		return cash_refund_fee;
	}

	public void setCash_refund_fee(Integer cash_refund_fee)
	{
		this.cash_refund_fee = cash_refund_fee;
	}

	@Override
	public String toString()
	{
		return "WeiXinRefundResultDto [appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info
				+ ", nonce_str=" + nonce_str + ", sign=" + sign + ", transaction_id=" + transaction_id
				+ ", out_trade_no=" + out_trade_no + ", out_refund_no=" + out_refund_no + ", refund_id=" + refund_id
				+ ", refund_channel=" + refund_channel + ", refund_fee=" + refund_fee + ", settlement_refund_fee="
				+ settlement_refund_fee + ", total_fee=" + total_fee + ", settlement_total_fee=" + settlement_total_fee
				+ ", fee_type=" + fee_type + ", cash_fee=" + cash_fee + ", cash_refund_fee=" + cash_refund_fee + "]";
	}

}
