package com.hexa2zero.whatssender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

public class FakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int temp_theme = Config.theme_return(getApplicationContext());
        switch (temp_theme){
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake);

        if (Config.theme_state_return(getApplicationContext()) == 1){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            overridePendingTransition(0, 0);

            Config.theme_state_save(getApplicationContext(),0);
            startActivity(intent);
        }else {
            Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            overridePendingTransition(0, 0);
            startActivity(intent);
        }

    }
}