package com.andela.gkuti.task2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.andela.gkuti.task2.Constants.*;


public class ContactStore extends SQLiteOpenHelper {

    public ContactStore(Context context) {
        super(context, DATABASE_NAME.getValue(), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME.getValue() + "(" + ID_COLUMN.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN.getValue() + " VARCHAR, " + PHONE_COLUMN.getValue() + " VARCHAR, " +
                ADDRESS_COLUMN.getValue() + " VARCHAR, " + EMAIL_COLUMN.getValue() + " VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveData(String name, String phoneNumber, String address, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN.getValue(), name);
        cv.put(PHONE_COLUMN.getValue(), phoneNumber);
        cv.put(ADDRESS_COLUMN.getValue(), address);
        cv.put(EMAIL_COLUMN.getValue(), email);
        long status = sqLiteDatabase.insert(TABLE_NAME.getValue(), null, cv);
        sqLiteDatabase.close();
        return status;
    }

    public Cursor getContactByDate(String name) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionArgs = {name};
        return sqLiteDatabase.query(TABLE_NAME.getValue(), null, NAME_COLUMN.getValue() + " =?", selectionArgs, null, null, null);
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.query(TABLE_NAME.getValue(), null, null, null, null, null, null);
    }
}