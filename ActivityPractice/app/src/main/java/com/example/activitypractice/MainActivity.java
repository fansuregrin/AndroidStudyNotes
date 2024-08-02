package com.example.activitypractice;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(v -> {
            FirstActivity.actionStart(this);
        });

        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(v -> {
            SecondActivity.actionStart(this);
        });

        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(v -> {
            ThirdActivity.actionStart(this);
        });
    }
}