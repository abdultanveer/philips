package com.abdul.philips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FragActivity : AppCompatActivity(), HeadlinesFragment.HeadlinesClickListenerSB {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag)
    }

    override fun onHeadlineClickSw(data: String) {
        var newsFragment:NewsFragment = supportFragmentManager.findFragmentById(R.id.newsFrag) as NewsFragment
        newsFragment.setData(data)
    }
}