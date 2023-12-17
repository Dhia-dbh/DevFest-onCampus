package com.example.athis;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity1 extends AppCompatActivity {
    Button b1;
    private static final int PICK_VIDEO_REQUEST = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       b1=findViewById(R.id.btn1);
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                pickVideoFromGallery();
               Log.v("9","2");

           }

       });


    }




    private void pickVideoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedVideoUri = data.getData();
                // Now you can use the selectedVideoUri to work with the chosen video
                // For example, you can use it in an Intent to share the video on Instagram
                shareVideoToInstagram(selectedVideoUri);
            }
        }
    }

    private void shareVideoToInstagram(Uri videoUri) {
        // Create an Intent with ACTION_SEND
        Intent intent = new Intent(Intent.ACTION_SEND);

        // Set the type to video/* to indicate we're sharing a video
        intent.setType("video/*");

        // Add the caption as the text (optional)

        // Add the video URI
        intent.putExtra(Intent.EXTRA_STREAM, videoUri);

        // Set the package to Instagram to ensure it opens in Instagram (optional)
        intent.setPackage("com.instagram.android");
        startActivity(Intent.createChooser(intent, "Share to:"));
    }
}
        /*
        Intent intent = ("com.instagram.android");
        startActivity(intent);
        // Get the absolute path of the video file
        String videoPath = getExternalFilesDir(null) + "/" + videoFileName;

        // Create the URI for the video file
        Uri videoUri = Uri.fromFile(new java.io.File(videoPath));

        // Create an implicit intent with the ACTION_SEND type
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        // Set the type of the content to "video/*"
        shareIntent.setType("video/*");

        // Set the video URI as the intent data
        shareIntent.putExtra(Intent.EXTRA_STREAM, videoUri);

        // Add the caption (optional)
        shareIntent.putExtra(Intent.EXTRA_TEXT, caption);

        // Set the package name for Instagram
        shareIntent.setPackage("com.instagram.android");

        // Check if Instagram is installed
        if (isInstagramInstalled(shareIntent)) {
            // Start the activity
            startActivity(shareIntent);
        } else {
            // Instagram is not installed, handle accordingly
            // You can prompt the user to install Instagram or provide a fallback
        }
    }

    private boolean isInstagramInstalled(Intent intent) {
        return intent.resolveActivity(getPackageManager()) != null;
    }
*/


