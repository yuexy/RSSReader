package com.example.weimeng.rssreader.net;

/**
 * Created by weimeng on 2016/5/24.
 */
public class GetInfoThread extends Thread
{
	protected OnFinished onFinished;

	public void setOnFinishedInterface(OnFinished o)
	{
		this.onFinished = o;
	}

	public interface OnFinished
	{
		void onSucceed();
		void onFailed();
	}
}
