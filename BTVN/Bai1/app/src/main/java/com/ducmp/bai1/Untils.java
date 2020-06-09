package com.ducmp.bai1;

import android.content.Context;
import android.content.SharedPreferences;

public class Untils {
    public static void setLanguage(String type, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MY_LANGUAGE", Context.MODE_PRIVATE).edit();
        editor.putString("myLanguage", type);
        editor.apply();
        editor.commit();
    }

    public static String getLanguage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_LANGUAGE", Context.MODE_PRIVATE);
        return sharedPreferences.getString("myLanguage", "");
    }
}
