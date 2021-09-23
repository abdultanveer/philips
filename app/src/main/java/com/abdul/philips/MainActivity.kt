package com.abdul.philips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var tvRes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //inflating -- R.layout

        etName = findViewById(R.id.etName)  //what is findviewbyid
        tvRes = findViewById(R.id.textView) //what is R.id
    }

    fun clickHandler(view: View) {
        //get the data from edittext
        //String name = etName.gettext().toString();
        var name:String = etName.text.toString()
        //put that data into the textview
        //tvRes.setText(name);
        tvRes.text = name
    }


}