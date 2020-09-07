package com.SundarImages.BhartiImageQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image1 = findViewById(R.id.logo_splash);

        image1.setVisibility(View.VISIBLE);

        splashit();

    }

    private void splashit() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                finishAffinity();
                startActivity(new Intent(Splash.this,UI.class));
            }
        },5000);
    }
    }
