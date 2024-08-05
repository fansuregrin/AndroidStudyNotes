package com.example.uilayouttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RelativeLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RelativeLayoutActivity.class);
        context.startActivity(intent);
    }
}