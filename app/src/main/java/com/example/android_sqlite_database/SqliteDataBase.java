package com.example.android_sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;


public class SqliteDataBase extends SQLiteOpenHelper {

    public static String Tag="com.example.android_sqlite_database";
    private static String Database_Name="College.db";
    private static String Table_Name="Student_Table";
    public static String col_1="ID";
    public static String col_2="NAME";
    public static String col_3="MARKS";
    public SqliteDataBase(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Table_Name+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT ,MARKS INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }
    public boolean insertData(String name,int marks) {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalue=new ContentValues();
        contentvalue.put(col_2,name);
        contentvalue.put(col_3,marks);
        long result=db.insert(Table_Name,null,contentvalue);
        if(result==-1)
            return false;
        return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+ Table_Name,null);
        return res;
    }
    public void deleteData(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+Table_Name+" WHERE "+col_2+" = \""+name+"\";");
    }
    public void dropTable(){
        SQLiteDatabase db=this.getWritableDatabase();
        Log.i(Tag,"deleting table");
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }
}
