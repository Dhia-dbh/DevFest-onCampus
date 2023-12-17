package com.example.devfest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class input extends AppCompatActivity {

    EditText Link;
    Button Post , Col;
    public static boolean isValidInstagramReelLink(String link) {
        String pattern = "https://www\\.instagram\\.com/reels/[\\w-]+/?";;
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(link);
        return matcher.matches();
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Link = findViewById(R.id.Link_Input);
        Post = findViewById(R.id.Post_story);
        Col = findViewById(R.id.collapse);

        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( isValidInstagramReelLink(Link.getText().toString()) )
                {
                    Toast.makeText(input.this,"Added to your story",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(input.this,"Invalid link for an Instagram reel",Toast.LENGTH_LONG).show();
                }
            }
        });

        Col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(input.this, floatingwidgetchathead.class));
                finish();
            }
        });





    }
}