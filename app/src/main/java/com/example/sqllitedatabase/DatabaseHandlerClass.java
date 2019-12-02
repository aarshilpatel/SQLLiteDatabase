package com.example.sqllitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandlerClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";
    public static final String TABLE_NAME = "PEOPLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    private SQLiteDatabase database;


    public DatabaseHandlerClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " VARCHAR, " + COLUMN_PASSWORD + " VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
public void insertRecord(ContactModel contact){

    database = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COLUMN_USER_NAME, contact.getUserName());
    contentValues.put(COLUMN_PASSWORD, contact.getPassword());
    database.insert(TABLE_NAME , null, contentValues);
    database.close();
}



    public ArrayList<ContactModel> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME , null, null, null, null, null, null);
        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if(cursor.getCount()>0){

            for (int i=0; i<cursor.getCount(); i++)
            {
                cursor.moveToNext();
                contactModel = new ContactModel();
                contactModel.setConID(cursor.getString(0));
                contactModel.setUserName(cursor.getString(1));
                contactModel.setPassword(cursor.getString(2));
                contacts.add(contactModel);
            }
        }
        cursor.close();
        database.close();
        return contacts;

    }

public void updateRecord(ContactModel contact){
        database= this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME,contact.getUserName());
        contentValues.put(COLUMN_PASSWORD,contact.getPassword());
        database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{contact.getConID()});
        database.close();
}

        public void deleteRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, COLUMN_ID+ " = ?", new String[]{contact.getConID()});
        database.close();

}

/*
public void insertRecordAlternate(ContactModel contact){
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_USER_NAME + "," + COLUMN_PASSWORD + ") VALUES( '" + contact.getUserName() + "','" + contact.getPassword() + "')");
        database.close();
}
public ArrayList<ContactModel> getAllRecords(){
        database = this.getReadableDatabase();
    Cursor cursor = database.query(TABLE_NAME , null, )
}
*/

}
