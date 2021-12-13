package com.example.mylotto2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME = "lotto.db" ;
    public static int VERSION = 1 ;

    public DBHelper(Context context) { super(context, NAME, null, VERSION) ; }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists lotto("
                + "count text ,"
                + "number text)" ;
        db.execSQL(sql) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
