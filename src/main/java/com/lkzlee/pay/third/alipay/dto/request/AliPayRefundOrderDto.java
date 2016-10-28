package com.lkzlee.pay.third.alipay.dto.request;

import com.lkzlee.pay.third.dto.AbstThirdPayDto;

/***
 * 支付宝即时支付参数定义
 * @author lkzlee
 *
 */
public class AliPayRefundOrderDto extends AbstThirdPayDto
{
	private static final long serialVersionUID = 1L;
	private String notify_url;

	private String seller_email;
	private String seller_user_id;
	private String refund_date;
	private String batch_no;
	private String batch_num;
	/***
	 * 单笔数据集格式为：第一笔交易退款数据集#第二笔交易退款数据集#第三笔交易退款数据集…#第N笔交易退款数据集；交易退款数据集的格式为：原付款支付宝交易号^退款总金额^退款理由；不支持退分润功能。
	 */
	private String detail_data;

	public String getNotify_url()
	{
		return notify_url;
	}

	public void setNotify_url(String notify_url)
	{
		this.notify_url = notify_url;
	}

	public String getSeller_email()
	{
		return seller_email;
	}

	public void setSeller_email(String seller_email)
	{
		this.seller_email = seller_email;
	}

	public String getSeller_user_id()
	{
		return seller_user_id;
	}

	public void setSeller_user_id(String seller_user_id)
	{
		this.seller_user_id = seller_user_id;
	}

	public String getRefund_date()
	{
		return refund_date;
	}

	public void setRefund_date(String refund_date)
	{
		this.refund_date = refund_date;
	}

	public String getBatch_no()
	{
		return batch_no;
	}

	public void setBatch_no(String batch_no)
	{
		this.batch_no = batch_no;
	}

	public String getBatch_num()
	{
		return batch_num;
	}

	public void setBatch_num(String batch_num)
	{
		this.batch_num = batch_num;
	}

	public String getDetail_data()
	{
		return detail_data;
	}

	public void setDetail_data(String detail_data)
	{
		this.detail_data = detail_data;
	}

	@Override
	public String toString()
	{
		return "AliPayRefundOrderDto [notify_url=" + notify_url + ", seller_email=" + seller_email
				+ ", seller_user_id=" + seller_user_id + ", refund_date=" + refund_date + ", batch_no=" + batch_no
				+ ", batch_num=" + batch_num + ", detail_data=" + detail_data + "]";
	}

}
