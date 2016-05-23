package com.example.weimeng.rssreader.subviews.newslist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weimeng.rssreader.BasicInfo;
import com.example.weimeng.rssreader.MyApplication;
import com.example.weimeng.rssreader.R;
import com.example.weimeng.rssreader.db.HistoryDatabaseUtils;
import com.example.weimeng.rssreader.nodes.News;
import com.example.weimeng.rssreader.subviews.content.ContentActivity;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by weimeng on 2016/5/24.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>
{
	private Context context;
	private List<News> newsList;
	private LayoutInflater layoutInflater;

	public NewsListAdapter(Context c, List<News> l)
	{
		this.context = c;
		this.newsList = l;

		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return new NewsListViewHolder(layoutInflater.inflate(R.layout.news_list_card, parent, false));
	}

	@Override
	public void onBindViewHolder(NewsListViewHolder holder, int position)
	{
		holder.newsTitle.setText(newsList.get(position).topic);
		holder.newsTime.setText(newsList.get(position).time);
		holder.newsDes.setText(newsList.get(position).des);
	}

	@Override
	public int getItemCount()
	{
		return newsList.size();
	}

	public class NewsListViewHolder extends RecyclerView.ViewHolder
	{
		public TextView newsTitle;
		public TextView newsTime;
		public TextView newsDes;

		public CardView newsCard;

		public NewsListViewHolder(View itemView)
		{
			super(itemView);

			newsTitle = (TextView) itemView.findViewById(R.id.news_list_card_title);
			newsTime = (TextView) itemView.findViewById(R.id.news_list_card_time);
			newsDes = (TextView) itemView.findViewById(R.id.news_list_card_des);

			newsCard = (CardView) itemView.findViewById(R.id.news_list_card_card);

			newsCard.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					HistoryDatabaseUtils.getInstance(MyApplication.getInstance()).insert(newsList.get(getPosition()));

					Intent intent = new Intent();
					intent.putExtra(BasicInfo.NEWS_ADD, newsList.get(getPosition()).add);
					intent.setClass(MyApplication.getInstance(), ContentActivity.class);

					context.startActivity(intent);
				}
			});
		}
	}
}
