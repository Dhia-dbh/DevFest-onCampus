package com.example.phonecallrecorder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.view.Gravity
import android.widget.Toast
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import java.io.File
import java.io.IOException


class CallReceiver:BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context, intent: Intent?) {
        var mediaRecorder = MediaRecorder();
        if(intent!!.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_OFFHOOK){
            showToastMessage(context!!, "Phone Call is Started...");
            startRecording(context);
        }

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

    private fun startRecording( context:Context ) {
        val mediaRecorder = MediaRecorder()

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)

        val outputFile = File(Environment.getExternalStorageDirectory(), "recorded_call.3gp")
        mediaRecorder.setOutputFile(outputFile.absolutePath)

        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        try {
            mediaRecorder.prepare()
            mediaRecorder.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun stopRecording( mediaRecorder:MediaRecorder ) {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop()
                mediaRecorder.release()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                //ymediaRecorder = null;
            }
        }
    }

}