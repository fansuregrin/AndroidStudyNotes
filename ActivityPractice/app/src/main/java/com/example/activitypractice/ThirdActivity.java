package com.example.activitypractice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(v -> {
            ActivityCollector.finishAll();
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ThirdActivity.class);
        context.startActivity(intent);
    }
}