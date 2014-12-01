package com.example.db;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityADD extends Activity {
	
	protected EditText firstName;
	protected EditText lastName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_add);
		
		/** Variables will get the data inserted on the respective edit text boxes. **/
		firstName = (EditText) findViewById(R.id.firstName);
		lastName = (EditText) findViewById(R.id.lastName);		
		
		/** Instructions for the ADD Button **/
		Button buttonADD = (Button) findViewById(R.id.addButton);
        buttonADD.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                DBAdapter adapter = new DBAdapter(getApplicationContext());
                adapter.insertPerson(firstName.getText().toString(), lastName.getText().toString());
                finish();
            }
        });
		
	}

}
