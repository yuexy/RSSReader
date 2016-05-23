package com.example.weimeng.rssreader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by weimeng on 2016/5/23.
 */
public class CustomDatabaseUtils extends SQLiteOpenHelper
{
	public static final String DATABASE_NAME = "rssreader";
	public static final int DATABASE_VERSION = 1;

	public static final String HISTORY_TABLE_NAME = "history";

	public CustomDatabaseUtils(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public CustomDatabaseUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
	{
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		createTableHistory(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//
	}

	private void createTableHistory(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE IF NOT EXISTS " + HISTORY_TABLE_NAME
				+ "(news_topic TEXT,"
				+ "news_time TEXT,"
				+ "news_des TEXT,"
				+ "news_add TEXT)";

		db.execSQL(sql);
	}
}
