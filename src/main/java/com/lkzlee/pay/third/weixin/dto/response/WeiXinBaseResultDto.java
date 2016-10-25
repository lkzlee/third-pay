package com.lkzlee.pay.third.weixin.dto.response;

import java.io.Serializable;

/**
 * 微信返回基本参数封装
 * @author liyongchao
 *
 */
public class WeiXinBaseResultDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String return_code;
	private String return_msg;
	private String err_code;
	private String err_code_des;

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

	@Override
	public String toString()
	{
		return "WeiXinBaseResultDto [return_code=" + return_code + ", return_msg=" + return_msg + ", err_code="
				+ err_code + ", err_code_des=" + err_code_des + "]";
	}
}
