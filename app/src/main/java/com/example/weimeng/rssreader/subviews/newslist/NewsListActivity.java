package com.example.weimeng.rssreader.subviews.newslist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weimeng.rssreader.BasicInfo;
import com.example.weimeng.rssreader.NewsTabFragment;
import com.example.weimeng.rssreader.R;
import com.example.weimeng.rssreader.net.GetInfoThread;
import com.example.weimeng.rssreader.net.GetNewsListThread;
import com.example.weimeng.rssreader.nodes.News;
import com.example.weimeng.rssreader.subviews.history.HistoryActivity;

import java.util.List;

import zq.mdlib.mdwidget.ButtonIcon;
import zq.mdlib.mdwidget.ProgressBarCircular;


/**
 * Created by weimeng on 2016/5/23.
 */
public class NewsListActivity extends ActionBarActivity
{
	private static final int HANDLER_GET_NEWS_LIST_SUCCEED = 0;
	private static final int HANDLER_GET_NEWS_LIST_FAILED = 1;

	private TextView title;
	private ButtonIcon back;
	private ButtonIcon history;

	private ProgressBarCircular progressBarCircular;
	private LinearLayout wrongMsg;

	private RecyclerView recyclerView;
	private LinearLayoutManager mLinearLayoutManager;
	private NewsListAdapter newsListAdapter;

	private int news_index = 0;

	private GetNewsListThread getNewsListThread;
	private List<News> newsList;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_list_layout);

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

		history.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(NewsListActivity.this, HistoryActivity.class);

				startActivity(intent);
//				Toast.makeText(NewsListActivity.this, "history", Toast.LENGTH_SHORT).show();
			}
		});

		news_index = getIntent().getIntExtra(BasicInfo.NEWS_ID, 0);

		title.setText(NewsTabFragment.myUrlCaptionMenu[news_index][1]);

		mLinearLayoutManager = new LinearLayoutManager(NewsListActivity.this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(mLinearLayoutManager);
		recyclerView.setHasFixedSize(true);

		progressBarCircular.setVisibility(View.VISIBLE);

		getNewsListThread = new GetNewsListThread(NewsTabFragment.myUrlCaptionMenu[news_index][0]);
		getNewsListThread.setOnFinishedInterface(new GetNewsListImpl());

		getNewsListThread.start();
	}

	private void findViews()
	{
		title = (TextView) findViewById(R.id.news_list_category);
		back = (ButtonIcon) findViewById(R.id.news_list_back);
		history = (ButtonIcon) findViewById(R.id.news_list_history);

		progressBarCircular = (ProgressBarCircular) findViewById(R.id.news_list_progress_bar);
		wrongMsg = (LinearLayout) findViewById(R.id.news_list_wrong_info);

		recyclerView = (RecyclerView) findViewById(R.id.news_list_recyclerview);
	}

	private Handler getNewsHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			progressBarCircular.setVisibility(View.GONE);

			switch (msg.what)
			{
			case HANDLER_GET_NEWS_LIST_SUCCEED:
				newsListAdapter = new NewsListAdapter(NewsListActivity.this, newsList);
				recyclerView.setAdapter(newsListAdapter);
				Toast.makeText(NewsListActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
				break;

			case HANDLER_GET_NEWS_LIST_FAILED:
				wrongMsg.setVisibility(View.VISIBLE);
				Toast.makeText(NewsListActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	class GetNewsListImpl implements GetInfoThread.OnFinished
	{
		@Override
		public void onSucceed()
		{
			Message msg = getNewsHandler.obtainMessage();
			msg.what = HANDLER_GET_NEWS_LIST_SUCCEED;

			newsList = getNewsListThread.getRes();

			getNewsHandler.sendMessage(msg);
		}

		@Override
		public void onFailed()
		{
			Message msg = getNewsHandler.obtainMessage();
			msg.what = HANDLER_GET_NEWS_LIST_FAILED;

			getNewsHandler.sendMessage(msg);
		}
	}
}
