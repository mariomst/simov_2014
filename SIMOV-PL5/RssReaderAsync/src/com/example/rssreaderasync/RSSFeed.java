package com.example.rssreaderasync;

import java.util.ArrayList;
import java.util.List;

public class RSSFeed {
	private String _title = null;
	private String _pubdate = null;
	private int _itemcount = 0;
	private List<RSSItem> _itemlist;
	
	
	RSSFeed()
	{
		//_itemlist = new Vector<RSSItem>(0);
		_itemlist = new ArrayList <RSSItem>();
	}
	int addItem(RSSItem item)
	{
		_itemlist.add(item);
		_itemcount++;
		return _itemcount;
	}
	RSSItem getItem(int position)
	{
		return _itemlist.get(position);
	}
	List<RSSItem> getAllItems()
	{
		return _itemlist;
	}
	int getItemCount()
	{
		return _itemcount;
	}
	void setTitle(String title)
	{
		_title = title;
	}
	void setPubDate(String pubdate)
	{
		_pubdate = pubdate;
	}
	String getTitle()
	{
		return _title;
	}
	String getPubDate()
	{
		return _pubdate;
	}


}
