package com.example.weimeng.rssreader.subviews.history;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.weimeng.rssreader.R;
import com.example.weimeng.rssreader.db.HistoryDatabaseUtils;
import com.example.weimeng.rssreader.nodes.News;
import com.example.weimeng.rssreader.subviews.newslist.NewsListAdapter;

import java.util.List;

import zq.mdlib.mdwidget.ButtonIcon;

/**
 * Created by weimeng on 2016/5/23.
 */
public class HistoryActivity extends ActionBarActivity
{
	private ButtonIcon back;
	private ButtonIcon delete;
	private LinearLayout wrongMsg;

	private RecyclerView recyclerView;
	private LinearLayoutManager mLinearLayoutManager;
	private NewsListAdapter newsListAdapter;

	private List<News> newsList;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_layout);

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

		delete.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				HistoryDatabaseUtils.getInstance(HistoryActivity.this).deleteAllNews();
				Toast.makeText(HistoryActivity.this, "历史记录已清除", Toast.LENGTH_SHORT).show();
				finish();
			}
		});

		mLinearLayoutManager = new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(mLinearLayoutManager);
		recyclerView.setHasFixedSize(true);

		newsList = HistoryDatabaseUtils.getInstance(HistoryActivity.this).getAllNews();

		if (newsList.size() == 0)
		{
			wrongMsg.setVisibility(View.VISIBLE);
		}
		else
		{
			newsListAdapter = new NewsListAdapter(HistoryActivity.this, newsList);
			recyclerView.setAdapter(newsListAdapter);
		}
	}

	private void findViews()
	{
		back = (ButtonIcon) findViewById(R.id.history_back);
		delete = (ButtonIcon) findViewById(R.id.history_delete);
		wrongMsg = (LinearLayout) findViewById(R.id.history_wrong_info);

		recyclerView = (RecyclerView) findViewById(R.id.history_recyclerview);
	}
}
