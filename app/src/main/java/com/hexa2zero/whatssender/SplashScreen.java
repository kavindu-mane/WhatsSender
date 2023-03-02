package com.hexa2zero.whatssender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Kavindu M. Wanniarachchi @ HEXA2ZERO
 **/

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> startActivity(new Intent(getApplicationContext() , MainActivity.class)) ,2000);
    }
}