package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseAdaper {

    private static final String DB_NAME = "pet.db";
    private static final int VERSION = 1;
    private DatabaseHelper databaseHelper;
    public DatabaseAdaper(Context context){
        databaseHelper=new DatabaseHelper(context, DB_NAME, null, VERSION);
    }
    //添加操作
    public void add(Dog dog){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PetMetaData.DogTable.NAME,dog.getName());
        values.put(PetMetaData.DogTable.AGE,dog.getAge());
        //参数（表名，可以为null的列名，更新字段的集合ContentValues）
        //合法：insert into dog(name,age) values('xx',2)
        //不合法：insert into dog() values()
        db.insert(PetMetaData.DogTable.TABLE_NAME,null,values);
        db.close();
    }
    //删除操作
    public void delete(int id){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        String whereClause=PetMetaData.DogTable._ID+"=?";
        String[] whereArgs= {String.valueOf(id)};
        //表名，删除条件，条件的值
        db.delete(PetMetaData.DogTable.TABLE_NAME,whereClause,whereArgs);
        db.close();
    }
    //更新操作
    public void update(Dog dog){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PetMetaData.DogTable.NAME,dog.getName());
        values.put(PetMetaData.DogTable.AGE,dog.getAge());
        String whereClause=PetMetaData.DogTable._ID+"=?";
        String[] whereArgs= {String.valueOf(dog.getId())};
        //表名，更新字段的集合ContentValues，条件，条件的值
        db.update(PetMetaData.DogTable.TABLE_NAME,values,whereClause,whereArgs);

    }
    //凭id查询
    public Dog findById(int id){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        String[] colums={PetMetaData.DogTable._ID,PetMetaData.DogTable.NAME,PetMetaData.DogTable.AGE};
        //是否去除重复记录，参数（表名，要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页）
        Cursor c=db.query(true,PetMetaData.DogTable.TABLE_NAME,colums,PetMetaData.DogTable._ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        Dog dog=null;
        if (c.moveToNext()){
            dog=new Dog();
            dog.setId(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
            dog.setName(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
            dog.setAge(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
        }
        c.close();
        db.close();
        return dog;
    }
    //查询所有
    public ArrayList<Dog> findAll(){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        String[] colums={PetMetaData.DogTable._ID,PetMetaData.DogTable.NAME,PetMetaData.DogTable.AGE};
        //是否去除重复记录，参数（表名，要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页）
        Cursor c=db.query(true,PetMetaData.DogTable.TABLE_NAME,colums,null,null,null,null,null,null);
        ArrayList<Dog> dogs=new ArrayList<>();
        Dog dog=null;
        while (c.moveToNext()){
            dog=new Dog();
            dog.setId(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
            dog.setName(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
            dog.setAge(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
            dogs.add(dog);
        }
        c.close();
        db.close();
        return dogs;
    }
}
