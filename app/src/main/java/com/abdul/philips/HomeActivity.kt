package com.abdul.philips

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var tvHome: TextView
    lateinit var etContact:EditText
    lateinit var lvCountries:ListView

    var TAG = HomeActivity::class.java.simpleName
    var coutries = arrayListOf<String>("india","usa","uk","australia")//plug

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        etContact = findViewById(R.id.etContact)
        Log.i(TAG, "oncreate")
        lvCountries = findViewById(R.id.countriesLV)//socketgit
        var adapter:ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            coutries)
        lvCountries.adapter = adapter



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
        var i =0
        for(j in 1..10){
             i = i+j
        }
        var data = etContact.text.toString()
        //put the contact in a intent
        var intent:Intent = Intent()
        intent.putExtra("contactkey",data)
        var res = add(10,20)

       // throw NullPointerException()

        //send the data to the parent activity[mainactivity]
        setResult(RESULT_OK,intent) //result code -- smell/ it'll inform the parent activity whether the result is good for consumption
        //finish/close this activity
        finish()
    }

    fun add(a: Int, b: Int): Int{
        return a+b
    }

    fun serviceHandler(view: View) {
        when(view.id){
            R.id.btnStart -> {
                var intent = Intent(this,MusicService::class.java)
                intent.putExtra("mkey","play music.mp3")
                startService(intent)
            }
            R.id.btnStop -> {
                var intent = Intent(this,MusicService::class.java)
                stopService(intent)
            }
        }
    }
}