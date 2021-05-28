package com.example.sqlite;

import android.provider.BaseColumns;

/*
 * 数据库元数据的定义
 * */
public class MedicineData {
    private MedicineData(){}
    //Medicine表的定义
    public static abstract class MedicineTable implements BaseColumns{
        public static final String TABLE_NAME="medicine";
        public static final String NAME="name";
        public static final String AGE="age";
    }
}

