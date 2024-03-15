package com.example.femto_ui.authentication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class OtpBroadcastReceiver :BroadcastReceiver() {
    var otpBrodcastReceiverListener:OtpBrodcastReceiverListener? = null
    override fun onReceive(context: Context?, intent: Intent?) {

        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action){
            val extras = intent.extras
            val otpRetrieveerStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when(otpRetrieveerStatus.statusCode){
                CommonStatusCodes.SUCCESS ->{
                    val messageIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                    otpBrodcastReceiverListener?.onSuccess(messageIntent!!)
                }
                CommonStatusCodes.TIMEOUT ->{
                    otpBrodcastReceiverListener?.onFailure()
                }
            }
        }
    }


    interface OtpBrodcastReceiverListener{

        fun onSuccess(intent: Intent)
        fun onFailure()
    }
}