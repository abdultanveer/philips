package com.abdul.philips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsFragment: Fragment() {
lateinit var tvNews: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            var screen:View = inflater.inflate(R.layout.fragment_news, container, false)
        tvNews = screen.findViewById(R.id.tvNews)
        return screen

    }

    fun setData(data: String) {
        tvNews.setText(data)
    }
}