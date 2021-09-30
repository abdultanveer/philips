package com.abdul.philips.datastorage.roomdb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {

    public Word(String title) {
        this.title = title;
    }

    @PrimaryKey(autoGenerate=true)
    int wid;

    @ColumnInfo
   public String title;

    @Override
    public String toString() {
        return title;
    }
}