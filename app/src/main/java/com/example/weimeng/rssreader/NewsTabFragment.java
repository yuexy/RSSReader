package com.example.weimeng.rssreader;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import zq.mdlib.mdviewpager.MaterialViewPagerHelper;
import zq.mdlib.mdwidget.ProgressBarCircular;

/**
 * Created by weimeng on 2016/5/23.
 */
public class NewsTabFragment extends BaseFragment
{
	private View rootView;
	private RecyclerView recyclerView;
	private LinearLayoutManager linearLayoutManager;
	private NewsTabRecyclerViewAdapter recyclerViewAdapter;

	String[][] myUrlCaptionMenu = {
			{"http://www.npr.org/rss/rss.php?id=1001", "Top Stories"},
			{"http://www.npr.org/rss/rss.php?id=1003", "U.S. News"},
			{"http://www.npr.org/rss/rss.php?id=1004", "World News"},
			{"http://www.npr.org/rss/rss.php?id=1006", "Business"},
			{"http://www.npr.org/rss/rss.php?id=1007", "Health & Science"},
			{"http://www.npr.org/rss/rss.php?id=1008", "Arts & Entertainment"},
			{"http://www.npr.org/rss/rss.php?id=1012", "Politics & Society"},
			{"http://www.npr.org/rss/rss.php?id=1021", "People & Places"},
			{"http://www.npr.org/rss/rss.php?id=1057", "Opinion"}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		if (rootView == null)
		{
			rootView = inflater.inflate(R.layout.news_tab_fragment, container, false);
			init();
		}

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
	}

	private void init()
	{
		findViews();

		linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(linearLayoutManager);    //RecyclerView set Layout Manager
		recyclerView.setHasFixedSize(true);

		recyclerViewAdapter = new NewsTabRecyclerViewAdapter(getActivity(), myUrlCaptionMenu);
		recyclerView.setAdapter(recyclerViewAdapter);

		MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView, null);
	}

	private void findViews()
	{
		recyclerView = (RecyclerView) rootView.findViewById(R.id.news_tab_recyclerview);
	}

	@Override
	public void update()
	{

	}
}
