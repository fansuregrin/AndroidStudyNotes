package com.example.sharedpreferencestest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_save_data).setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putString("name", "Tom");
            editor.putInt("age", 16);
            editor.putBoolean("married", false);
            editor.putFloat("weight", 67.4f);
            editor.apply();
        });

        findViewById(R.id.btn_restore_data).setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
            String name = pref.getString("name", "");
            int age = pref.getInt("age", -1);
            boolean married = pref.getBoolean("married", false);
            float weight = pref.getFloat("weight", 0.0f);
            Log.d(TAG, "name: " + name);
            Log.d(TAG, "age: " + age);
            Log.d(TAG, "married: " + married);
            Log.d(TAG, "weight: " + weight);
        });
    }
}