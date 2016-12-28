package com.lkzlee.pay.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lkzlee.pay.third.weixin.dto.response.WeiXinOrderResultDto;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XstreamUtil
{
	private static Log LOG = LogFactory.getLog(XstreamUtil.class);
	private final static XStream xstream = new XStream(new DomDriver());

	public static void main(String[] args)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>" + "" + "" + "" + "<return_code><![CDATA[FAIL]]></return_code> ");
		sb.append("<return_msg>" + "" + "<![CDATA[appid参数长度有误]]>" + "</return_msg>");
		sb.append("</xml>");
		xstream.alias("xml", WeiXinOrderResultDto.class);
		WeiXinOrderResultDto resultDto = (WeiXinOrderResultDto) xstream.fromXML(sb.toString());
		System.out.println(resultDto);
	}

	public static <T> T fromXml(String xml, Class clz)
	{
		try
		{
			xml = xml.replaceAll("\\s+", "");
			xstream.alias("xml", clz);
			T t = (T) xstream.fromXML(xml);
			return t;
		}
		catch (Exception e)
		{
			LOG.fatal("解析xml出错。请检查,clz=" + clz, e);
			return null;
		}
	}

	public static <T> T fromXml(String xml, String rootName, Class clz)
	{
		try
		{
			xml = xml.replaceAll("\\s+", "");
			xstream.alias(rootName, clz);
			T t = (T) xstream.fromXML(xml);
			return t;
		}
		catch (Exception e)
		{
			LOG.fatal("解析xml出错。请检查,rootName=" + rootName + ",clz=" + clz, e);
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
			XStream xstream = new XStream(new DomDriver());
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
