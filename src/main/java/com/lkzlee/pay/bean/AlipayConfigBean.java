package com.lkzlee.pay.bean;

import org.springframework.stereotype.Service;

@Service("aliPayConfigBean")
public class AlipayConfigBean extends AbstConfigBean
{

	@Override
	protected String getConfigFileName()
	{
		return "alipay-config.properties";
	}

}
