package com.example.lostandfound;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LostFound.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE items (id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, name TEXT, phone TEXT, description TEXT, date TEXT, location TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    public boolean insertItem(String type, String name, String phone, String description, String date, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type", type);
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("description", description);
        cv.put("date", date);
        cv.put("location", location);

        long result = db.insert("items", null, cv);
        return result != -1;
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM items", null);
    }

    public String getItemDetails(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM items WHERE id = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            return "Type: " + cursor.getString(1) +
                    "\nName: " + cursor.getString(2) +
                    "\nPhone: " + cursor.getString(3) +
                    "\nDescription: " + cursor.getString(4) +
                    "\nDate: " + cursor.getString(5) +
                    "\nLocation: " + cursor.getString(6);
        }
        return "No details found";
    }

    public boolean deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("items", "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
