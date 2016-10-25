package com.lkzlee.pay.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * 使用HttpClient发送和接收Http请求
 * 
 *
 */
public class HttpClientUtil
{

	private static HttpClient httpClient;
	// 最大连接数
	private static final int MAX_CONNECTION = 100;
	// 每个route能使用的最大连接数，一般和MAX_CONNECTION取值一样
	private static final int MAX_CONCURRENT_CONNECTIONS = 100;
	// 建立连接的超时时间，单位毫秒
	private static final int CONNECTION_TIME_OUT = 200;
	// 请求超时时间，单位毫秒
	private static final int REQUEST_TIME_OUT = 200;
	// 请求配置，可以复用
	private static RequestConfig requestConfig;

	static
	{
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(REQUEST_TIME_OUT).setSoKeepAlive(true)
				.setTcpNoDelay(true).build();

		requestConfig = RequestConfig.custom().setSocketTimeout(REQUEST_TIME_OUT)
				.setConnectTimeout(CONNECTION_TIME_OUT).build();
		/**
		 * 每个默认的 ClientConnectionPoolManager 实现将给每个route创建不超过2个并发连接，最多20个连接总数。
		 */
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(MAX_CONNECTION);
		connManager.setDefaultMaxPerRoute(MAX_CONCURRENT_CONNECTIONS);
		connManager.setDefaultSocketConfig(socketConfig);

		httpClient = HttpClients.custom().setConnectionManager(connManager).build();
	}

	public static void main(String[] args)
	{
		testGet();
	}

	/**
	 * 测试get方法
	 */
	private static void testGet()
	{
		String url = "http://www.baidu.com";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("key", "95708f902ac2428ea119ec99fb70e6a3");
		paramMap.put("keywords", "互联网金融大厦");
		paramMap.put("city", "330100");
		paramMap.put("extensions", "all");

		try
		{
			System.out.println(get(url, paramMap));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param paramMap
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> paramMap, List<Header> headers) throws Exception
	{
		URIBuilder uriBuilder = new URIBuilder(url);
		if (paramMap != null)
		{
			// 添加请求参数
			for (Entry<String, String> entry : paramMap.entrySet())
			{
				uriBuilder.addParameter(entry.getKey(), entry.getValue());
			}
		}

		HttpPost httpPost = new HttpPost(uriBuilder.build());
		if (headers != null)
		{
			// 添加请求首部
			for (Header header : headers)
			{
				httpPost.addHeader(header);
			}
		}

		httpPost.setConfig(requestConfig);

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);

		return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param paramMap
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String text, List<Header> headers) throws Exception
	{
		URIBuilder uriBuilder = new URIBuilder(url);
		StringEntity entity = new StringEntity(text, "UTF-8");
		HttpPost httpPost = new HttpPost(uriBuilder.build());
		httpPost.setEntity(entity);
		if (headers != null)
		{
			// 添加请求首部
			for (Header header : headers)
			{
				httpPost.addHeader(header);
			}
		}
		httpPost.setConfig(requestConfig);
		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);
		return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
	}

	/**
	 * post请求，不带请求首部
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String text) throws Exception
	{
		return post(url, text, null);
	}

	/**
	 * post请求，不带请求首部
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> paramMap) throws Exception
	{

		return post(url, paramMap, null);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @param paramMap
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> paramMap, List<Header> headers) throws Exception
	{
		URIBuilder uriBuilder = new URIBuilder(url);
		if (paramMap != null)
		{
			// 添加请求参数
			for (Entry<String, String> entry : paramMap.entrySet())
			{
				uriBuilder.addParameter(entry.getKey(), entry.getValue());
			}
		}

		HttpGet httpGet = new HttpGet(uriBuilder.build());
		if (headers != null)
		{
			// 添加请求首部
			for (Header header : headers)
			{
				httpGet.addHeader(header);
			}
		}

		httpGet.setConfig(requestConfig);

		// 执行请求
		HttpResponse response = httpClient.execute(httpGet);

		return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
	}

	/**
	 * get请求，不带请求首部
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> paramMap) throws Exception
	{

		return get(url, paramMap, null);
	}

}
