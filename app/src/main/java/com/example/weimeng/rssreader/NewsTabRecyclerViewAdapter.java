package com.example.weimeng.rssreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weimeng.rssreader.subviews.newslist.NewsListActivity;

import java.util.List;

/**
 * Created by weimeng on 2016/5/23.
 */
public class NewsTabRecyclerViewAdapter extends RecyclerView.Adapter<NewsTabRecyclerViewAdapter.NewsCardViewHolder>
{
	private static final int TYPE_OTHER = 1;
	private static final int TYPE_PLACEHOLDER = 2;

	private final Context context;
	private final LayoutInflater layoutInflater;

	private String[][] cateList;

	private int windowHeight;
	private int sumHeight;

	private int lastAnimatePosition = -1;

	public NewsTabRecyclerViewAdapter(Context c, String[][] cl)
	{
		context = c;
		cateList = cl;

		layoutInflater = LayoutInflater.from(context);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		windowHeight = dm.heightPixels;

		sumHeight = 0;
	}

	@Override
	public int getItemViewType(int position)
	{
		switch (position)
		{
			case 0:
				return TYPE_PLACEHOLDER;
			default:
				return TYPE_OTHER;
		}
	}

	@Override
	public NewsCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view;

		if (viewType == TYPE_PLACEHOLDER)
		{
			view = LayoutInflater.from(parent.getContext()).inflate(com.zswd.zq.materiallib.R.layout.material_viewpager_placeholder, parent, false);
			return new NewsCardViewHolder(view, TYPE_PLACEHOLDER);
		}

		return new NewsCardViewHolder(layoutInflater.inflate(R.layout.news_tab_card, parent, false), TYPE_OTHER);
	}

	@Override
	public void onBindViewHolder(NewsCardViewHolder holder, int position)
	{
		if (getItemViewType(position) == TYPE_PLACEHOLDER)
			return;

		holder.NewsCardTitle.setText(cateList[position -1][1]);

		setAnimation(holder.NewsCardView, position);

		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		holder.viewOfItem.measure(w, h);

		sumHeight += holder.viewOfItem.getMeasuredHeight();

		if (position == getItemCount() - 1)
		{
			RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.viewOfItem.getLayoutParams();
			layoutParams.setMargins(layoutParams.leftMargin,
					layoutParams.topMargin,
					layoutParams.rightMargin,
					windowHeight > sumHeight ? (windowHeight - sumHeight) : layoutParams.bottomMargin);
			holder.viewOfItem.setLayoutParams(layoutParams);
		}
	}

	public void setAnimation(View viewToAnimate, int position)
	{
		if (position > lastAnimatePosition)
		{
			Animation animation = AnimationUtils.loadAnimation(context, R.anim.news_tab_list_anim);
			viewToAnimate.startAnimation(animation);
			lastAnimatePosition = position;
		}
	}

	@Override
	public int getItemCount()
	{
		return cateList.length + 1;
	}

	public class NewsCardViewHolder extends RecyclerView.ViewHolder
	{
		public TextView NewsCardTitle;
		public TextView NewsCardMore;

		public CardView NewsCardView;

		public View viewOfItem;

		public NewsCardViewHolder(View itemView, int type)
		{
			super(itemView);

			viewOfItem = itemView;

			if (type == TYPE_PLACEHOLDER)
				return;

			NewsCardTitle = (TextView) itemView.findViewById(R.id.news_tab_card_title);
			NewsCardMore = (TextView) itemView.findViewById(R.id.news_tab_card_more);

			NewsCardView = (CardView) itemView.findViewById(R.id.news_tab_card_card);

			//

			NewsCardView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent();
					intent.putExtra(BasicInfo.NEWS_ID, getPosition() - 1);
					intent.setClass(MyApplication.getInstance(), NewsListActivity.class);

					context.startActivity(intent);
				}
			});
		}
	}
}
