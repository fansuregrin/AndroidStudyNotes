package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "CREATE TABLE Book(" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "author TEXT," +
        "price REAL," +
        "pages INTEGER," +
        "name TEXT);";
    public static final String CREATE_CATEGORY = "CREATE TABLE Category(" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "category_name TEXT," +
        "category_code INTEGER)";

    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Book");
        db.execSQL("DROP TABLE IF EXISTS Category");
        onCreate(db);
    }
}
