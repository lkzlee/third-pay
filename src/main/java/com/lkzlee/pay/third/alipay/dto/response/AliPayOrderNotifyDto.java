package com.lkzlee.pay.third.alipay.dto.response;

/***
 * 支付宝即时支付通知返回参数定义
 * @author lkzlee
 *
 */
public class AliPayOrderNotifyDto extends AbstAliPayBaseDto
{
	private static final long serialVersionUID = 1L;

	private String out_trade_no;
	private String subject;
	private String payment_type;
	private String trade_no;
	private String trade_status;
	private String gmt_create;
	private String gmt_payment;
	private String gmt_close;
	private String refund_status;
	private String gmt_refund;
	private String seller_email;
	private String buyer_email;
	private String seller_id;
	private String buyer_id;
	private String price;
	private String total_fee;
	private String quantity;
	private String body;
	private String discount;
	private String is_total_fee_adjust;
	private String use_coupon;
	private String extra_common_param;
	/***
	 * 回传给商户此标识为qrpay时，表示对应交易为扫码支付。
	 * 目前只有qrpay一种回传值。
	 * 非扫码支付方式下，目前不会返回该参数。
	 */
	private String business_scene;

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

	public String getPayment_type()
	{
		return payment_type;
	}

	public void setPayment_type(String payment_type)
	{
		this.payment_type = payment_type;
	}

	public String getTrade_no()
	{
		return trade_no;
	}

	public void setTrade_no(String trade_no)
	{
		this.trade_no = trade_no;
	}

	public String getTrade_status()
	{
		return trade_status;
	}

	public void setTrade_status(String trade_status)
	{
		this.trade_status = trade_status;
	}

	public String getGmt_create()
	{
		return gmt_create;
	}

	public void setGmt_create(String gmt_create)
	{
		this.gmt_create = gmt_create;
	}

	public String getGmt_payment()
	{
		return gmt_payment;
	}

	public void setGmt_payment(String gmt_payment)
	{
		this.gmt_payment = gmt_payment;
	}

	public String getGmt_close()
	{
		return gmt_close;
	}

	public void setGmt_close(String gmt_close)
	{
		this.gmt_close = gmt_close;
	}

	public String getRefund_status()
	{
		return refund_status;
	}

	public void setRefund_status(String refund_status)
	{
		this.refund_status = refund_status;
	}

	public String getGmt_refund()
	{
		return gmt_refund;
	}

	public void setGmt_refund(String gmt_refund)
	{
		this.gmt_refund = gmt_refund;
	}

	public String getSeller_email()
	{
		return seller_email;
	}

	public void setSeller_email(String seller_email)
	{
		this.seller_email = seller_email;
	}

	public String getBuyer_email()
	{
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email)
	{
		this.buyer_email = buyer_email;
	}

	public String getSeller_id()
	{
		return seller_id;
	}

	public void setSeller_id(String seller_id)
	{
		this.seller_id = seller_id;
	}

	public String getBuyer_id()
	{
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id)
	{
		this.buyer_id = buyer_id;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(String total_fee)
	{
		this.total_fee = total_fee;
	}

	public String getQuantity()
	{
		return quantity;
	}

	public void setQuantity(String quantity)
	{
		this.quantity = quantity;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public String getDiscount()
	{
		return discount;
	}

	public void setDiscount(String discount)
	{
		this.discount = discount;
	}

	public String getIs_total_fee_adjust()
	{
		return is_total_fee_adjust;
	}

	public void setIs_total_fee_adjust(String is_total_fee_adjust)
	{
		this.is_total_fee_adjust = is_total_fee_adjust;
	}

	public String getUse_coupon()
	{
		return use_coupon;
	}

	public void setUse_coupon(String use_coupon)
	{
		this.use_coupon = use_coupon;
	}

	public String getExtra_common_param()
	{
		return extra_common_param;
	}

	public void setExtra_common_param(String extra_common_param)
	{
		this.extra_common_param = extra_common_param;
	}

	public String getBusiness_scene()
	{
		return business_scene;
	}

	public void setBusiness_scene(String business_scene)
	{
		this.business_scene = business_scene;
	}

	@Override
	public String toString()
	{
		return "AliPayOrderNotifyDto [out_trade_no=" + out_trade_no + ", subject=" + subject + ", payment_type="
				+ payment_type + ", trade_no=" + trade_no + ", trade_status=" + trade_status + ", gmt_create="
				+ gmt_create + ", gmt_payment=" + gmt_payment + ", gmt_close=" + gmt_close + ", refund_status="
				+ refund_status + ", gmt_refund=" + gmt_refund + ", seller_email=" + seller_email + ", buyer_email="
				+ buyer_email + ", seller_id=" + seller_id + ", buyer_id=" + buyer_id + ", price=" + price
				+ ", total_fee=" + total_fee + ", quantity=" + quantity + ", body=" + body + ", discount=" + discount
				+ ", is_total_fee_adjust=" + is_total_fee_adjust + ", use_coupon=" + use_coupon
				+ ", extra_common_param=" + extra_common_param + ", business_scene=" + business_scene + "]，super="
				+ super.toString();
	}

}
