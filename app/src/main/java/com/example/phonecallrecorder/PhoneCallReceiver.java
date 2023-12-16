package com.example.phonecallrecorder;

import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.media.MediaRecorder;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import android.widget.Toast;



public class PhoneCallReceiver extends BroadcastReceiver {
    private MediaRecorder mediaRecorder;
    private File outputFile;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Recording ended", Toast.LENGTH_LONG).show();
        String action = intent.getAction();

        if (action != null && action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if (phoneState != null && phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                // Display a toast message when recording starts
                Toast.makeText(context, "Recording started", Toast.LENGTH_LONG).show();
                // Phone is ringing (incoming call)
                startRecording();
            } else if (phoneState != null && phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                // Display a toast message when recording ends
                Toast.makeText(context, "Recording ended", Toast.LENGTH_LONG).show();

                // Phone call has ended
                // Stop recording here if needed
                stopRecording();
            }
        }
    }
    private void startRecording() {
        mediaRecorder = new MediaRecorder();

        // Set audio source and output format
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        // Set output file
        outputFile = new File(Environment.getExternalStorageDirectory(), "recorded_call.3gp");
        mediaRecorder.setOutputFile(outputFile.getAbsolutePath());

        // Set audio encoder
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void stopRecording() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mediaRecorder = null;
            }
        }
    }

}

