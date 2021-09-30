package com.abdul.philips.datastorage.roomdb.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Query("SELECT * from Word ORDER BY title ASC")
    List<Word> getAllNotes();

    /*@Query("SELECT * FROM Word WHERE title LIKE :word ")
    List<Word> findNote(Word word);*/

}