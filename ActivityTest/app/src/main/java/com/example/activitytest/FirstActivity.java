package com.example.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate " + this.toString());
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

        // 启动浏览器，访问指定网站
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://xiaomi.com"));
            startActivity(intent);
        });

        // 拨号
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        });

        // 传递数据给下一个活动
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(v -> {
            String data = "Hello FourthActivity";
            Intent intent = new Intent(FirstActivity.this, FourthActivity.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        });


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == FirstActivity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        String returnedData = data.getStringExtra("data_return");
                        assert returnedData != null;
                        Log.d(TAG, returnedData);
                    }
                }
        );
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, TargetActivity.class);
            launcher.launch(intent);
        });

        Button button10 = findViewById(R.id.button_10);
        button10.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, FirstActivity.class);
            startActivity(intent);
        });

        Button button11 = findViewById(R.id.button_11);
        button11.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, ThirdActivity.class);
            startActivity(intent);
        });

        Log.d(TAG, "Task id is " + getTaskId());
        Button button12 = findViewById(R.id.button_12);
        button12.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, FifthActivity.class);
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
        Log.d(TAG, "onDestroy " + this.toString());
        super.onDestroy();
    }
}