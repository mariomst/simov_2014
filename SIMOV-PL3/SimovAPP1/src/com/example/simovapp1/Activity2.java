package com.example.simovapp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
		TextView tv = (TextView)findViewById(R.id.textView2);
		Intent i = getIntent();
		int id = i.getIntExtra("EXTRA_ID", 0);
		String message = i.getStringExtra("EXTRA_MESSAGE");
		tv.setText("Nome: " + message + " - Pos: " + id);
	}
}
