package com.example.weimeng.rssreader;

import android.app.Application;

/**
 * Created by weimeng on 2016/5/23.
 */
public class MyApplication extends Application
{
	private static final String TAG = MyApplication.class.getSimpleName();
	private static MyApplication myApplication = null;

	public static MyApplication getInstance()
	{
		return myApplication;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		myApplication = this;
	}
}
