package com.hexa2zero.whatssender;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Kavindu M. Wanniarachchi @ HEXA2ZERO
 **/

public class Config extends AppCompatActivity {

    private static final String MY_PREF = "com.hexa2zero.whatssender";
    private static final String package_name = "package";
    private static final String theme_id = "theme";
    private static final String state_id = "state";

    public static void package_save(Context context , String pack_name){
        SharedPreferences spref = context.getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.putString(package_name,pack_name);
        editor.apply();
    }

    public static String package_return(Context context){
        SharedPreferences spref = context.getSharedPreferences(MY_PREF ,Context.MODE_PRIVATE);
        return spref.getString(package_name , "com.whatsapp");
    }

    public static void theme_save(Context context , int theme){
        SharedPreferences spref = context.getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.putInt(theme_id,theme);
        editor.apply();
    }

    public static int theme_return(Context context){
        SharedPreferences spref = context.getSharedPreferences(MY_PREF ,Context.MODE_PRIVATE);
        return spref.getInt(theme_id , 0); // 0 = default , 1 = day , 2 = night
    }

    public static void theme_state_save(Context context , int state){
        SharedPreferences spref = context.getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.putInt(state_id,state);
        editor.apply();
    }

    public static int theme_state_return(Context context){
        SharedPreferences spref = context.getSharedPreferences(MY_PREF ,Context.MODE_PRIVATE);
        return spref.getInt(state_id , 0); // 0 = theme not changed , 1 = theme changed
    }

}
