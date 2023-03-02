package com.hexa2zero.whatssender;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Kavindu M. Wanniarachchi @ HEXA2ZERO
 **/

public class MainActivity extends AppCompatActivity {

    private EditText editText , get_other_packages;
    private Button other_package_btn;
    private String ret_val;
    private RadioGroup radioGroup;
    private LinearLayout linearLayout;
    private TextView other_package;
    private String active_package;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        radioGroup = findViewById(R.id.radio_group);
        linearLayout = findViewById(R.id.others_layout);
        get_other_packages = findViewById(R.id.other_package);
        other_package_btn = findViewById(R.id.other_btn);
        other_package = findViewById(R.id.other_text_view);
        ImageButton theme_btn = findViewById(R.id.theme_btn);

        int temp_theme = Config.theme_return(getApplicationContext());
        switch (temp_theme){
            case 1:
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                theme_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_dark_mode_24));
                break;
            case 2:
               // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                theme_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_light_mode_24));
                break;
        }

        startCheck();

        button.setOnClickListener(v -> {
            String get_number = editText.getText().toString();

            if (get_number.length() == 11){
                ret_val = "https://wa.me/"+ get_number;

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(ret_val));
                    intent.setPackage(active_package);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext() ,"Something went wrong. Please try again.." , Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getApplicationContext() ,"Enter valid number and format..." , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void radioSelected(View view) {
        int index = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(index);
        int tag = Integer.parseInt(radioButton.getTag().toString());

        switch (tag){
            case 0:
                Config.package_save(getApplicationContext() , "com.whatsapp");
                active_package = "com.whatsapp";
                linearLayout.setVisibility(View.INVISIBLE);
                other_package.setText(null);
                break;
            case 1:
                Config.package_save(getApplicationContext() , "com.whatsapp.w4b");
                active_package = "com.whatsapp.w4b";
                linearLayout.setVisibility(View.INVISIBLE);
                other_package.setText(null);
                break;
            case 2:
                linearLayout.setVisibility(View.VISIBLE);
                other_package_btn.setOnClickListener(v -> {
                   String package_temp = get_other_packages.getText().toString();
                   get_other_packages.setText(null);

                    try {
                        getPackageManager().getPackageInfo(package_temp,0);
                        active_package = package_temp;
                        Config.package_save(getApplicationContext() , package_temp);
                        other_package.setText(package_temp + " ");
                        other_package.setTextColor(getResources().getColor(R.color.gray));

                    } catch (PackageManager.NameNotFoundException e) {
                        other_package.setText(package_temp + " - not installed ");
                        other_package.setTextColor(Color.RED);
                    }

                });
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void startCheck(){
        RadioButton rdbtn1 = findViewById(R.id.whatsapp);
        RadioButton rdbtn2 = findViewById(R.id.whatsapp_business);
        RadioButton rdbtn3 = findViewById(R.id.others);
        String temp_act = Config.package_return(getApplicationContext());

        active_package = temp_act;

        try {
            getPackageManager().getPackageInfo("com.whatsapp",0);
        } catch (PackageManager.NameNotFoundException e) {
            rdbtn1.setEnabled(false);
            rdbtn1.setText("Whatsapp - not installed ");
            rdbtn1.setTextColor(Color.RED);
        }

        try {
            getPackageManager().getPackageInfo("com.whatsapp.w4b",0);
        } catch (PackageManager.NameNotFoundException e) {
            rdbtn2.setEnabled(false);
            rdbtn2.setText("Whatsapp business - not installed ");
            rdbtn2.setTextColor(Color.RED);
        }

        if (!temp_act.equals("com.whatsapp") &&  !temp_act.equals("com.whatsapp.w4b")){

            try {
                getPackageManager().getPackageInfo(temp_act,0);
                other_package.setText(temp_act + " ");
                other_package.setTextColor(getResources().getColor(R.color.gray));
            } catch (PackageManager.NameNotFoundException e) {
                other_package.setText(temp_act + " - not installed ");
                other_package.setTextColor(Color.RED);
            }
        }

        switch (temp_act){
            case "com.whatsapp":
                rdbtn1.setChecked(true);
                break;
            case "com.whatsapp.w4b":
                rdbtn2.setChecked(true);
                break;
            default:
                rdbtn3.setChecked(true);
                rdbtn3.performClick();
        }
    }

    public void changeTheme(View view){

        int temp_theme = Config.theme_return(getApplicationContext());
        switch (temp_theme){
            case 1:
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Config.theme_save(getApplicationContext(),2);
               // theme_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_light_mode_24));
                goToFake();
                break;
            case 2:
               // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Config.theme_save(getApplicationContext(),1);
               // theme_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_dark_mode_24));
                goToFake();
                break;
            default:

                switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK){
                    case Configuration.UI_MODE_NIGHT_NO:
                        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                       // theme_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_light_mode_24));
                        Config.theme_save(getApplicationContext(),2);
                        goToFake();

                        break;
                    case Configuration.UI_MODE_NIGHT_YES:
                       // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                       // theme_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_dark_mode_24));
                        Config.theme_save(getApplicationContext(),1);
                        goToFake();
                        break;
                }
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public void goToFake(){
        Config.theme_state_save(getApplicationContext(),1);

        finishAffinity();

        Intent intent = new Intent(getApplicationContext(),FakeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}