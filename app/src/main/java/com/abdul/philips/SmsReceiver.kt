package com.abdul.philips

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {
    private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
    private val TAG = "SMSBroadcastReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        val (smsContact, smsBody) = getSMSContent(intent)
        Log.i(TAG,smsContact +"\t"+ smsBody)

    }

    fun getSMSContent(intent: Intent): Pair<String, String> {
        val data = intent.extras
        val pdusObj = data!!.get("pdus") as Array<*>
        for (i in pdusObj.indices) {
            val currentMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]
            else SmsMessage.createFromPdu(pdusObj[0] as ByteArray)

            // TODO: Here we get the all information about SMS.
            //Apply your conditions and logic here to filter your SMS from various SMS
            // and extract your content from message body

            val phoneNumber = currentMessage.displayOriginatingAddress
            Log.e("receiver", phoneNumber)

            val senderNum = phoneNumber
            Log.e("receiver", senderNum)

            val smsContent = currentMessage.getDisplayMessageBody()
            Log.d("receiver","Message: "+smsContent) // Always max 67 characters!

            return Pair(senderNum, smsContent)
        }
        return Pair("","")
    }
}