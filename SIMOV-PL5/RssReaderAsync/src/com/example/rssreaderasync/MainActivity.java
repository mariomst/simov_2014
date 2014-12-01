package com.example.rssreaderasync;


import java.net.URL;





import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity 
						implements OnItemClickListener{
	static ProgressDialog pd_ring;
	TextView tv1 ;
	TextView tv2;
	ListView lv;
	private RSSFeed feed = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		lv = (ListView) findViewById(R.id.listView1);
		final EditText et = (EditText) findViewById(R.id.editText1);
		et.setText("http://feeds2.feedburner.com/carlosmmartins");
		Button b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				GetRSSDataTask task = new GetRSSDataTask();
				task.execute(et.getText().toString());
				
			}
			
		});
		
	}
	public void UpdateDisplay() {
		
		if (feed == null){
			tv1.setText("No RSS Feed Available");
			return;
		}
		tv1.setText(feed.getTitle());
		tv2.setText(feed.getPubDate());
		ListAdapter  adapter = new ListAdapter(MainActivity.this,R.layout.feed_item,feed.getAllItems());
		lv.setAdapter(adapter);
		lv.setOnItemClickListener((OnItemClickListener) MainActivity.this);
		lv.setSelection(0);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent i = new Intent(this,DetailsActivity.class); 
		
		i.putExtra("TITLE", feed.getItem(arg2).getTitle());
		i.putExtra("DESCRIPTION", feed.getItem(arg2).getDescription());
		i.putExtra("LINK", feed.getItem(arg2).getLink());
		i.putExtra("PUBDATE", feed.getItem(arg2).getPubDate());
		
		startActivity(i);
		
	}
	private class GetRSSDataTask extends AsyncTask<String, Void, RSSFeed > {
		@Override
		protected void onPreExecute() {
			pd_ring = new ProgressDialog(MainActivity.this);
			pd_ring.setMessage("Loading....");
			pd_ring.setTitle("Please Wait...");
			pd_ring.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd_ring.setCancelable(false);
			pd_ring.setIndeterminate(true);
			pd_ring.show();
		}
		@Override
		protected RSSFeed doInBackground(String... params) {
			// TODO Auto-generated method stub
			try
	    	{
				RSSFeedHandler handler = new RSSFeedHandler();
				URL url = new URL(params[0]);
				return handler.getRSSFeed(url);
	    	}
	    	catch (Exception ee)
	    	{
	    		return null;
	    	}
		} 
	    @Override
        protected void onPostExecute(RSSFeed f) {
	    	feed = f;
	    	pd_ring.dismiss();
	    	UpdateDisplay();
        }
		  
	  }

}
