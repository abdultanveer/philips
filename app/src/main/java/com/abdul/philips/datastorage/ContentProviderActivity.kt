package com.abdul.philips.datastorage

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import com.abdul.philips.R
import com.abdul.philips.datastorage.db.FeedReaderContract

class ContentProviderActivity : AppCompatActivity() {
    lateinit var cpListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_content_provider)
        cpListView = findViewById(R.id.cpListview)

        val uriSms: Uri = Uri.parse("content://sms/inbox")
        val cursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)

        var colNames = arrayOf("body","address")
        var toArray = intArrayOf(android.R.id.text1,android.R.id.text2)
        var adapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2, //row layout
            cursor, //data
            colNames,
            toArray) // array of textviews in each row

        cpListView.adapter = adapter
    }
}