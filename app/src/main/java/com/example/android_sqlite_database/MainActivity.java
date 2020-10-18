package com.example.android_sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String Tag="com.example.android_sqlite_database";
    public static SqliteDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new SqliteDataBase(this);
        Button add=(Button) findViewById(R.id.main_page_add_data);
        Button show=(Button) findViewById(R.id.main_page_show_data);
        Button delete=(Button) findViewById(R.id.main_page_delete_data);
        Button clear=(Button) findViewById(R.id.main_page_clear_data);

        add.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent add_intent=new Intent(MainActivity.this,add_data.class);
                        Log.i(Tag,"adding data to database");
                        startActivity(add_intent);
                    }
                }
        );

        show.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent show_intent=new Intent(MainActivity.this,show_data.class);
                        Log.i(Tag,"show data from database");
                        startActivity(show_intent);
                    }
                }
        );

        delete.setOnClickListener(
            new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent delete_intent=new Intent(MainActivity.this,delete_data.class);
                    Log.i(Tag,"delete data from database");
                    startActivity(delete_intent);
                }
            }
        );

        clear.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        database.dropTable();
                        Toast.makeText(MainActivity.this,"All data is deleted \nfrom data base",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        
    }

}