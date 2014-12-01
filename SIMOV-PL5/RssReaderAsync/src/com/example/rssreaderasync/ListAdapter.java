package com.example.rssreaderasync;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter{
	private final List<RSSItem> items;
	
	public ListAdapter(final Context context, final int itemResId,final List<RSSItem> items) {
		this.items = items;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return this.items.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.items.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		 View itemView = null;
		 final RSSItem row = this.items.get(arg0);
		 if (arg1 == null) {
			 LayoutInflater inflater = (LayoutInflater) arg2.getContext()
					 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 itemView = inflater.inflate(R.layout.feed_item, null);
	        } else {
	            itemView = arg1;
	        }
		 	TextView item_pubdate = (TextView) itemView.findViewById(R.id.item_pubdate);
		 	item_pubdate.setText(row.getPubDate());

	        TextView item_title = (TextView) itemView.findViewById(R.id.item_title);
	        item_title.setText(row.getTitle());
	        
	        return itemView;
	}

}
