package com.SundarImages.BhartiImageQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class ExitPage extends AppCompatActivity {

    TextView link1,link2,link3,link4,link5,link6,link7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit_page);

        link1 = findViewById(R.id.link1);
        link2 = findViewById(R.id.link2);
        link3 = findViewById(R.id.link3);
        link4 = findViewById(R.id.link4);
        link5 = findViewById(R.id.link5);
        link6 = findViewById(R.id.link6);
        link7 = findViewById(R.id.link7);


        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openurl(link1.getText().toString());
            }
        });
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openurl(link2.getText().toString());
            }
        });
        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openurl(link3.getText().toString());
            }
        });
        link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openurl(link4.getText().toString());
            }
        });
        link5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openurl(link5.getText().toString());
            }
        });
        link6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openurl(link6.getText().toString());
            }
        });
        link7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openurl(link7.getText().toString());
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                finishAffinity();
            }
        },6000);
    }

    private void openurl(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
    }


    public void onBackPressed() {
        finish();
        finishAffinity();
    }
}