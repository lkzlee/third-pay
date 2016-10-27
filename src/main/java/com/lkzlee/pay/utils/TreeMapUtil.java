package com.lkzlee.pay.utils;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TreeMapUtil
{
	public static TreeMap<String, String> getInitTreeMapAsc()
	{
		TreeMap<String, String> signTreeMap = new TreeMap<String, String>(new Comparator<String>() {
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
}
