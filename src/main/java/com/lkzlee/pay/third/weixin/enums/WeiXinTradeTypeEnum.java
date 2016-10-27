package com.lkzlee.pay.third.weixin.enums;

/***
 * 微信交易类型枚举
 * @author lkzlee
 *
 */
public enum WeiXinTradeTypeEnum
{
	JSAPI("JSAPI", "wap公众号端支付"), NATIVE("NATIVE", "微信app本地支付，包括pc端扫描支付"), APP("APP", "wap公众号端支付");
	private String tradeType;
	private String tradeDesc;

	private WeiXinTradeTypeEnum(String tradeType, String tradeDesc)
	{
		this.tradeType = tradeType;
		this.tradeDesc = tradeDesc;
	}

	public String getTradeType()
	{
		return tradeType;
	}

	public void setTradeType(String tradeType)
	{
		this.tradeType = tradeType;
	}

	public String getTradeDesc()
	{
		return tradeDesc;
	}

	public void setTradeDesc(String tradeDesc)
	{
		this.tradeDesc = tradeDesc;
	}

}
