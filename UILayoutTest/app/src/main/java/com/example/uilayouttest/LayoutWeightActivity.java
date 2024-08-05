package com.example.uilayouttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutWeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_weight);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LayoutWeightActivity.class);
        context.startActivity(intent);
    }
}