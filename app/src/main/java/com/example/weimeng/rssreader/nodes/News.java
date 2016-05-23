package com.example.weimeng.rssreader.nodes;

/**
 * Created by weimeng on 2016/5/23.
 */
public class News
{
	public String topic;
	public String time;
	public String des;
	public String add;

	public News()
	{
		//
	}

	public News(String topic, String time, String des, String add)
	{
		this.topic = topic;
		this.time = time;
		this.des = des;
		this.add = add;
	}
}
