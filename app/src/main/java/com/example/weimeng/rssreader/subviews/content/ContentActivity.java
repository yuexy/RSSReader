package com.example.weimeng.rssreader.subviews.content;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;

import com.example.weimeng.rssreader.BasicInfo;
import com.example.weimeng.rssreader.R;

import zq.mdlib.mdwidget.ButtonIcon;

/**
 * Created by weimeng on 2016/5/23.
 */
public class ContentActivity extends ActionBarActivity
{
	private ButtonIcon back;
	private WebView content;

	private String add;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_content_layout);

		init();
	}

	private void init()
	{
		findViews();

		back.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		add = getIntent().getStringExtra(BasicInfo.NEWS_ADD);

		content.loadUrl(add);
	}

	private void findViews()
	{
		back = (ButtonIcon) findViewById(R.id.news_content_back);
		content = (WebView) findViewById(R.id.news_content_webview);
	}
}
