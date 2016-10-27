package com.lkzlee.pay.third.alipay.dto.response;

import com.lkzlee.pay.dto.AbstThirdPayDto;

/***
 * 支付宝即时支付通知返回参数定义
 * @author lkzlee
 *
 */
public class AliPayRefundNotifyDto extends AbstThirdPayDto
{
	private static final long serialVersionUID = 1L;

	private String batch_no;
	private String success_num;
	/***
	 * 退款结果明细。退手续费结果返回格式：交易号^退款金额^处理结果$退费账号^退费账户ID^退费金额^处理结果；
	 * 不退手续费结果返回格式：交易号^退款金额^处理结果。
	 * 若退款申请提交成功，处理结果会返回“SUCCESS”。
	 * 若提交失败，退款的处理结果中会有报错码，参见业务错误码。
	 */
	private String result_details;

	public String getBatch_no()
	{
		return batch_no;
	}

	public void setBatch_no(String batch_no)
	{
		this.batch_no = batch_no;
	}

	public String getSuccess_num()
	{
		return success_num;
	}

	public void setSuccess_num(String success_num)
	{
		this.success_num = success_num;
	}

	public String getResult_details()
	{
		return result_details;
	}

	public void setResult_details(String result_details)
	{
		this.result_details = result_details;
	}

	@Override
	public String toString()
	{
		return "AliPayRefundNotifyDto [batch_no=" + batch_no + ", success_num=" + success_num + ", result_details="
				+ result_details + "]";
	}
}
