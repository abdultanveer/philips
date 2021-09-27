package com.abdul.philips.whowroteit

import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkUtils2 {

    companion object {
        private val LOG_TAG = NetworkUtils2::class.java.simpleName
        private val BOOK_BASE_URL =
            "https://www.googleapis.com/books/v1/volumes?" // Base URI for the    Books API

        private val QUERY_PARAM = "q" // Parameter for the search string ie bookname

        private val MAX_RESULTS = "maxResults" // Parameter that limits search results

        private val PRINT_TYPE = "printType" // Parameter to filter by print type

        fun getBookInfo(bookName: String): String? {
            var urlConnection: HttpURLConnection? = null
            var reader: BufferedReader? = null
            var bookJSONString: String? = null

            try {
                val builtURI: Uri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, bookName)
                    .appendQueryParameter(MAX_RESULTS, "3")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build()
                val requestURL = URL(builtURI.toString())
                Log.d(LOG_TAG, requestURL.toString());


                urlConnection = requestURL.openConnection() as HttpURLConnection
                urlConnection.setRequestMethod("GET") //GET -- read db, POST -- write db, PUT -- update db, DELETE

                urlConnection.connect()


                val inputStream: InputStream? = urlConnection.inputStream
                val buffer = StringBuffer()
                if (inputStream == null) {
                    // Nothing to do.
                    return null
                }

                reader = BufferedReader(InputStreamReader(inputStream))
                var line: String = ""
                while (reader.readLine().also { line = it } != null) {
                    buffer.append(
                        """
                $line    """.trimIndent()
                    )
                }

                if (buffer.length == 0) { // Stream was empty. No point in parsing.
                    return null
                }
                bookJSONString = buffer.toString()
                Log.i(LOG_TAG, requestURL.toString())
            } catch (e: Exception) {
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (ioe: IOException) {
                        ioe.printStackTrace();
                    }
                }

            }
            Log.d(LOG_TAG, bookJSONString.toString());
            return bookJSONString;

        }
    }
}