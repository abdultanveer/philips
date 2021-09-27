package com.abdul.philips.whowroteit

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import org.json.JSONObject
import org.json.JSONArray


class FetchBookTask(val mTitleText: TextView, val mAuthorText: TextView) :
    AsyncTask<String, Void, String>() {
    var TAG = FetchBookTask::class.java.simpleName

    override fun doInBackground(vararg p0: String?): String {
        Log.i(TAG, "search for the book named ---" + p0[0].toString())

        return NetworkUtils.getBookInfo(p0[0].toString())!!
    }

    override fun onPostExecute(jsonString: String?) {
        super.onPostExecute(jsonString)
        Log.i(TAG, "result is \n" + jsonString)

        val rootJsonObject = JSONObject(jsonString)
        val itemsArray = rootJsonObject.getJSONArray("items")

        for (i in 0 until itemsArray.length()) {
            val book = itemsArray.getJSONObject(i)
            val title: String? = null
            val authors: String? = null
            val volumeInfo = book.getJSONObject("volumeInfo")

            try {
                var title = volumeInfo.getString("title");
                var authors = volumeInfo.getString("authors");
            } catch (e: Exception) {
                e.printStackTrace();
            }

            if (title != null && authors != null) {
                mTitleText.setText(title);
                mAuthorText.setText(authors);
                return;
            }

            mTitleText.setText("No Results Found");

        }
    }


}


