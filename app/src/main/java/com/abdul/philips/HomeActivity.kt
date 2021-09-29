package com.abdul.philips

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import com.abdul.philips.recycler.RecyclerActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class HomeActivity : AppCompatActivity() {
    lateinit var tvHome: TextView
    lateinit var etContact:EditText
    lateinit var lvCountries:ListView

    var TAG = HomeActivity::class.java.simpleName
    var coutries = arrayListOf<String>("india","usa","uk","australia")//plug

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        etContact = findViewById(R.id.etContact)
        Log.i(TAG, "oncreate")
        lvCountries = findViewById(R.id.countriesLV)//socketgit
        var adapter:ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            coutries)
        lvCountries.adapter = adapter



    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")


    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "onPause")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")

    }



    fun handleClick(view: View) { //step 2
        //get the contact from edittext
        var i =0
        for(j in 1..10){
             i = i+j
        }
        var data = etContact.text.toString()
        //put the contact in a intent
        var intent:Intent = Intent()
        intent.putExtra("contactkey",data)
        var res = add(10,20)

       // throw NullPointerException()

        //send the data to the parent activity[mainactivity]
        setResult(RESULT_OK,intent) //result code -- smell/ it'll inform the parent activity whether the result is good for consumption
        //finish/close this activity
        finish()
    }

    fun add(a: Int, b: Int): Int{
        return a+b
    }

    fun serviceHandler(view: View) {
        when(view.id){
            R.id.btnStart -> {
                var intent = Intent(this,MusicService::class.java)
                intent.putExtra("mkey","play music.mp3")
                startService(intent)
            }
            R.id.btnStop -> {
                var intent = Intent(this,MusicService::class.java)
                stopService(intent)
            }
        }
    }

    fun bindHandler(view: View) { //activity gets bound with the service
        var intent = Intent(this,MusicService::class.java)
        bindService(intent,serviceConnection, BIND_AUTO_CREATE)
    }

    private lateinit var mService: MusicService //MusicService()

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, binderPipe: IBinder?) {
            val binder = binderPipe as MusicService.LocalBinder
            mService = binder.getService()
           var result = mService.add(10,34)
            Log.i(TAG,"result ="+result)


        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }

    }

    var CHANNEL_ID  = "philips channel id"
    fun showNotification(view: View) {
        val intent = Intent(this, RecyclerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("textTitle")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        var notification = builder.build()
        createNotificationChannel()
        notificationManager.notify(123,notification)
    }
    lateinit var notificationManager: NotificationManager

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "chanell name"
            val descriptionText = "philips channel desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name.toString(), importance).apply {
                description = descriptionText.toString()
            }
            // Register the channel with the system
             notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun getRegnTokenFCM(view: View) {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token: String = task.getResult().toString()

                // Log and toast
                //val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, token)
                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
            })
    }

}
