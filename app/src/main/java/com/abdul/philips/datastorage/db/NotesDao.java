package com.abdul.philips.datastorage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abdul.philips.datastorage.db.FeedReaderContract.FeedEntry;


//db access object - chef
public class NotesDao {
    SQLiteDatabase database;
    FeedReaderDbHelper dbHelper;

    public NotesDao(Context context) {
        dbHelper = new FeedReaderDbHelper(context);
    }

    public void openDb(){
        database = dbHelper.getWritableDatabase();
    }
    public  void closeDb(){}

    public void createNote(String title, String subtitle){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,title);
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);

        database.insert(FeedEntry.TABLE_NAME,null,values);
    }
    public void readNote(){}
    public void updateNote(){}
    public void deleteNote(){}

}
