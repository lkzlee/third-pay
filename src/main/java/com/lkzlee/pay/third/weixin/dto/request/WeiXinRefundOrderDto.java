package com.lkzlee.pay.third.weixin.dto.request;

public class WeiXinRefundOrderDto extends WeiXinBaseDto
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * 二选一transaction_id out_trade_no
	 */
	private String transaction_id;
	private String out_trade_no;
	/***
	 * 商户退款单号
	 */
	private String out_refund_no;
	private Integer total_fee;
	private Integer refund_fee;
	private String refund_fee_type;
	private String op_user_id;
	private String refund_account;

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

	public String getRefund_fee_type()
	{
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refund_fee_type)
	{
		this.refund_fee_type = refund_fee_type;
	}

	public String getOp_user_id()
	{
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id)
	{
		this.op_user_id = op_user_id;
	}

	public String getRefund_account()
	{
		return refund_account;
	}

	public void setRefund_account(String refund_account)
	{
		this.refund_account = refund_account;
	}

	@Override
	public String toString()
	{
		return "WeiXinRefundOrderDto [transaction_id=" + transaction_id + ", out_trade_no=" + out_trade_no
				+ ", out_refund_no=" + out_refund_no + ", total_fee=" + total_fee + ", refund_fee=" + refund_fee
				+ ", refund_fee_type=" + refund_fee_type + ", op_user_id=" + op_user_id + ", refund_account="
				+ refund_account + "]";
	}

}
