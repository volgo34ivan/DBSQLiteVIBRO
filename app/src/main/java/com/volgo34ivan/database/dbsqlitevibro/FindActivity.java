package com.volgo34ivan.database.dbsqlitevibro;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class FindActivity extends Activity {

    final String LOG_TAG = "myLogs";
    MainActivity.DBHelper dbHelper;
    private List<View> editTextList = new ArrayList<View>();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_layout);
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        for (int N = 0; N < 10; N++){
            TextView myTextView = new TextView(this);
            myTextView.setText("my first added TextView" + N);
            LinearLayout mylayout = (LinearLayout) findViewById(R.id.myLayout);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            editTextList.add(N, myTextView);
            mylayout.addView(editTextList.get(N),lParams);
        }

    }
}
