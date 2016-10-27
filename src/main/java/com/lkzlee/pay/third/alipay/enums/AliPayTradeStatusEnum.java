package com.lkzlee.pay.third.alipay.enums;

/**
 * 支付宝交易状态枚举类
 * @author lkzlee
 * WAIT_BUYER_PAY 交易创建，等待买家付款。 
 * TRADE_CLOSED 在指定时间段内未支付时关闭的交易；在交易完成全额退款成功时关闭的交易。
 * TRADE_SUCCESS 交易成功，且可对该交易做操作，如：多级分润、退款等。
 * TRADE_PENDING 等待卖家收款（买家付款后，如果卖家账号被冻结）。
 * TRADE_FINISHED 交易成功且结束，即不可再做任何操作
 */
public enum AliPayTradeStatusEnum
{
	/***
	 * 
	 */
	WAIT_BUYER_PAY("WAIT_BUYER_PAY", "交易创建，等待买家付款"),
	/***
	 * 
	 */
	TRADE_CLOSED("TRADE_CLOSED", "在指定时间段内未支付时关闭的交易；在交易完成全额退款成功时关闭的交易"),
	/***
	 * 
	 */
	TRADE_SUCCESS("TRADE_SUCCESS", "交易成功，且可对该交易做操作，如：多级分润、退款等"),
	/***
	 * 
	 */
	TRADE_PENDING("TRADE_PENDING", "等待卖家收款（买家付款后，如果卖家账号被冻结）"),
	/***
	 * 
	 */
	TRADE_FINISHED("TRADE_FINISHED", "交易成功且结束，即不可再做任何操作");
	private String tradeStatus;
	private String tradeStatusDesc;

	private AliPayTradeStatusEnum(String tradeStatus, String tradeStatusDesc)
	{
		this.tradeStatus = tradeStatus;
		this.tradeStatusDesc = tradeStatusDesc;
	}

	public String getTradeStatus()
	{
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus)
	{
		this.tradeStatus = tradeStatus;
	}

	public String getTradeStatusDesc()
	{
		return tradeStatusDesc;
	}

	public void setTradeStatusDesc(String tradeStatusDesc)
	{
		this.tradeStatusDesc = tradeStatusDesc;
	}

}
