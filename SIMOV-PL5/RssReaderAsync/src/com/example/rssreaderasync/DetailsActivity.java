package com.example.rssreaderasync;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_details);
		 String item = null;
		 Intent i = getIntent();
		 if (i != null) {
			 item = i.getStringExtra("TITLE") + "\n\n" + 
					i.getStringExtra("PUBDATE") + "\n\n" + 
	        		i.getStringExtra("DESCRIPTION").replace('\n',' ') + "\n\nMore information:\n" + 
	        		i.getStringExtra("LINK");
	        }else{
	        	item = "Information Not Found.";
	        }
	        TextView tv= (TextView) findViewById(R.id.textView3);
	        tv.setText(item);
	}
}
