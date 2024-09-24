package com.example.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);

        findViewById(R.id.open_db).setOnClickListener(v -> {
            dbHelper.getWritableDatabase();
            Toast.makeText(this, R.string.open_db_successfully, Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.add_data).setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "Harry Potter");
            values.put("author", "J. K. Rolling");
            values.put("pages", 987);
            values.put("price", 278.97);
            db.insert("Book", null, values);
            values.clear();
            values.put("name", "Acceptance");
            values.put("author", "Jeff VanderMeer");
            values.put("pages", 341);
            values.put("price", 46.83);
            db.insert("Book", null, values);
        });

        findViewById(R.id.update_data).setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 50.34);
            db.update("Book", values, "name = ?", new String[] {"Acceptance"});
        });

        findViewById(R.id.del_data).setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            db.delete("Book", "pages > ?", new String[] { "500" });
        });

        findViewById(R.id.query_data).setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("Book", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int nameIdx = cursor.getColumnIndex("name");
                int authorIdx = cursor.getColumnIndex("author");
                int pagesIdx = cursor.getColumnIndex("pages");
                int priceIdx = cursor.getColumnIndex("price");
                String name = cursor.getString(nameIdx);
                String author = cursor.getString(authorIdx);
                int pages = cursor.getInt(pagesIdx);
                float price = cursor.getFloat(priceIdx);
                Log.d(TAG, "book name: " + name);
                Log.d(TAG, "book author: " + author);
                Log.d(TAG, "book pages: " + pages);
                Log.d(TAG, "book price: " + price);
            }
            cursor.close();
        });
    }
}