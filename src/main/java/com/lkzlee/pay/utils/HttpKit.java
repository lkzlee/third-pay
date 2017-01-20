/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.lkzlee.pay.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

/**
 * https 请求 微信为https的请求
 *
 * @author L.cm
 * @date 2013-10-9 下午2:40:19
 */
public class HttpKit
{
	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * @return 返回类型:
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @description 功能描述: get 请求
	 */
	public static String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException,
			ExecutionException, InterruptedException
	{
		AsyncHttpClient http = new AsyncHttpClient();
		AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
		builder.setBodyEncoding(DEFAULT_CHARSET);
		if (params != null && !params.isEmpty())
		{
			Set<String> keys = params.keySet();
			for (String key : keys)
			{
				builder.addQueryParameter(key, params.get(key));
			}
		}

		if (headers != null && !headers.isEmpty())
		{
			Set<String> keys = headers.keySet();
			for (String key : keys)
			{
				builder.addHeader(key, params.get(key));
			}
		}
		Future<Response> f = builder.execute();
		String body = f.get().getResponseBody(DEFAULT_CHARSET);
		http.close();
		return body;
	}

	/**
	 * @return 返回类型:
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @description 功能描述: get 请求
	 */
	public static String get(String url) throws KeyManagementException, NoSuchAlgorithmException,
			NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException,
			InterruptedException
	{
		return get(url, null);
	}

	/**
	 * @return 返回类型:
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws UnsupportedEncodingException
	 * @description 功能描述: get 请求
	 */
	public static String get(String url, Map<String, String> params) throws KeyManagementException,
			NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException,
			ExecutionException, InterruptedException
	{
		return get(url, params, null);
	}

	/**
	 * @return 返回类型:
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @description 功能描述: POST 请求
	 */
	public static String post(String url, Map<String, String> params) throws IOException, ExecutionException,
			InterruptedException
	{
		AsyncHttpClient http = new AsyncHttpClient();
		AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
		builder.setBodyEncoding(DEFAULT_CHARSET);
		if (params != null && !params.isEmpty())
		{
			Set<String> keys = params.keySet();
			for (String key : keys)
			{
				builder.addParameter(key, params.get(key));
			}
		}
		Future<Response> f = builder.execute();
		String body = f.get().getResponseBody(DEFAULT_CHARSET);
		http.close();
		return body;
	}

	/**
	 * 上传媒体文件
	 *
	 * @param url
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String url, File file) throws IOException, NoSuchAlgorithmException,
			NoSuchProviderException, KeyManagementException, ExecutionException, InterruptedException
	{
		AsyncHttpClient http = new AsyncHttpClient();
		AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
		builder.setBodyEncoding(DEFAULT_CHARSET);
		String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; // 定义数据分隔线
		builder.setHeader("connection", "Keep-Alive");
		builder.setHeader("user-agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
		builder.setHeader("Charsert", "UTF-8");
		builder.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
		builder.setBody(new UploadEntityWriter(end_data, file));

		Future<Response> f = builder.execute();
		String body = f.get().getResponseBody(DEFAULT_CHARSET);
		http.close();
		return body;
	}

	public static String post(String url, String s) throws IOException, ExecutionException, InterruptedException
	{
		AsyncHttpClient http = new AsyncHttpClient();
		AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
		builder.setBodyEncoding(DEFAULT_CHARSET);
		builder.setBody(s);
		Future<Response> f = builder.execute();
		String body = f.get().getResponseBody(DEFAULT_CHARSET);
		http.close();
		return body;
	}

	public static String postXml(String preOrderUrl, String xml) throws IOException
	{
		URL url = new URL(preOrderUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(xml.getBytes("utf-8"));
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		StringBuffer line = new StringBuffer();
		String result = "";
		while ((result = br.readLine()) != null)
		{
			line.append(StringUtils.trimToEmpty(result));
		}
		System.out.println("--data result=" + line);
		return line.toString();
	}

	public static String postXmlWthCaByPKCS12(String preOrderUrl, String xml, String caPath, String passwd)
			throws Exception
	{
		SSLConnectionSocketFactory sslsf = getSSLContext(caPath, passwd);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try
		{

			HttpPost httpPost = new HttpPost(preOrderUrl);
			StringEntity reqEntity = new StringEntity(xml, "UTF-8");
			reqEntity.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			StringBuffer result = new StringBuffer();
			try
			{
				HttpEntity entity = response.getEntity();
				if (entity != null)
				{
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),
							"UTF-8"));
					String text = null;
					while ((text = bufferedReader.readLine()) != null)
					{
						result.append(text);
					}

				}
				EntityUtils.consume(entity);
				return result.toString();
			}
			finally
			{
				response.close();
			}
		}
		finally
		{
			httpclient.close();
		}
	}

	private static SSLConnectionSocketFactory getSSLContext(String caPath, String passwd) throws KeyStoreException,
			FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException,
			UnrecoverableKeyException
	{
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(caPath));
		try
		{
			keyStore.load(instream, passwd.toCharArray());
		}
		finally
		{
			instream.close();
		}
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, passwd.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]
		{ "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		return sslsf;
	}
}