package com.example.weimeng.rssreader.net;

import com.example.weimeng.rssreader.nodes.News;

import java.util.List;

/**
 * Created by weimeng on 2016/5/24.
 */
public class GetNewsListThread extends GetInfoThread
{
	private List<News> res;

	private String url;

	public GetNewsListThread(String u)
	{
		this.url = u;
	}

	@Override
	public void run()
	{
		res = NewsNetUtils.getNews(url);

		if (onFinished == null)
			return;

		if (res == null)
			onFinished.onFailed();
		else
			onFinished.onSucceed();
	}

	public List<News> getRes()
	{
		return res;
	}
}
