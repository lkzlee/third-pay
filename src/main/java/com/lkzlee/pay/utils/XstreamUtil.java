package com.lkzlee.pay.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lkzlee.pay.third.weixin.dto.response.WeiXinOrderResultDto;
import com.lkzlee.pay.third.weixin.dto.response.WeiXinRefundResultDto;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XstreamUtil
{
	private static Log LOG = LogFactory.getLog(XstreamUtil.class);

	public static void main(String[] args)
	{
		XStream xstream = new XStream(new DomDriver());
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>" + "" + "" + "" + "<return_code><![CDATA[FAIL]]></return_code> ");
		sb.append("<return_msg>" + "" + "<![CDATA[appid参数长度有误]]>" + "</return_msg>");
		sb.append("</xml>");
		xstream.alias("xml", WeiXinOrderResultDto.class);
		WeiXinOrderResultDto resultDto = (WeiXinOrderResultDto) xstream.fromXML(sb.toString());
		System.out.println(resultDto);
		StringBuilder ss = new StringBuilder();
		ss.append("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx855e27a3d08ed53f]]></appid><mch_id><![CDATA[1370242202]]></mch_id><nonce_str><![CDATA[CBwmE4qD9OXGO0xc]]></nonce_str><sign><![CDATA[EBB1F9A0ECE14947C9EB32F93E3328FE]]></sign><result_code><![CDATA[SUCCESS]]></result_code><transaction_id><![CDATA[4008012001201701196886560833]]></transaction_id><out_trade_no><![CDATA[20170119200824LD1d16c353]]></out_trade_no><out_refund_no><![CDATA[20170119200824ODd8081d33]]></out_refund_no><refund_id><![CDATA[2008012001201701200760933256]]></refund_id><refund_channel><![CDATA[]]></refund_channel><refund_fee>1</refund_fee><coupon_refund_fee>0</coupon_refund_fee><total_fee>1</total_fee><cash_fee>1</cash_fee><coupon_refund_count>0</coupon_refund_count><cash_refund_fee>1</cash_refund_fee></xml>");
		WeiXinRefundResultDto refundResultDto = fromXml(ss.toString(), WeiXinRefundResultDto.class);
		System.out.println(refundResultDto);
	}

	public static <T> T fromXml(String xml, Class clz)
	{
		try
		{
			XStream xstream = new XStream(new DomDriver());
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
			XStream xstream = new XStream(new DomDriver());
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
