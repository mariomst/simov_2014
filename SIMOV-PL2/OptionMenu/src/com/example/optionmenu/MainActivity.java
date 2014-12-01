package com.example.optionmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.favMenu:
				Toast.makeText(getApplicationContext(), "Favourite", Toast.LENGTH_LONG).show();
				return true;
			case R.id.listMenu:
				Toast.makeText(getApplicationContext(), "List", Toast.LENGTH_LONG).show();
				return true;
			case R.id.mapMenu:
				Toast.makeText(getApplicationContext(), "Map", Toast.LENGTH_LONG).show();
				return true;
			case R.id.settingsMenu:
				Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

}
