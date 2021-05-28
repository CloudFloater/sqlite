package com.example.sqlite;

import android.provider.BaseColumns;

/*
 * 数据库元数据的定义
 * */
public class PetMetaData {
    private PetMetaData(){}
    //Dog表的定义
    public static abstract class DogTable implements BaseColumns{
        public static final String TABLE_NAME="dog";
        public static final String NAME="name";
        public static final String AGE="age";
    }
}

