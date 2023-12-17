package com.example.phonecallrecorder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import android.view.Gravity
import android.widget.Toast

class CallReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_OFFHOOK)
            showToastMessage(context!!, "Phone Call is Started...");
        else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_IDLE){
                showToastMessage(context!!, "Phone Call is Ended...");
        }
        else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_RINGING){
            showToastMessage(context!!, "Phone Call is Incming...");
        }


    }
    fun showToastMessage(c:Context?, message: String){
        val toast = Toast.makeText(c, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}