package com.abdul.philips

import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar

class DownloadTask(val progressBar: ProgressBar) : AsyncTask<String,Integer,Void> (){
    var TAG =DownloadTask::class.java.simpleName

    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.visibility = View.VISIBLE

    }

    //doInBackground -- will execute in a background worker thread
    override fun doInBackground(vararg urlString: String?): Void? {
        Log.i(TAG,urlString[0].toString())
        for(i in 1..20){
            Thread.sleep(200)
        publishProgress(Integer(i*5))

        }

        return null
    }

    override fun onProgressUpdate(vararg downloadPercentage: Integer) {
        super.onProgressUpdate(*downloadPercentage)
        progressBar.progress = downloadPercentage[0]?.toInt()!!
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        progressBar.visibility = View.INVISIBLE
    }
}