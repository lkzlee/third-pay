package com.lkzlee.pay.bean;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.lkzlee.pay.exceptions.BusinessException;

public abstract class AbstConfigBean
{
	private static Properties props = null;

	private final static Log LOG = LogFactory.getLog(AbstConfigBean.class);

	@PostConstruct
	public void init()
	{
		try
		{
			props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(getConfigFileName()));
		}
		catch (Exception e)
		{
			LOG.fatal("#Init PayConfigBean error,初始化异常!Cause:", e);
		}
	}

	protected abstract String getConfigFileName();

	public static String getPayConfigValue(String key, String defaultValue)
	{
		if (props == null)
		{
			LOG.fatal("加载配置文件异常，初始化失败,返回默认值，key=" + key + ",getValue=" + defaultValue);
			return defaultValue;
		}
		return props.getProperty(key, defaultValue);
	}

	public static String getPayConfigValue(String key)
	{
		if (props == null)
		{
			LOG.fatal("加载配置文件异常，初始化失败,key=" + key);
			throw new BusinessException("props is null.加载配置文件失败");
		}
		if (!props.containsKey(key))
		{
			LOG.fatal("props not exist key.未配置该属性，key=" + key);
			throw new BusinessException("props not exit key.未配置该属性，key=" + key);
		}
		return props.getProperty(key);
	}
}
