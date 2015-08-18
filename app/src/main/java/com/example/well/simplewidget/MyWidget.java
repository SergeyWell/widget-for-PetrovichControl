package com.example.well.simplewidget;

/**
 * Created by Well on 10.08.2015.
 */

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;

import com.example.widget.R;


public class MyWidget extends AppWidgetProvider {
    private static boolean colorType = true;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
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

        for (int i : appWidgetIds) {
            clickWidget(context, appWidgetManager, i);
        }
    }


    static void clickWidget(Context context, AppWidgetManager appWidgetManager,
                             int widgetID) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget);

        Intent configIntent = new Intent(context, SettingsActivity.class);
        configIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent = PendingIntent.getActivity(context, widgetID,
                configIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.imageView, pIntent);

        appWidgetManager.updateAppWidget(widgetID, remoteViews);
    }
}