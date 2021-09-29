package com.abdul.philips.datastorage

import android.content.SharedPreferences
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.abdul.philips.R
import com.abdul.philips.datastorage.db.NotesDao
import com.abdul.philips.datastorage.model.TodoNote
import com.abdul.philips.datastorage.db.FeedReaderContract.FeedEntry;


class DataActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    lateinit var etTitle: EditText
    lateinit var etNotes: EditText
    lateinit var cbRemPwd: CheckBox
    lateinit var  btnPut: Button
    lateinit var btnGet: Button
    lateinit var notesDao: NotesDao
    lateinit var tvResult: TextView
    lateinit var lvDb: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        etTitle = findViewById(R.id.etTitle)
        etNotes = findViewById(R.id.etNotes)
        cbRemPwd = findViewById(R.id.cbRemPwd)
        btnPut = findViewById(R.id.btnPut)
        btnGet = findViewById(R.id.btnGet)
        tvResult = findViewById(R.id.tvResult)
        lvDb = findViewById(R.id.dbList)

        lvDb.setOnItemClickListener(this)

        notesDao = NotesDao(this)
        notesDao.openDb()
    }


    override fun onStart() {
        super.onStart()
        var colNames = arrayOf(FeedEntry.COLUMN_NAME_TITLE,FeedEntry.COLUMN_NAME_SUBTITLE)
        var toArray = intArrayOf(android.R.id.text1,android.R.id.text2)
       var dataCursor: Cursor = notesDao.getAllNotes()
        var adapter = SimpleCursorAdapter(this,
                 android.R.layout.simple_list_item_2, //row layout
                     dataCursor, //data
                        colNames,
                         toArray) // array of textviews in each row
        lvDb.adapter = adapter

    }


    override fun onPause() {
        super.onPause()
        saveData()
    }

    private fun saveData() {
        //get data from edittext
        var title = etTitle.text.toString()
        var notes = etNotes.text.toString()
        var isChecked = cbRemPwd.isChecked

        //create a file
        var sharedPreferences: SharedPreferences
        sharedPreferences = getSharedPreferences("philipsPrefs", MODE_PRIVATE)
        //open the file in edit mode
        //SharedPreferences.Editor editor = sharedPreferences.edit()
        //var editor:SharedPreferences.Editor = sharedPreferences.edit()
        var editor = sharedPreferences.edit()
        //write to the file
        editor.putString("titlekey",title)
        editor.putString("noteskey",notes)
        editor.putBoolean("cb", isChecked)
        //save the file
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        restoreData()
    }

    private fun restoreData() {
        //open the file
       var sharedPreferences = getSharedPreferences("philipsPrefs", MODE_PRIVATE)
        //read the data
        var title = sharedPreferences.getString("titlekey","def title")
        var notes = sharedPreferences.getString("noteskey","def notes")
        var isChecked = sharedPreferences.getBoolean("cb",false)
        //put the data into the fields
        etTitle.setText(title)
        etNotes.setText(notes)
        cbRemPwd.isChecked = isChecked
    }

    fun dbHandler(view: View) {
        when(view.id){
            R.id.btnPut -> { putDataDb() }
            R.id.btnGet -> { getDataDb() }
        }
    }

    private fun getDataDb() {
        var result = notesDao.readNote()
        tvResult.setText(result)
    }

    private fun putDataDb() {
        var title = etTitle.text.toString()
        var subtitle = etNotes.text.toString()

        var todoNote: TodoNote = TodoNote(title,subtitle)
       // notesDao.createNote("first title","first subtitle")
        notesDao.createNote(todoNote)
    }

    override fun onItemClick(adapterView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        var itemClicked = adapterView?.getItemAtPosition(position) as Cursor
        val titleIndex: Int = itemClicked.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE)
        val title: String = itemClicked.getString(titleIndex)


        Toast.makeText(this,title,Toast.LENGTH_SHORT).show()
    }


}