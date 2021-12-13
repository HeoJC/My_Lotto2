package com.example.mylotto2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LottoDAO {

    public static ArrayList<LottoVO> selectList(DBHelper dbHelper) {
        ArrayList<LottoVO> list = new ArrayList<>() ;
        SQLiteDatabase db = dbHelper.getWritableDatabase() ;
        String sql = "select count , number from lotto" ;
        Cursor cursor = db.rawQuery(sql , null) ;
        while(cursor.moveToNext()) {
            LottoVO lottoVO = new LottoVO() ;
            lottoVO.setCount(cursor.getString(0)) ;
            lottoVO.setNumber(cursor.getString(1)) ;
            list.add(lottoVO) ;
        } ;
        return list ;
    }

    public static  void insert(DBHelper dbHelper , LottoVO lottoVO) {
        SQLiteDatabase db = dbHelper.getWritableDatabase() ;

        ContentValues contentValues = new ContentValues() ;
        contentValues.put("count" , lottoVO.getCount()) ;
        contentValues.put("number" , lottoVO.getNumber()) ;

        db.insert("lotto" , null , contentValues) ;
        dbHelper.close() ;
    }

    public static void delete(DBHelper dbHelper , String count) {
        SQLiteDatabase db = dbHelper.getWritableDatabase() ;

        db.delete("lotto" , "count=?" , new String[]{count}) ;
    }
}
