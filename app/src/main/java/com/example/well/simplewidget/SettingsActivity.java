package com.example.well.simplewidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.widget.R;

/**
 * Created by Well on 13.08.2015..
 */
public class SettingsActivity extends Activity implements OnClickListener {
    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Button btnOk, btnCancel, btnTest;
    EditText etEmail, etKey, etIdIbeacon, etTimeDelay;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        etEmail = (EditText) findViewById(R.id.editEmail);
        etKey = (EditText) findViewById(R.id.editKey);
        etIdIbeacon = (EditText) findViewById(R.id.editID);
        etTimeDelay = (EditText) findViewById(R.id.editTimeDelay);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadText();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view){
        MyWidget.clickWidget(this, AppWidgetManager.getInstance(this), widgetID);

        switch (view.getId()) {
                case R.id.btnOk:
                    saveText();
                    break;
                case R.id.btnCancel:
                    onBackPressed();
                    break;
                case R.id.btnTest:
                    loadText();
                    break;
                default:
                    break;
            }

        }

    public void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString("email", etEmail.getText().toString());
        ed.putString("key", etKey.getText().toString());
        ed.putString("id", etIdIbeacon.getText().toString());
        ed.putString("timeDelay", etTimeDelay.getText().toString());
        ed.commit();

//        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    public void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        etEmail.setText(sPref.getString("email", ""));
        etKey.setText(sPref.getString("key", ""));
        etIdIbeacon.setText(sPref.getString("id", ""));
        etTimeDelay.setText(sPref.getString("timeDelay", ""));
    }
}
