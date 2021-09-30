package com.abdul.philips.datastorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.abdul.philips.R
import com.abdul.philips.datastorage.roomdb.WordDb
import com.abdul.philips.datastorage.roomdb.model.InsertNoteAsyncTask
import com.abdul.philips.datastorage.roomdb.model.Word
import com.abdul.philips.datastorage.roomdb.model.WordDao

class RoomActivity : AppCompatActivity() {

    lateinit var wordDao: WordDao
    lateinit var wordDb: WordDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        wordDb = WordDb.getNoteDb(this)
        wordDao = wordDb.wordDao()
    }

    fun roomHandler(view: View) {
        var word: String = "abdul"
            //etTitle.text.toString()
        insertWordAsync(Word("abdul"))
    }

    private fun insertWordAsync(word: Word) {
        var insertTask = InsertNoteAsyncTask(word,wordDao)
        insertTask.execute()
    }
}