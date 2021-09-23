package com.abdul.philips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var mButton:Button //declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mButton = findViewById(R.id.button)
        //step1 -- user has to press the button
        //step 2 -- after step 1 show the toast
        mButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"button was clicked",Toast.LENGTH_SHORT).show()
        })
    }


}