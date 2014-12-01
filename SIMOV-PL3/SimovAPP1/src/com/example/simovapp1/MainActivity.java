package com.example.simovapp1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] users = getResources().getStringArray(R.array.users);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, users);
        ListView lv = getListView();
        lv.setAdapter(adapter);
        
        //Register for Context Menu
        registerForContextMenu(lv);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.option_menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	//Handle item selection
    	switch(item.getItemId()){
    		case R.id.settingsMenu:
    			Intent i = new Intent(this, Activity1.class);
    			startActivity(i);
    			return true;
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	int pos = info.position;
    	long id = info.id;
    	ListView lv = getListView();
    	String str = lv.getItemAtPosition(pos).toString();
    	
    	switch(item.getItemId()){
    		case R.id.open:    			
    			Intent i = new Intent(this, Activity2.class);
    			i.putExtra("EXTRA_MESSAGE", str);
    			i.putExtra("EXTRA_ID", pos);
    			startActivity(i);
    			return true;
    		default:
    			return super.onContextItemSelected(item);
    	}
    }
}
