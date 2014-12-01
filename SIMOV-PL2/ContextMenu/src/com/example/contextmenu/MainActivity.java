package com.example.contextmenu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends ListActivity {

	private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] students = getResources().getStringArray(R.array.students);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);
        lv = getListView();
        lv.setAdapter(adapter);        
        
        registerForContextMenu(lv);
    }
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int pos = info.position;
		long id = info.id;
		ListView lv = getListView();
		String str = lv.getItemAtPosition(pos).toString();
		
		switch(item.getItemId()){
			case R.id.edit:
				Toast.makeText(getApplicationContext(), "Edit: " + id + ":" + str, Toast.LENGTH_LONG).show();
				return true;
			case R.id.delete:
				Toast.makeText(getApplicationContext(), "Delete: " + id + ":" + str, Toast.LENGTH_LONG).show();
				return true;
			case R.id.share:
				Toast.makeText(getApplicationContext(), "Share: " + id + ":" + str, Toast.LENGTH_LONG).show();
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}
	
	
    
    
}
