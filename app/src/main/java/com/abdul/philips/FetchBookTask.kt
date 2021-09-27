package com.abdul.philips

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView

class FetchBookTask(mTitleText: TextView, mAuthorText: TextView): AsyncTask<String,Void,String>() {
    var TAG =FetchBookTask::class.java.simpleName

    override fun doInBackground(vararg p0: String?): String {
        Log.i(TAG,"search for the book named ---"+p0[0].toString())

        return ""
    }

}
