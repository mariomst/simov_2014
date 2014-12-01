package com.example.db;

import java.util.ArrayList;



import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity {

	protected ListView peopleList;
	private ListAdapter listAdapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*!
         * Create database if it is not created yet
         */
        DBAdapter adapter = new DBAdapter(getApplicationContext());
        /*!
         * Fill the listview
         */
        peopleList = getListView();
        ArrayList<Person> people = adapter.getPeople(); 
        listAdapter = new ListAdapter(getApplicationContext(), R.layout.person_item,people);
        peopleList.setAdapter(listAdapter);
        /*!
         * Register a callback to be invoked when an item in this AdapterView 
         * has been clicked.
         */
        peopleList.setOnItemClickListener(new OnItemClickListener(){
        	/*!
        	 * Callback method to be invoked when an item in this 
        	 * AdapterView has been clicked.
        	 * Implementers can call getItemAtPosition(position) 
        	 * if they need to access the data associated with 
        	 * the selected item.
        	 * Parameters 
        	 * 	arg0 	The AdapterView where the click happened.
        	 *  arg1 	The view within the AdapterView that was clicked (this will be a view provided by the adapter)
        	 *  arg2 	The position of the view in the adapter.
        	 *  arg3 	The row id of the item that was clicked. 
        	 */
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, PersonDetails.class);
		        Person p = (Person)listAdapter.getItem(arg2);
		        intent.putExtra("PERSON_ID", p.getId());
		        startActivity(intent);
    		}
		});
        
        //Register for Context Menu
        registerForContextMenu(peopleList);
		
    }

    /** On activity resume **/
    protected void onResume(){
    	super.onResume();
    	fillData();
    }
    
    /** Create Options Menu **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inf = getMenuInflater();
    	inf.inflate(R.menu.option_menu, menu);
    	return true;
    }
    
    /** Call activity ADD when the item on options menu is selected **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	//Handle item selection
    	switch(item.getItemId()){
    		case R.id.adicionar:
    			Intent i = new Intent(this, ActivityADD.class);
    			startActivity(i);
    			return true;
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }

    /** Create Context Menu **/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater inf = getMenuInflater();
    	inf.inflate(R.menu.context_menu, menu);
    }

    /** Call specific activity when an item on context menu is selected **/
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	Person p = (Person)listAdapter.getItem(info.position);
    	
    	switch(item.getItemId()){
    		case R.id.edit:    			
    			Intent i = new Intent(this, ActivityEDIT.class); 
    			i.putExtra("PERSON_ID", p.getId());   			 
    			startActivity(i);
    			return true;
    		case R.id.delete:    	
    			DBAdapter adapter = new DBAdapter(getApplicationContext());
    			adapter.deletePerson(p.getId());
    			fillData();
    			return true;
    		default:
    			return super.onContextItemSelected(item);
    	}    	
    }
    
    /** Force Update List **/
    private void fillData(){
    	DBAdapter adapter = new DBAdapter(getApplicationContext());
    	ArrayList<Person> people = adapter.getPeople();
    	listAdapter = new ListAdapter(getApplicationContext(), R.layout.person_item, people);
    	peopleList.setAdapter(listAdapter);
    }
}
