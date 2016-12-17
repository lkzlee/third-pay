package com.lkzlee.pay.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XstreamUtil
{
	private static XStream xstream = new XStream(new DomDriver());
	private static Log LOG = LogFactory.getLog(XstreamUtil.class);

	/***
	 * 默认为<xml></xml>
	 * @param xml
	 * @param clz
	 * @return
	 */
	public static <T> T fromXml(String xml, Class clz)
	{
		try
		{
			xstream.alias("xml", clz);
			return (T) xstream.fromXML(xml, clz);
		}
		catch (Exception e)
		{
			LOG.fatal("解析xml出错。请检查,xml=" + xml, e);
			return null;
		}
	}

	/***
	 * 根节点为<rootName></rootName>
	 * @param obj
	 * @return
	 */
	public static <T> T fromXmlWtihRoot(String xml, Class clz, String rootName)
	{
		try
		{
			xstream.alias(rootName, clz);
			return (T) xstream.fromXML(xml, clz);
		}
		catch (Exception e)
		{
			LOG.fatal("解析xml出错。请检查,xml=" + xml, e);
			return null;
		}
	}

	/***
	 * 默认为<xml></xml>
	 * @param obj
	 * @return
	 */
	public static String toXml(Map<String, String> obj)
	{
		try
		{
			StringBuilder sb = new StringBuilder("<xml>");
			for (Entry<String, String> ent : obj.entrySet())
			{
				sb.append("<" + ent.getKey() + ">" + ent.getValue() + "</" + ent.getKey() + ">");
			}
			sb.append("</xml>");
			return sb.toString();
		}
		catch (Exception e)
		{
			LOG.fatal("解析xml出错。请检查,obj=" + obj, e);
			return null;
		}
	}

	/***
	 * 根节点为<rootName></rootName>
	 * @param obj
	 * @return
	 */
	public static String toXmlWithRoot(Object obj, String rootName)
	{
		try
		{
			xstream.alias(rootName, obj.getClass());
			return xstream.toXML(obj);
		}
		catch (Exception e)
		{
			LOG.fatal("解析xml出错。请检查,obj=" + obj, e);
			return null;
		}
	}
}
