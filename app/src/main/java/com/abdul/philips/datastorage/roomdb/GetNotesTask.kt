package com.abdul.philips.datastorage.roomdb

import android.R
import android.content.Context
import android.os.AsyncTask
import android.widget.ArrayAdapter
import android.widget.ListView
import com.abdul.philips.datastorage.roomdb.model.Word
import com.abdul.philips.datastorage.roomdb.model.WordDao

class GetNotesTask(val context: Context, val listView: ListView, val wordDao: WordDao) : AsyncTask<Void, Void, MutableList<Word>>() {


    override fun doInBackground(vararg p0: Void?): MutableList<Word>? {
        return wordDao.allNotes //query db in bg
    }

    override fun onPostExecute(result: MutableList<Word>) {
        super.onPostExecute(result)
        var arrayAdapter = ArrayAdapter<Word>(context,
            R.layout.simple_list_item_1,result)
        listView.adapter = arrayAdapter
    }


}