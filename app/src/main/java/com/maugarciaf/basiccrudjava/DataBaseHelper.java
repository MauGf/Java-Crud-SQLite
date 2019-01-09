package com.maugarciaf.basiccrudjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NANE = "prueba.db";
    public static final String TABLE_NAME = "Table_Student";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "EMAIL";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NANE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "  + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, EMAIL TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    // Insert data
    public long insertData(String name, String surname, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, email);
        long insert = db.insert(TABLE_NAME,null,contentValues);
        db.close();

        return insert;
    }

    //Read data
    public Cursor getAlldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select *from " + TABLE_NAME,null);
        return res;
    }

    // Update data
    public boolean updateData(String id, String name, String surname, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, email);
        int update = db.update(TABLE_NAME,contentValues,"ID =?", new String[]{id});
        if (update>0){
            return true;
        }else{
            return false;
        }
    }

    // Delete Data
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,"ID =?", new String[]{id});
        return i;
    }
}
