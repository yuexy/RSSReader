package com.example.weimeng.rssreader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import zq.mdlib.mdviewpager.MaterialViewPager;

/**
 * Created by weimeng on 2016/5/23.
 */
public class MainActivityAdapter extends FragmentPagerAdapter
{
	private FragmentManager mFragmentManager;
	private MaterialViewPager mViewPager;
	private Context mContext;

	private int oldPosition = -1;

	public MainActivityAdapter(Context context, FragmentManager fm, MaterialViewPager viewPager)
	{
		super(fm);
		mFragmentManager = fm;
		mContext = context;
		mViewPager = viewPager;
	}

	@Override
	public Fragment getItem(int position)
	{
		return new NewsTabFragment();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object)
	{
		super.setPrimaryItem(container, position, object);

		//only if position changed
		if (position == oldPosition)
			return;
		oldPosition = position;

		Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_news);
		Drawable drawableBg = mContext.getResources().getDrawable(R.mipmap.img_tab_1);

		int color = mContext.getResources().getColor(R.color.colorfortab1);

		final int fadeDuration = 400;
		mViewPager.setBackgroudImageDrawable(drawableBg, fadeDuration);
		mViewPager.setColor(color, fadeDuration);
		mViewPager.setLogoImageDrawable(drawable, fadeDuration / 2);
	}

	@Override
	public int getCount()
	{
		return 1;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return "News";
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		BaseFragment baseFragment = (BaseFragment) super.instantiateItem(container, position);

		baseFragment.update();

		return baseFragment;
	}

	@Override
	public int getItemPosition(Object object)
	{
		return PagerAdapter.POSITION_NONE;
	}
}
