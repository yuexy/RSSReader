package com.example.weimeng.rssreader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weimeng.rssreader.nodes.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weimeng on 2016/5/23.
 */
public class HistoryDatabaseUtils extends CustomDatabaseUtils
{
	public static final String TABLE_NAME = CustomDatabaseUtils.HISTORY_TABLE_NAME;

	public static HistoryDatabaseUtils historyDatabaseUtils;

	public static HistoryDatabaseUtils getInstance(Context context)
	{
		if (null == historyDatabaseUtils)
			historyDatabaseUtils = new HistoryDatabaseUtils(context);

		return historyDatabaseUtils;
	}

	public HistoryDatabaseUtils(Context context)
	{
		super(context);
	}

	public boolean insert(News news)
	{
		if (!hasHistoryByTopic(news.topic))
		{
			try
			{
				ContentValues values = new ContentValues();
				values.put("news_topic", news.topic);
				values.put("news_time", news.time);
				values.put("news_des", news.des);
				values.put("news_add", news.add);

				SQLiteDatabase db = getWritableDatabase();
				db.insert(TABLE_NAME, null, values);
				db.close();
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return true;
	}

	public List<News> getAllNews()
	{
		List<News> newsList = new ArrayList<>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

		try
		{
			while (cursor.moveToNext())
			{
				News news = new News();
				news.topic = cursor.getString(cursor.getColumnIndex("news_topic"));
				news.time = cursor.getString(cursor.getColumnIndex("news_time"));
				news.des = cursor.getString(cursor.getColumnIndex("news_des"));
				news.add = cursor.getString(cursor.getColumnIndex("news_add"));

				newsList.add(news);
			}
		}
		catch (Exception e)
		{
			newsList.clear();
		}
		finally
		{
			if (cursor != null)
			{
				cursor.close();
			}

			db.close();
		}


		return newsList;
	}

	public boolean hasHistoryByTopic(String t)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, "news_topic = ?", new String[]{t}, null, null, null, null);

		boolean hasNext = cursor.moveToNext();

		if (null != cursor)
			cursor.close();

		db.close();

		return hasNext;
	}

	public void deleteAllNews()
	{
		getWritableDatabase().delete(TABLE_NAME, null, null);
	}
}
