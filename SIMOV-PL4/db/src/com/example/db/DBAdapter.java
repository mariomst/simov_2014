package com.example.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*!
 * 
 */
public class DBAdapter {
	private DBHelper dbHelper;
	
	private static final String TABLE = "People10";
	private static final String _ID = "_id";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	
	private static final String SELECT_PEOPLE = "SELECT * FROM " + TABLE;
	
	
	 public DBAdapter(Context context) {
	        dbHelper = new DBHelper(context, TABLE, _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
	                + FIRST_NAME + " TEXT," + LAST_NAME + " TEXT");
	 }
	 public boolean insertPerson(String personId, String personFirstName, String personLastName) {
        try {
            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
            ContentValues initialValues = new ContentValues();
          //  initialValues.put(_ID, personId);
            initialValues.put(FIRST_NAME, personFirstName);
            initialValues.put(LAST_NAME, personLastName);
            sqlite.insert(TABLE, null, initialValues);

        } catch (SQLException sqlerror) {
            Log.v("Insert into table error", sqlerror.getMessage());
            return false;
        }
        return true;
	 }
	 public ArrayList<Person> getPeople() {
	        ArrayList<Person> people = new ArrayList<Person>();
	        SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
	        Cursor crsr = sqliteDB.rawQuery(SELECT_PEOPLE, null);
	        crsr.moveToFirst();
	        for (int i = 0; i < crsr.getCount(); i++){
	        	people.add(new Person(crsr.getInt(0), crsr.getString(1),crsr.getString(2)));
	            crsr.moveToNext();
	        }
	        return people;
	    }
	 public Person getPerson(int _id) {
	        SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
	        String s = "SELECT * FROM " + TABLE + " WHERE " + _ID+ "=" + _id;
	        Cursor crsr = sqliteDB.rawQuery(s, null);
	        crsr.moveToFirst();
	        Person person = new Person(crsr.getInt(0), crsr.getString(1),crsr.getString(2));
	        return person;
	    }

	 //Insert New Person
	 public boolean insertPerson(String firstName, String lastName){
		 try{
			 SQLiteDatabase sql = dbHelper.getWritableDatabase();
			 ContentValues values = new ContentValues();
			 values.put(FIRST_NAME, firstName);
			 values.put(LAST_NAME, lastName);
			 sql.insert(TABLE, null, values);
		 } catch (SQLException sqlerror) {
			 Log.v("Insert into table error", sqlerror.getMessage());
			 return false;
		 }
		 return true;
	 }
	 //Update Person
	 public boolean updatePerson(int id, String firstName, String lastName){
		 try{
			 SQLiteDatabase sql = dbHelper.getWritableDatabase();
			 ContentValues values = new ContentValues();
			 values.put(_ID, id);
			 values.put(FIRST_NAME, firstName);
			 values.put(LAST_NAME, lastName);
			 sql.update(TABLE, values, _ID + "=" + id, null);
		 } catch (SQLException sqlerror) {
			 return false;
		 }
		 return true;
	 }
	 //Delete Person
	 public boolean deletePerson(int id){
		 try{
			 SQLiteDatabase sql = dbHelper.getWritableDatabase();
			 sql.delete(TABLE, _ID + "=" + id, null);
		 } catch (SQLException sqlerror){
			 return false;
		 }
		 return true;
	 }
	 
}
