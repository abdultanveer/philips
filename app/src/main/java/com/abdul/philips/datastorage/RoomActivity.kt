package com.abdul.philips.datastorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.abdul.philips.R
import com.abdul.philips.datastorage.roomdb.WordDb
import com.abdul.philips.datastorage.roomdb.model.InsertNoteAsyncTask
import com.abdul.philips.datastorage.roomdb.model.Word
import com.abdul.philips.datastorage.roomdb.model.WordDao

class RoomActivity : AppCompatActivity() {

    lateinit var wordDao: WordDao
    lateinit var wordDb: WordDb
    lateinit var etWord: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        wordDb = WordDb.getNoteDb(this)
        wordDao = wordDb.wordDao()
        etWord = findViewById(R.id.etWord)
    }

    fun roomHandler(view: View) {
        var word: String = etWord.text.toString()
        insertWordAsync(Word(word))
    }

    private fun insertWordAsync(word: Word) {
        var insertTask = InsertNoteAsyncTask(word,wordDao)
        insertTask.execute()
    }
}