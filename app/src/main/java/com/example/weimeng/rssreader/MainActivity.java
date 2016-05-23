package com.example.weimeng.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import zq.mdlib.mdviewpager.MaterialViewPager;


public class MainActivity extends ActionBarActivity
{

	private static final int HANDLER_REFRESH_VIEWPAGER = 0;

	MaterialViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init()
	{
		findViews();

		viewPager.getViewPager().setAdapter(new MainActivityAdapter(MainActivity.this, getSupportFragmentManager(), viewPager));
		// 这里必须全部加载，否则会视图错误
		viewPager.getViewPager().setOffscreenPageLimit(viewPager.getViewPager().getAdapter().getCount());
//		viewPager.getPagerTitleStrip().setViewPager(viewPager.getViewPager());
	}

	private void findViews()
	{
		viewPager = (MaterialViewPager) findViewById(R.id.main_viewpager);
	}
}
