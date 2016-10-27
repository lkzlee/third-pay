package com.lkzlee.pay.third.weixin.dto.response;

import java.sql.Timestamp;

/**
 * 微信统一下单返回参数
 * @author lkzlee
 *
 */
public class WeiXinOrderResultDto extends WeiXinBaseResultDto
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
	private String trade_type;
	private String prepay_id;
	private String code_url;
	private Timestamp timeStamp;

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

	public String getResult_code()
	{
		return result_code;
	}

	public void setResult_code(String result_code)
	{
		this.result_code = result_code;
	}

	@Override
	public String getErr_code()
	{
		return err_code;
	}

	@Override
	public void setErr_code(String err_code)
	{
		this.err_code = err_code;
	}

	@Override
	public String getErr_code_des()
	{
		return err_code_des;
	}

	@Override
	public void setErr_code_des(String err_code_des)
	{
		this.err_code_des = err_code_des;
	}

	public String getTrade_type()
	{
		return trade_type;
	}

	public void setTrade_type(String trade_type)
	{
		this.trade_type = trade_type;
	}

	public String getPrepay_id()
	{
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id)
	{
		this.prepay_id = prepay_id;
	}

	public String getCode_url()
	{
		return code_url;
	}

	public void setCode_url(String code_url)
	{
		this.code_url = code_url;
	}

	public Timestamp getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp)
	{
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString()
	{
		return "WeiXinOrderResultDto [appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info
				+ ", nonce_str=" + nonce_str + ", sign=" + sign + ", result_code=" + result_code + ", err_code="
				+ err_code + ", err_code_des=" + err_code_des + ", trade_type=" + trade_type + ", prepay_id="
				+ prepay_id + ", code_url=" + code_url + ", timeStamp=" + timeStamp + "]";
	}

}
