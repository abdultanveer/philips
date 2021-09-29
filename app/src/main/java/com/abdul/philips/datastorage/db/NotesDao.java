package com.abdul.philips.datastorage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.abdul.philips.datastorage.db.FeedReaderContract.FeedEntry;
import com.abdul.philips.datastorage.model.TodoNote;

import org.jetbrains.annotations.NotNull;


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
    public String readNote(){
        Cursor cursor = database.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToLast();
        int titleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE);
        int subtitleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE);
        String title = cursor.getString(titleIndex);
        String subTitle = cursor.getString(subtitleIndex);
        return title +"\n"+ subTitle;

    }
    public void updateNote(){}
    public void deleteNote(){}

    public void createNote(@NotNull TodoNote todoNote) {
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,todoNote.getTitle());
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,todoNote.getSubtitle());

        database.insert(FeedEntry.TABLE_NAME,null,values);
    }
}
