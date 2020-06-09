package com.ducmp.bai2;

import android.content.Context;
import android.content.SharedPreferences;

public class Untils {

    public static void setSize(String size, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("size", Context.MODE_PRIVATE).edit();
        editor.putString("mySize", size);
        editor.apply();
        editor.commit();
    }

    public static String getSize(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("size", Context.MODE_PRIVATE);
        return sharedPreferences.getString("mySize", "");
    }

    public static void refreshSize(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("size", Context.MODE_PRIVATE).edit();
        editor.remove("mySize");
        editor.apply();
        editor.commit();
    }
}
