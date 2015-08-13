package com.example.well.simplewidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.view.View;

import com.example.widget.R;

/**
 * Created by Well on 13.08.2015.
 */
public class SettingsActivity extends Activity {
    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);
    }

    public void onClick(View view){
        MyWidget.clickWidget(this, AppWidgetManager.getInstance(this), widgetID);
        finish();
    }
}
