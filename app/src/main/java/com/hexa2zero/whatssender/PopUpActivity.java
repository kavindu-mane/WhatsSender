package com.hexa2zero.whatssender;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PopUpActivity extends AppCompatActivity {

    Dialog dialog;
    EditText editText;
    Button button;
    private String ret_val;
    private String active_package;

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
        setContentView(R.layout.activity_pop_up);

        /*dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_pop_up);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();*/

        button = findViewById(R.id.button_widget);
        editText = findViewById(R.id.editText_widget);
        active_package = Config.package_return(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String get_number = editText.getText().toString();

                if (get_number.length() == 11){
                    ret_val = "https://wa.me/"+ get_number;

                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(ret_val));
                        intent.setPackage(active_package);
                        startActivity(intent);
                        finishAffinity();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext() ,"Something went wrong. Please try again.." , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext() ,"Enter valid number and format..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}