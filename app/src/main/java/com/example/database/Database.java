package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "mydata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String query= "create table student(" +
              "id integer primary key,name text,g_id integer)";

      db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student" );
        onCreate(db);
    }

    public void addData(String name,int g_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("g_id",g_id);

        db.insert("student",null,values);
        db.close();
    }

     public ArrayList<Model> readData(){

        SQLiteDatabase db = this.getReadableDatabase();

         Cursor cursor = db.rawQuery("select id,name,g_id from student",null);

         ArrayList<Model> arrayList = new ArrayList<>();

         if (cursor.moveToFirst()){
             do {
                  String name = cursor.getString(1);
                  int g_id = cursor.getInt(2);

                  arrayList.add(new Model(name,g_id));
             }while (cursor.moveToNext());
         }
         cursor.close();
         return arrayList;
     }

}
