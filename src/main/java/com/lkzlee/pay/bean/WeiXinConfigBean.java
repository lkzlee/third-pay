package com.lkzlee.pay.bean;

import org.springframework.stereotype.Service;

@Service("weiXinConfigBean")
public class WeiXinConfigBean extends AbstConfigBean
{

	@Override
	protected String getConfigFileName()
	{
		return "wxpay-config.properties";
	}

}
