package com.abdul.philips

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var tvHome: TextView
    lateinit var etContact:EditText

    var TAG = HomeActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        etContact = findViewById(R.id.etContact)
        Log.i(TAG, "oncreate")
      /*  tvHome = findViewById(R.id.tvHome)

       var name = intent.getStringExtra("namekey")
        tvHome.setText(name)*/

    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")


    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "onPause")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")

    }



    fun handleClick(view: View) { //step 2
        //get the contact from edittext
        var data = etContact.text.toString()
        //put the contact in a intent
        var res = add(10,20)
        var intent:Intent = Intent()
        intent.putExtra("contactkey",data)
        throw NullPointerException()

        //send the data to the parent activity[mainactivity]
        setResult(RESULT_OK,intent) //result code -- smell/ it'll inform the parent activity whether the result is good for consumption
        //finish/close this activity
        finish()
    }

    fun add(a: Int, b: Int): Int{
        return a+b
    }
}