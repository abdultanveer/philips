package com.abdul.philips

import android.os.AsyncTask
import android.util.Log

class DownloadTask : AsyncTask<String,Integer,Void> (){
    var TAG =DownloadTask::class.java.simpleName

    //doInBackground -- will execute in a background worker thread
    override fun doInBackground(vararg urlString: String?): Void? {
        Log.i(TAG,urlString[0].toString())
        return null
    }
}