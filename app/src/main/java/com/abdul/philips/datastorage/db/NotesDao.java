package com.abdul.philips.datastorage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.abdul.philips.datastorage.db.FeedReaderContract.FeedEntry;
import com.abdul.philips.datastorage.model.TodoNote;

import org.jetbrains.annotations.NotNull;


//db access object - chef\

/**
 * this class handles db operations
 */
public class NotesDao {
    SQLiteDatabase database;
    FeedReaderDbHelper dbHelper;

    public NotesDao(Context context) {
        dbHelper = new FeedReaderDbHelper(context);
    }

    /**
     * this method opens the db in writeable mode
     */
    public void openDb(){
        database = dbHelper.getWritableDatabase();
    }
    public  void closeDb(){
        database.close();
    }

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

    /**
     * This will create a row in the table
     * @param todoNote the note to be inserted in the db table
     */
    public void createNote(@NotNull TodoNote todoNote) {
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,todoNote.getTitle());
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,todoNote.getSubtitle());

        database.insert(FeedEntry.TABLE_NAME,null,values);
    }

    @NotNull
    public Cursor getAllNotes() {
        return     database.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
    }
}
