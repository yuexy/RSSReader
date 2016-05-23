package com.example.weimeng.rssreader.net;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by weimeng on 2016/5/23.
 */
public class CustomNetUtils
{
	private static final int CONNECTION_TIME_OUT = 7 * 1000;
	private static final int SO_TIME_OUT = 8 * 10000;

	public static String downloadByGET(String url)
	{
		String res = null;

		HttpClient httpClient = new DefaultHttpClient();
		HttpParams httpParams = httpClient.getParams();

		/* 连接超时 */
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIME_OUT);

		/* 请求超时 */
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIME_OUT);

		HttpGet httpGet = new HttpGet(url);

		try
		{
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				res = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			}
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return res;
	}
}
