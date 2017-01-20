package com.lkzlee.pay.third.weixin.dto.response;

import java.io.Serializable;

import com.lkzlee.pay.third.dto.AbstThirdPayDto;

/***
 * 微信退款查下dto
 * @author lkzlee
 *
 */
public class WeiXinQueryRefundResultDto extends AbstThirdPayDto implements Serializable
{

	/**
	 * <xml>
	 * <appid><![CDATA[wx855e27a3d08ed53f]]></appid>
	 * <cash_fee><![CDATA[4]]></cash_fee>
	 * <mch_id><![CDATA[1370242202]]></mch_id>
	 * <nonce_str><![CDATA[B5YyNpWEpVHBauEU]]></nonce_str>
	 * <out_refund_no_0><![CDATA[20170120130457OD7cfeb16a]]></out_refund_no_0>
	 * <out_trade_no><![CDATA[20170120130457LD9ce72fe4]]></out_trade_no>
	 * <refund_channel_0><![CDATA[ORIGINAL]]></refund_channel_0>
	 * <refund_count>1</refund_count>
	 * <refund_fee>2</refund_fee>
	 * <refund_fee_0>2</refund_fee_0>
	 * <refund_id_0><![CDATA[2008012001201701200761513892]]></refund_id_0>
	 * <refund_recv_accout_0><![CDATA[支付用户的零钱]]></refund_recv_accout_0>
	 * <refund_status_0><![CDATA[SUCCESS]]></refund_status_0>
	 * <result_code><![CDATA[SUCCESS]]></result_code>
	 * <return_code><![CDATA[SUCCESS]]></return_code>
	 * <return_msg><![CDATA[OK]]></return_msg>
	 * <sign><![CDATA[9929C64213DE6170F09669D263A50DCE]]></sign>
	 * <total_fee><![CDATA[4]]></total_fee>
	 * <transaction_id><![CDATA[4008012001201701206947904161]]></transaction_id>
	 * </xml>
	 */
	private static final long serialVersionUID = 1L;
	private String return_code;
	private String return_msg;
	private String err_code;
	private String err_code_des;
	private String result_code;

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

	private Integer total_fee;
	private Integer settlement_total_fee;
	private String fee_type;
	private Integer cash_fee;
	private Integer refund_count;
	private String out_refund_no_0;
	private String refund_id_0;
	private String refund_channel_0;
	private Integer refund_fee;
	private Integer refund_fee_0;
	private Integer settlement_refund_fee_0;
	private String refund_account;
	private String refund_status_0;
	private String refund_recv_accout_0;

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

	public String getReturn_code()
	{
		return return_code;
	}

	public void setReturn_code(String return_code)
	{
		this.return_code = return_code;
	}

	public String getReturn_msg()
	{
		return return_msg;
	}

	public void setReturn_msg(String return_msg)
	{
		this.return_msg = return_msg;
	}

	public String getErr_code()
	{
		return err_code;
	}

	public void setErr_code(String err_code)
	{
		this.err_code = err_code;
	}

	public String getErr_code_des()
	{
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des)
	{
		this.err_code_des = err_code_des;
	}

	public String getResult_code()
	{
		return result_code;
	}

	public void setResult_code(String result_code)
	{
		this.result_code = result_code;
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

	public Integer getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee)
	{
		this.total_fee = total_fee;
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

	public Integer getRefund_count()
	{
		return refund_count;
	}

	public void setRefund_count(Integer refund_count)
	{
		this.refund_count = refund_count;
	}

	public String getOut_refund_no_0()
	{
		return out_refund_no_0;
	}

	public void setOut_refund_no_0(String out_refund_no_0)
	{
		this.out_refund_no_0 = out_refund_no_0;
	}

	public String getRefund_id_0()
	{
		return refund_id_0;
	}

	public void setRefund_id_0(String refund_id_0)
	{
		this.refund_id_0 = refund_id_0;
	}

	public String getRefund_channel_0()
	{
		return refund_channel_0;
	}

	public void setRefund_channel_0(String refund_channel_0)
	{
		this.refund_channel_0 = refund_channel_0;
	}

	public Integer getRefund_fee_0()
	{
		return refund_fee_0;
	}

	public void setRefund_fee_0(Integer refund_fee_0)
	{
		this.refund_fee_0 = refund_fee_0;
	}

	public Integer getSettlement_refund_fee_0()
	{
		return settlement_refund_fee_0;
	}

	public void setSettlement_refund_fee_0(Integer settlement_refund_fee_0)
	{
		this.settlement_refund_fee_0 = settlement_refund_fee_0;
	}

	public String getRefund_account()
	{
		return refund_account;
	}

	public void setRefund_account(String refund_account)
	{
		this.refund_account = refund_account;
	}

	public String getRefund_status_0()
	{
		return refund_status_0;
	}

	public void setRefund_status_0(String refund_status_0)
	{
		this.refund_status_0 = refund_status_0;
	}

	public String getRefund_recv_accout_0()
	{
		return refund_recv_accout_0;
	}

	public void setRefund_recv_accout_0(String refund_recv_accout_0)
	{
		this.refund_recv_accout_0 = refund_recv_accout_0;
	}

	public Integer getRefund_fee()
	{
		return refund_fee;
	}

	public void setRefund_fee(Integer refund_fee)
	{
		this.refund_fee = refund_fee;
	}

	@Override
	public String toString()
	{
		return "WeiXinQueryRefundResultDto [return_code=" + return_code + ", return_msg=" + return_msg + ", err_code="
				+ err_code + ", err_code_des=" + err_code_des + ", result_code=" + result_code + ", appid=" + appid
				+ ", mch_id=" + mch_id + ", device_info=" + device_info + ", nonce_str=" + nonce_str + ", sign=" + sign
				+ ", transaction_id=" + transaction_id + ", out_trade_no=" + out_trade_no + ", total_fee=" + total_fee
				+ ", settlement_total_fee=" + settlement_total_fee + ", fee_type=" + fee_type + ", cash_fee="
				+ cash_fee + ", refund_count=" + refund_count + ", out_refund_no_0=" + out_refund_no_0
				+ ", refund_id_0=" + refund_id_0 + ", refund_channel_0=" + refund_channel_0 + ", refund_fee="
				+ refund_fee + ", refund_fee_0=" + refund_fee_0 + ", settlement_refund_fee_0="
				+ settlement_refund_fee_0 + ", refund_account=" + refund_account + ", refund_status_0="
				+ refund_status_0 + ", refund_recv_accout_0=" + refund_recv_accout_0 + "]";
	}

}
