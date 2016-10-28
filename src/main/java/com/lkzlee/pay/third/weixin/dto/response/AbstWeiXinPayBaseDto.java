package com.lkzlee.pay.third.weixin.dto.response;

import java.io.Serializable;

import com.lkzlee.pay.third.dto.AbstThirdPayDto;

/**
 * 微信返回基本参数封装
 * @author lkzlee
 *
 */
public class AbstWeiXinPayBaseDto extends AbstThirdPayDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String return_code;
	private String return_msg;
	private String err_code;
	private String err_code_des;
	private String result_code;

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

	@Override
	public String toString()
	{
		return "WeiXinBaseResultDto [return_code=" + return_code + ", return_msg=" + return_msg + ", err_code="
				+ err_code + ", err_code_des=" + err_code_des + ", result_code=" + result_code + "]";
	}

}
