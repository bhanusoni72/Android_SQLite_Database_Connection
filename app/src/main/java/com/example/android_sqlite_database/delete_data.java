package com.example.android_sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class delete_data extends AppCompatActivity {

    public static int i=0;
    public static String[]arr;
    public static String[]size;
    public static View[]v;
    public static String[]name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);
        final Cursor res=MainActivity.database.getAllData();
        final ListView show=findViewById(R.id.delete_page_list);
        Button delete=findViewById(R.id.delete_page_delete_button);
        if(res.getCount()>0)
        {
            arr=new String[res.getCount()];
            i=0;
            while(res.moveToNext()) {
                arr[i++]=res.getString(1)+"\t:\t"+res.getString(2);
            }
            ListAdapter out=new ArrayAdapter<String>(delete_data.this,android.R.layout.simple_list_item_1,arr);
            show.setAdapter(out);
            i=0;
            name=new String[arr.length];
            size = new String[arr.length];
            v=new View[arr.length];
            show.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selected=String.valueOf(parent.getItemAtPosition(position));
                            View ch= check(i, size,v,name,selected);
                            if(ch==null)
                            {
                                size[i]=String.valueOf(parent.getItemAtPosition(position));
                                v[i]=view;
                                name[i]=size[i].split(":")[0].trim();
                                view.setBackgroundResource(R.drawable.delete_page_select_background);
                                ++i;
                            }
                            else{
                                ch.setBackgroundColor(500012);
                                i--;
                            }

                        }
                    }
            );

            delete.setOnClickListener(
                    new Button.OnClickListener(){
                        @Override
                        public void onClick(View vjd) {
                            for(int k=0;k<i;k++)
                            {
                                MainActivity.database.deleteData(name[k]);
                            }
                            Cursor res1=MainActivity.database.getAllData();
                            int p=0;
                            final String[]rearr = new String[res1.getCount()];
                            if(res1.getCount()>0) {
                                while(res1.moveToNext()) {
                                    rearr[p++]=res1.getString(1)+"\t:\t"+res1.getString(2);
                                }
                            }
                            else{
                                MainActivity.database.dropTable();
                            }
                            ListAdapter out1= new ArrayAdapter<String>(delete_data.this, android.R.layout.simple_list_item_1, rearr);
                            arr=rearr;
                            v=new View[rearr.length];
                            size=new String[rearr.length];
                            name=new String[rearr.length];
                            i=0;
                            show.setAdapter(out1);
                        }

                    }
            );
        }
        else {
            MainActivity.database.dropTable();
        }
    }
    public static View check(int i,String[]size,View[]v,String[]name,String s_name) {

        for(int j=0;j<i;j++)
        {
            if(size[j].equals(s_name))
            {
                View vv=v[j];
                for(int k=j;k<i-1;k++){
                    size[k]=size[k+1];
                    v[k]=v[k+1];
                    name[k]=name[k+1];
                }
                return vv;
            }
        }
        return null;
    }
}