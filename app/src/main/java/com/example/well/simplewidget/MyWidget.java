package com.example.well.simplewidget;

/**
 * Created by Well on 10.08.2015.
 */

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.Color;
import android.widget.RemoteViews;

import com.example.widget.R;


public class MyWidget extends AppWidgetProvider {
    private static boolean colorType = true;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
        BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();

        if (!bt.isEnabled()){
            remoteViews.setImageViewResource(R.id.imageView, R.drawable.ic_error_black_48dp);
            remoteViews.setTextViewText(R.id.textView2, "NO");
            remoteViews.setTextColor(R.id.textView2, Color.GRAY);

        } else {
            remoteViews.setImageViewResource(R.id.imageView, R.drawable.ic_face_black_48dp);

            long em = (long) ((System.currentTimeMillis()) / 1000 / 60);
            String min = "" + (em % 60);

            remoteViews.setTextViewText(R.id.textView2, min);

            if (colorType) {
                colorType = false;
                remoteViews.setTextColor(R.id.textView2, Color.RED);
            } else {
                colorType = true;
                remoteViews.setTextColor(R.id.textView2, Color.BLUE);
            }
        }



        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

}