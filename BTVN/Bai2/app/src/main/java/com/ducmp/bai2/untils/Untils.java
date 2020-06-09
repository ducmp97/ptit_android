package com.ducmp.bai2.untils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import com.ducmp.bai2.activity.LoginActivity;

public class Untils {
    public static void setUser(String type, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MY_USER", Context.MODE_PRIVATE).edit();
        editor.putString("myUser", type);
        editor.apply();
        editor.commit();
    }

    public static String getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_USER", Context.MODE_PRIVATE);
        return sharedPreferences.getString("myUser", "");
    }

    public static void deleteUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_USER", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("myUser").commit();
    }

    public static void showDialogLogout(final Context context, String title, String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Untils.deleteUser(context);
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                });
        builder1.setPositiveButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
