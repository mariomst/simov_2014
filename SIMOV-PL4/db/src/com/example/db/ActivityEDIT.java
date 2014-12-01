package com.example.db;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityEDIT extends Activity {

	protected EditText firstName;
	protected EditText lastName;
	protected int _id;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_edit);
		
		/** Get information of the person thru his id **/
		DBAdapter adapter = new DBAdapter(getApplicationContext());
        _id = getIntent().getIntExtra("PERSON_ID", 0);
        Person p = adapter.getPerson(_id);
		
		/** Variables will get the data inserted on the respective edit text boxes. **/
		firstName = (EditText) findViewById(R.id.firstName);
		lastName = (EditText) findViewById(R.id.lastName);	
		
		/** Set text on the edit text boxes. **/
		firstName.setText(p.getFirstName());
		lastName.setText(p.getLastName());
		
		/** Instructions for the ADD Button **/
		Button buttonEDIT = (Button) findViewById(R.id.editButton);
		buttonEDIT.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
            	/** Create new Person object **/
            	Person p = new Person(_id, firstName.getText().toString(), lastName.getText().toString());
            	/** Update DB **/
                DBAdapter adapter = new DBAdapter(getApplicationContext());
                adapter.updatePerson(p.getId(), p.getFirstName(), p.getLastName());
                finish();
            }
        });
	}
}
