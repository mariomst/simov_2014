package com.example.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NewActivity extends Activity {
	
	private static final String TAG = "NewActivity:";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
		Log.i(getString(R.string.app_name), TAG + "onCreate");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(getString(R.string.app_name), TAG + "onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(getString(R.string.app_name), TAG + "onPause");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(getString(R.string.app_name), TAG + "onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(getString(R.string.app_name), TAG + "onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(getString(R.string.app_name), TAG + "onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(getString(R.string.app_name), TAG + "onStop");
	}

}
