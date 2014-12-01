package com.example.rssreaderasync;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler {
	RSSFeed feed;
	RSSItem item;
	int depth = 0;
	//outer
	final int RSS = 1;
	final int CHANNEL = 2;
	final int ITEM = 3;
	
	int curr_element = 0;
	//options
	final int TITLE = 1;
	final int LINK = 2;
	final int DESCRIPTION = 3;
	final int CATEGORY = 4;
	final int PUBDATE = 5;
	
	
	
	RSSHandler()
	{
		
	}
	RSSFeed getFeed()
	{
		return feed;
	}
	
	public void startDocument() throws SAXException
	{
		feed = new RSSFeed();
		depth = 0;

	}
	public void endDocument() throws SAXException
	{
	}
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException
	{
		if (localName.equals("rss"))
		{
			depth++; //RSS
			return;
		}
		if (localName.equals("channel"))
		{
			depth++;//CHANNEL
			return;
		}
		if (localName.equals("item"))
		{
			depth++;//ITEM
			item = new RSSItem();
			return;
		}
		
		switch(depth){
			case CHANNEL:
				if (localName.equals("title"))
				{
					curr_element = TITLE;
				}else if (localName.equals("pubDate"))
				{
					curr_element = PUBDATE;
				}
				break;
			case ITEM:
				if (localName.equals("title"))
				{
					curr_element = TITLE;
				}else if (localName.equals("description"))
				{
					curr_element = DESCRIPTION;
				}else if (localName.equals("link"))
				{
					curr_element = LINK;
				}else if (localName.equals("category"))
				{
					curr_element = CATEGORY;
				}else if (localName.equals("pubDate"))
				{
					curr_element = PUBDATE;
				}
				break;
		}
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		if (localName.equals("item"))
		{
			feed.addItem(item);
			depth--;
		}else if (localName.equals("channel"))
		{
			depth--;
		} else if (localName.equals("rss"))
		{
			depth--;
		}
		
	}
	 
	public void characters(char ch[], int start, int length)
	{
		String theString = new String(ch,start,length);
		switch(depth){
		case CHANNEL:
			switch(curr_element){
			case TITLE:
				feed.setTitle(theString);
				break;
			case PUBDATE:
				feed.setPubDate(theString);
				break;
			}
			curr_element = 0;
			break;
		case ITEM:
			switch(curr_element){
			case TITLE:
				item.setTitle(theString);
				break;
			case DESCRIPTION:
				item.setDescription(theString);
				break;
			case LINK:
				item.setLink(theString);
				break;
			case CATEGORY:
				item.setCategory(theString);
				break;
			case PUBDATE:
				item.setPubDate(theString);
				break;
			}
			curr_element = 0;
			break;
		}
	}

}
