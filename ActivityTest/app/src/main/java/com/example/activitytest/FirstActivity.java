package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "creating first activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        // 使用 Toast
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(v -> Toast.makeText(FirstActivity.this,
                R.string.toast_for_button_1, Toast.LENGTH_LONG).show());

        // 使用 Toast（另一种方式设置点击事件）
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, R.string.toast_for_button_2,
                        Toast.LENGTH_LONG).show();
            }
        });

        // 使用显式 Intent
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        // 使用隐式 Intent
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.activitytest.ACTION_START");
            startActivity(intent);
        });

        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.activitytest.ACTION_START");
            intent.addCategory("com.example.activitytest.MY_CATEGORY");
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.add_item) {
            Toast.makeText(this, R.string.toast_for_add, Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.remove_item) {
            Toast.makeText(this, R.string.toast_for_remove, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "destroying first activity");
        super.onDestroy();
    }
}