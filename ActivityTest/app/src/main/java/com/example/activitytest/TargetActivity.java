package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target_layout);

        Button button = findViewById(R.id.button_return_data);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("data_return", "Hello FirstActivity");
            setResult(RESULT_OK, intent);
            finish();
        });

        OnBackPressedDispatcher onBackPressDispatcher = getOnBackPressedDispatcher();
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent();
                intent.putExtra("data_return", "Hello FirstActivity");
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        onBackPressDispatcher.addCallback(callback);
    }
}