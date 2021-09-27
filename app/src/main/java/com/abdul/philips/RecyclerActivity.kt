package com.abdul.philips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {
    var coutries = arrayListOf<String>("india","usa","uk","australia")//plug
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler) //layout inflater
        recyclerView = findViewById(R.id.countriesRV)  //taking the handle of recyclerview

    }
}