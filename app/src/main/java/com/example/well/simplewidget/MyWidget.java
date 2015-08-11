package com.example.well.simplewidget;

/**
 * Created by Well on 10.08.2015.
 */

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Color;
import android.widget.RemoteViews;
import com.example.widget.R;


public class MyWidget extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {


            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);

            long em = (long) ((System.currentTimeMillis()) / 1000 / 60);
            long res = (em % 60);
            String min = "" + res;

            remoteViews.setTextViewText(R.id.textView2, min);

            if (res % 2 == 0) {
                remoteViews.setTextColor(R.id.textView2, Color.parseColor("#FF0F08FF"));
            } else {
                remoteViews.setTextColor(R.id.textView2, Color.parseColor("#ffff090c"));
            }


            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
            }

}