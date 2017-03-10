package com.lkzlee.pay.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.lkzlee.pay.enums.SignTypeEnum;
import com.lkzlee.pay.third.alipay.dto.response.AliPayRefundNotifyDto;

public class TreeMapUtil
{
	public static TreeMap<String, String> getInitTreeMapAsc()
	{
		TreeMap<String, String> signTreeMap = new TreeMap<String, String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2)
			{
				return o1.compareTo(o2);
			}
		});
		return signTreeMap;
	}

	public static TreeMap<String, String> getInitTreeMapDesc()
	{
		TreeMap<String, String> signTreeMap = new TreeMap<String, String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2)
			{
				return o2.compareTo(o1);
			}
		});
		return signTreeMap;
	}

	public static String getTreeMapString(TreeMap<String, String> treeMap)
	{
		StringBuffer rs = new StringBuffer();
		for (Entry<String, String> key : treeMap.entrySet())
		{
			rs.append("&" + key.getKey() + "=" + key.getValue());
		}
		return rs.toString().substring(1);
	}

	public static String getTreeMapStringWithEncode(TreeMap<String, String> treeMap, String encode)
			throws UnsupportedEncodingException
	{
		StringBuffer rs = new StringBuffer();
		for (Entry<String, String> key : treeMap.entrySet())
		{
			rs.append("&" + key.getKey() + "=" + URLEncoder.encode(key.getValue(), encode));
		}
		return rs.toString().substring(1);
	}

	public static void setFiledParamToMapInfo(Object weixinResult, TreeMap<String, String> sourceMap, Class clazz)
			throws IllegalAccessException
	{
		if (weixinResult == null || clazz == null)
			return;
		Field fs[] = clazz.getDeclaredFields();
		for (int i = 0; null != fs && i < fs.length; i++)
		{
			if ("serialVersionUID".equals(fs[i].getName()))
			{
				continue;
			}
			fs[i].setAccessible(true);
			Object value = fs[i].get(weixinResult);
			if (value == null)
			{
				continue;
			}
			if ((value instanceof String) && StringUtils.isEmpty(value.toString()))
			{
				continue;
			}
			sourceMap.put(fs[i].getName(), value + "");
		}
		Class superClass = clazz.getSuperclass();
		if (superClass == null)
			return;
		setFiledParamToMapInfo(weixinResult, sourceMap, superClass);
	}

	public static void main(String[] args) throws IllegalAccessException
	{
		AliPayRefundNotifyDto aliPayNotifyDto = new AliPayRefundNotifyDto();
		aliPayNotifyDto.setBatch_no("xxxx001");
		aliPayNotifyDto.setSign_type("MD5");
		aliPayNotifyDto.setNotify_time("2016-10-28 18:12:24");
		aliPayNotifyDto.setSign("9D39DA2257049B38894D2CD228B763AD");
		TreeMap<String, String> sourceMap = TreeMapUtil.getInitTreeMapAsc();

		TreeMapUtil.setFiledParamToMapInfo(aliPayNotifyDto, sourceMap, aliPayNotifyDto.getClass());
		System.out.println(sourceMap);
		String sign = null;
		if (sourceMap.containsKey("sign"))
			sign = sourceMap.get("sign");
		sourceMap.remove("sign");
		String source = TreeMapUtil.getTreeMapString(sourceMap);
		System.out.println("source=" + source);
		String calcSign = SignTypeEnum.MD5.sign(source, null);
		System.out.println(sourceMap);
		if (!calcSign.equals(sign))
		{
			System.out.println("验签不通过，请检查，calcSign=" + calcSign + ",sign=" + sign);
		}
		else
		{
			System.out.println("验签通过，请检查，calcSign=" + calcSign + ",sign=" + sign);
		}

	}
}
