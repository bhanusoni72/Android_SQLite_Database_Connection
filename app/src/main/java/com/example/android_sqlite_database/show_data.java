package com.example.android_sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class show_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        final TextView show_text=(TextView) findViewById(R.id.show_page_text);
        String get_data="";
        Cursor res=MainActivity.database.getAllData();
        if(res.getCount()==0) {
            get_data="Student table is empty";
        }
        else {
            while(res.moveToNext()) {
                get_data=get_data+"\n\tId : "+res.getString(0)+"\n\tName : "+res.getString(1)+"\n\tMarks : "+res.getString(2)+"\n";
            }
        }
        show_text.setText(get_data);
    }
}