package com.abdul.philips

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var tvHome: TextView
    lateinit var etContact:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        etContact = findViewById(R.id.etContact)
      /*  tvHome = findViewById(R.id.tvHome)

       var name = intent.getStringExtra("namekey")
        tvHome.setText(name)*/

    }

    fun handleClick(view: View) {
        //get the contact from edittext
        var data = etContact.text.toString()
        //put the contact in a intent
        var intent:Intent = Intent()
        intent.putExtra("contactkey",data)
        //send the data to the parent activity[mainactivity]
        setResult(RESULT_OK,intent) //result code -- smell/ it'll inform the parent activity whether the result is good for consumption
        //finish/close this activity
        finish()
    }
}