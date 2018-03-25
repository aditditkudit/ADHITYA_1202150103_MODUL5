package com.example.android.adhitya_1202150103_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Tavs on 25/03/2018.
 */

public class Database  extends SQLiteOpenHelper{
    //Deklarasi buat komponen db,table dan column name.
    Context konteks;
    SQLiteDatabase db;

    public static final String db_name = "modul5ListToDo.db";
    public static final String table_name = "listAktivitas";
    public static final String column1 = "toDo";
    public static final String column2 = "deskripsi";
    public static final String column3 = "prioritas";

    //Membuat konstruktor com.example.android.adhitya_1202150103_modul5.Database

    public Database(Context context){
        super(context, db_name, null, 1);
        this.konteks = context;
        db = this.getWritableDatabase();
        db.execSQL("create table if not exists "+table_name+" (toDo varchar(35) primary key, deskripsi varchar(50), prioritas varchar(3))");

    }
    //Ketika database sudah dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+table_name+" (toDo varchar(35) primary key, deskripsi varchar(50), prioritas varchar(3))");
    }
    //Ketika database mau di update tablenya
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists"+table_name);
        onCreate(sqLiteDatabase);
    }
    //Method proses pencocokkan disaat penginputan data
    public boolean nambahData(Model list){
        ContentValues cVal = new ContentValues();
        cVal.put(column1, list.getToDo());
        cVal.put(column2, list.getDesk());
        cVal.put(column3, list.getPrior());
        long result = db.insert(table_name, null, cVal);
        if (result==-1){
            return false;
        } else {
            return true;
        }
    }

    // Method disaaat delete data
    // "=\""+todo+"\"" */
    public boolean hapusData(String todo){
        return db.delete(table_name, column1+"=\""+todo+"\"", null)>0;
    }

    //method untuk mengakses dan membaca data dari database

    public void readData(ArrayList<Model> listData){
        Cursor cursor = this.getReadableDatabase().rawQuery("select toDo, deskripsi, prioritas from "+table_name, null);
        while (cursor.moveToNext()){
            listData.add(new Model(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}
