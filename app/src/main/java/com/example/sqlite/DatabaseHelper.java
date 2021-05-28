package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="pet.db";
    private static final int VERSION=1;
    private static final String CREATE_TABLE_Medicine_Kit="create table medicine(_id integer primary key autoincrement,"+
            "name text,age integer)";
    private static final String DROP_TABLE_Medicine_Kit="DROP TABLE IF EXISTS medicine";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, VERSION);
    }

    //如果数据库表不存在，那么会调用该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLiteDatabase 用于操作数据库的工具类
        db.execSQL(CREATE_TABLE_Medicine_Kit);
    }

    //升级更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_Medicine_Kit);
        db.execSQL(CREATE_TABLE_Medicine_Kit);
    }
}
