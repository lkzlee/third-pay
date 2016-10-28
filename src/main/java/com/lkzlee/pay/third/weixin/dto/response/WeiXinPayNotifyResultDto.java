package com.lkzlee.pay.third.weixin.dto.response;

import java.io.Serializable;

/**
 * 微信通知回来参数
 * @author lkzlee
 *
 */
public class WeiXinPayNotifyResultDto extends AbstWeiXinPayBaseDto implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String appid;
	private String mch_id;
	private String fee_type;
	private String is_subscribe;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String openid;
	private String trade_type;
	private String bank_type;
	private String total_fee;
	private String cash_fee;
	private String transaction_id;
	private String out_trade_no;
	private String time_end;

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

	public String getFee_type()
	{
		return fee_type;
	}

	public void setFee_type(String fee_type)
	{
		this.fee_type = fee_type;
	}

	public String getIs_subscribe()
	{
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe)
	{
		this.is_subscribe = is_subscribe;
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

	public String getOpenid()
	{
		return openid;
	}

	public void setOpenid(String openid)
	{
		this.openid = openid;
	}

	public String getTrade_type()
	{
		return trade_type;
	}

	public void setTrade_type(String trade_type)
	{
		this.trade_type = trade_type;
	}

	public String getBank_type()
	{
		return bank_type;
	}

	public void setBank_type(String bank_type)
	{
		this.bank_type = bank_type;
	}

	public String getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(String total_fee)
	{
		this.total_fee = total_fee;
	}

	public String getCash_fee()
	{
		return cash_fee;
	}

	public void setCash_fee(String cash_fee)
	{
		this.cash_fee = cash_fee;
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

	public String getTime_end()
	{
		return time_end;
	}

	public void setTime_end(String time_end)
	{
		this.time_end = time_end;
	}

	@Override
	public String toString()
	{
		return "WeiXinPayNotifyResultDto [appid=" + appid + ", mch_id=" + mch_id + ", fee_type=" + fee_type
				+ ", is_subscribe=" + is_subscribe + ", device_info=" + device_info + ", nonce_str=" + nonce_str
				+ ", sign=" + sign + ", openid=" + openid + ", trade_type=" + trade_type + ", bank_type=" + bank_type
				+ ", total_fee=" + total_fee + ", cash_fee=" + cash_fee + ", transaction_id=" + transaction_id
				+ ", out_trade_no=" + out_trade_no + ", time_end=" + time_end + "]";
	}

}
