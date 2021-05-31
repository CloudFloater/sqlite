package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseAdaper {

    private static final String DB_NAME = "medicine.db";
    private static final int VERSION = 1;
    private DatabaseHelper databaseHelper;
    public DatabaseAdaper(Context context){
        databaseHelper=new DatabaseHelper(context, DB_NAME, null, VERSION);
    }
    //添加操作
    public void add(Medicine medicine){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MedicineData.MedicineTable.NAME, medicine.getName());
        values.put(MedicineData.MedicineTable.FAMILY_NAME, medicine.getFamily_name());
        values.put(MedicineData.MedicineTable.DEADLINE,medicine.getDeadline());
        values.put(MedicineData.MedicineTable.INTRODUCTION,medicine.getIntroduction());
        values.put(MedicineData.MedicineTable.METHOD,medicine.getMethod());
        //参数（表名，可以为null的列名，更新字段的集合ContentValues）
        //合法：insert into dog(name,age) values('xx',2)
        //不合法：insert into dog() values()
        db.insert(MedicineData.MedicineTable.TABLE_NAME,null,values);
        db.close();
    }
    //删除操作
    public void delete(int id){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        String whereClause=MedicineData.MedicineTable._ID+"=?";
        String[] whereArgs= {String.valueOf(id)};
        //表名，删除条件，条件的值
        db.delete(MedicineData.MedicineTable.TABLE_NAME,whereClause,whereArgs);
        db.close();
    }
    //更新操作
    public void update(Medicine medicine){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MedicineData.MedicineTable.NAME, medicine.getName());
        values.put(MedicineData.MedicineTable.FAMILY_NAME, medicine.getFamily_name());
        values.put(MedicineData.MedicineTable.DEADLINE,medicine.getDeadline());
        values.put(MedicineData.MedicineTable.INTRODUCTION,medicine.getIntroduction());
        values.put(MedicineData.MedicineTable.METHOD,medicine.getMethod());
        String whereClause=MedicineData.MedicineTable._ID+"=?";
        String[] whereArgs= {String.valueOf(medicine.getId())};
        //表名，更新字段的集合ContentValues，条件，条件的值
        db.update(MedicineData.MedicineTable.TABLE_NAME,values,whereClause,whereArgs);

    }
    //凭id查询
    public Medicine findById(int id){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        String[] colums={MedicineData.MedicineTable._ID,MedicineData.MedicineTable.NAME,MedicineData.MedicineTable.DEADLINE,MedicineData.MedicineTable.FAMILY_NAME,MedicineData.MedicineTable.INTRODUCTION, MedicineData.MedicineTable.METHOD};
        //是否去除重复记录，参数（表名，要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页）
        Cursor c=db.query(true,MedicineData.MedicineTable.TABLE_NAME,colums,MedicineData.MedicineTable._ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        Medicine medicine =null;
        if (c.moveToNext()){
            medicine =new Medicine();
            medicine.setId(c.getInt(c.getColumnIndexOrThrow(MedicineData.MedicineTable._ID)));
            medicine.setName(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.NAME)));
            medicine.setDeadline(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.DEADLINE)));
            medicine.setFamily_name(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.FAMILY_NAME)));
            medicine.setIntroduction(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.INTRODUCTION)));
            medicine.setMethod(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.METHOD)));
        }
        c.close();
        db.close();
        return medicine;
    }
    //凭name查询
    public ArrayList<Medicine> findByName(String name){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        String[] colums={MedicineData.MedicineTable._ID,MedicineData.MedicineTable.NAME,MedicineData.MedicineTable.DEADLINE,MedicineData.MedicineTable.FAMILY_NAME,MedicineData.MedicineTable.INTRODUCTION, MedicineData.MedicineTable.METHOD};
        //是否去除重复记录，参数（表名，要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页）
        Cursor c=db.query(true,MedicineData.MedicineTable.TABLE_NAME,colums,MedicineData.MedicineTable.FAMILY_NAME+"=?",new String[]{String.valueOf(name)},null,null,null,null);
        ArrayList<Medicine> medicines =new ArrayList<>();
        Medicine medicine =null;
        while (c.moveToNext()){
            medicine =new Medicine();
            medicine.setId(c.getInt(c.getColumnIndexOrThrow(MedicineData.MedicineTable._ID)));
            medicine.setName(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.NAME)));
            medicine.setDeadline(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.DEADLINE)));
            medicine.setFamily_name(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.FAMILY_NAME)));
            medicine.setIntroduction(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.INTRODUCTION)));
            medicine.setMethod(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.METHOD)));
            medicines.add(medicine);
        }
        c.close();
        db.close();
        return medicines;
    }
    //查询所有
    public ArrayList<Medicine> findAll(){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        String[] colums={MedicineData.MedicineTable._ID,MedicineData.MedicineTable.NAME,MedicineData.MedicineTable.DEADLINE,MedicineData.MedicineTable.FAMILY_NAME,MedicineData.MedicineTable.INTRODUCTION, MedicineData.MedicineTable.METHOD};
        //是否去除重复记录，参数（表名，要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页）
        Cursor c=db.query(true,MedicineData.MedicineTable.TABLE_NAME,colums,null,null,null,null,null,null);
        ArrayList<Medicine> medicines =new ArrayList<>();
        Medicine medicine =null;
        while (c.moveToNext()){
            medicine =new Medicine();
            medicine.setId(c.getInt(c.getColumnIndexOrThrow(MedicineData.MedicineTable._ID)));
            medicine.setName(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.NAME)));
            medicine.setDeadline(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.DEADLINE)));
            medicine.setFamily_name(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.FAMILY_NAME)));
            medicine.setIntroduction(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.INTRODUCTION)));
            medicine.setMethod(c.getString(c.getColumnIndexOrThrow(MedicineData.MedicineTable.METHOD)));
            medicines.add(medicine);
        }
        c.close();
        db.close();
        return medicines;
    }
}
