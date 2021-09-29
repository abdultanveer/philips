package com.abdul.philips.datastorage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import com.abdul.philips.R

class DataActivity : AppCompatActivity() {

    lateinit var etTitle: EditText
    lateinit var etNotes: EditText
    lateinit var cbRemPwd: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        etTitle = findViewById(R.id.etTitle)
        etNotes = findViewById(R.id.etNotes)
        cbRemPwd = findViewById(R.id.cbRemPwd)
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
}