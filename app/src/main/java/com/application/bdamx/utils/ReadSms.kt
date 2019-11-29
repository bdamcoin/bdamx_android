package com.application.bit900.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage


class ReadSms : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val bundle = intent.extras
        try {

            if (bundle != null) {

                val pdusObj = bundle.get("pdus") as Array<Any>
                for (i in pdusObj.indices) {

                    val currentMessage = SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                    val phoneNumber = currentMessage.displayOriginatingAddress
                    var message = currentMessage.displayMessageBody

                    /*message = message.substring(0, message.length - 1)
                    Log.i("SmsReceiver", "senderNum: $phoneNumber; message: $message")

                    val myIntent = Intent("otp")
                    myIntent.putExtra("message", message)
                    LocalBroadcastManager.getInstance(context).sendBroadcast(myIntent)
*/
                    println("num===$phoneNumber=$phoneNumber=$message")

                    try {

                        if (phoneNumber !=null&&!phoneNumber.equals("")&&!phoneNumber.equals("null")) {
                            println("1111==="+message)

                            val i = Intent("mycustombroadcast")
                            i.putExtra("message", message)
                            context.sendBroadcast(i)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}