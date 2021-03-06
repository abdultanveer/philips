package com.abdul.philips

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var tvRes: TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //inflating -- R.layout

        etName = findViewById(R.id.etName)  //what is findviewbyid
        tvRes = findViewById(R.id.textView) //what is R.id
        progressBar = findViewById(R.id.progressBar)
    }

    fun clickHandler(view: View) {
        when(view.id){
            R.id.btnDownload -> {
                var downloadTask = DownloadTask(progressBar)
                downloadTask.execute("http://urlForimage.com")
            }
            R.id.button -> {
                //startHome()
                setTextView()
            }
            R.id.btnDial -> {
                var dIntent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:9876543")) //implicit intent
                startActivity(dIntent)
            }
            R.id.btnAlarm -> {
                createAlarm("philips",16,54)
            }

        }
       // setTextView()
    }

    private fun startHome() {
        //var data = etName.text.toString()
        var hIntent: Intent = Intent(this, HomeActivity::class.java) //explicit intent
       // hIntent.putExtra("namekey", data)
        startActivityForResult(hIntent,123) //step1   what is request code--123
        //for eg on whatsapp -- camera,contact, location the return point is onActivityREsult
    }

    private fun setTextView() {
        //get the data from edittext
        //String name = etName.gettext().toString();
        var name: String = etName.text.toString()
        //put that data into the textview
        //tvRes.setText(name);
        tvRes.text = name
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) { //step 3
        super.onActivityResult(requestCode, resultCode, intent)
        //request code helps you determine from which childactivity[camera/contact] is returning the data
        //request code = 123, result code =RESULT_OK
        if(resultCode ==  RESULT_OK && requestCode == 123) {
            var data = intent?.getStringExtra("contactkey")
            tvRes.text = data
        }
    }




}