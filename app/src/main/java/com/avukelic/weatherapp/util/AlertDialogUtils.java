package com.avukelic.weatherapp.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class AlertDialogUtils {

    public interface OnAlertDialogButtonClickListener {
        void onAlertDialogConfirm(String location, int itemId);
    }


    public static void askForDeleteAlertDialog(Context context, final String location, final OnAlertDialogButtonClickListener listener, final int itemId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete location");
        builder.setMessage("Do you want to delete location " + location);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onAlertDialogConfirm(location, itemId);
                }
                dialog.dismiss();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}