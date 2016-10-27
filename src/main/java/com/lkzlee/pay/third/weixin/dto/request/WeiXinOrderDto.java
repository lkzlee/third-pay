package com.lkzlee.pay.third.weixin.dto.request;

/**
 * 微信统一下单参数
 * @author lkzlee
 *
 */
public class WeiXinOrderDto extends WeiXinBaseDto
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String body;
	private String detail;
	private String attach;
	/***
	 * 下单传递参数，必传
	 */
	private String out_trade_no;
	private String fee_type;
	private Integer total_fee;
	private String spbill_create_ip;
	/***
	 * 订单生成时间，格式为yyyyMMddHHmmss
	 */
	private String time_start;
	/**
	 * 订单生成时间，格式为yyyyMMddHHmmss
	 */
	private String time_expire;
	private String goods_tag;
	private String notify_url;
	private String trade_type;
	private String product_id;
	private String limit_pay;
	private String openid;

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public String getAttach()
	{
		return attach;
	}

	public void setAttach(String attach)
	{
		this.attach = attach;
	}

	public String getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type()
	{
		return fee_type;
	}

	public void setFee_type(String fee_type)
	{
		this.fee_type = fee_type;
	}

	public Integer getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee)
	{
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip()
	{
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip)
	{
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start()
	{
		return time_start;
	}

	public void setTime_start(String time_start)
	{
		this.time_start = time_start;
	}

	public String getTime_expire()
	{
		return time_expire;
	}

	public void setTime_expire(String time_expire)
	{
		this.time_expire = time_expire;
	}

	public String getGoods_tag()
	{
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag)
	{
		this.goods_tag = goods_tag;
	}

	public String getNotify_url()
	{
		return notify_url;
	}

	public void setNotify_url(String notify_url)
	{
		this.notify_url = notify_url;
	}

	public String getTrade_type()
	{
		return trade_type;
	}

	public void setTrade_type(String trade_type)
	{
		this.trade_type = trade_type;
	}

	public String getProduct_id()
	{
		return product_id;
	}

	public void setProduct_id(String product_id)
	{
		this.product_id = product_id;
	}

	public String getLimit_pay()
	{
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay)
	{
		this.limit_pay = limit_pay;
	}

	public String getOpenid()
	{
		return openid;
	}

	public void setOpenid(String openid)
	{
		this.openid = openid;
	}

	@Override
	public String toString()
	{
		return "WeiXinOrderDto [body=" + body + ", detail=" + detail + ", attach=" + attach + ", out_trade_no="
				+ out_trade_no + ", fee_type=" + fee_type + ", total_fee=" + total_fee + ", spbill_create_ip="
				+ spbill_create_ip + ", time_start=" + time_start + ", time_expire=" + time_expire + ", goods_tag="
				+ goods_tag + ", notify_url=" + notify_url + ", trade_type=" + trade_type + ", product_id="
				+ product_id + ", limit_pay=" + limit_pay + ", openid=" + openid + "]";
	}

}
