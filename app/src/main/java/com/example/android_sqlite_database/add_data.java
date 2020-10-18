package com.example.android_sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        final EditText student_name = (EditText) findViewById(R.id.Add_page_student_name);
        final EditText student_marks = (EditText) findViewById(R.id.Add_page_student_marks);
        Button add = (Button) findViewById(R.id.add_page_add_button);
        add.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String show = "";
                        String s_name = student_name.getText().toString().trim();
                        String marks = student_marks.getText().toString();
                        if (isInteger(marks) && s_name != null) {
                            int s_marks = Integer.parseInt(marks);
                            boolean check = MainActivity.database.insertData(s_name, s_marks);
                            if (check)
                                show = "Student " + s_name + " is \n inserted successfully";
                            else
                                show = "Data is not inserted";
                            Toast.makeText(add_data.this, show, Toast.LENGTH_LONG).show();
                            Log.i("data", s_name + " is inserted successfully");
                            student_name.setText("");
                            student_marks.setText("");
                        } else
                            Toast.makeText(add_data.this, "you Enter wrong data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public static boolean isInteger(String str) {
        int length = str.length();
        if (str == null) {
            return false;
        } else if (length == 0) {
            return false;
        } else {
            for (int i = 0; i < length; i++) {
                char c = str.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        }
    }
}