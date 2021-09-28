package com.abdul.philips

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MusicService : Service() {

    companion object {
        var TAG = MusicService::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"service created")
        Toast.makeText(this,"service created",Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        Log.i(TAG,"i received a command --"+ intent?.getStringExtra("mkey"))
        var mediaPlayer = MediaPlayer.create(this,R.raw.music)
        mediaPlayer.start()
        return START_STICKY

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"service destroyed")

    }
}