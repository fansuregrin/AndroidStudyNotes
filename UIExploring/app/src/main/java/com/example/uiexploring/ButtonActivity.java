package com.example.uiexploring;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ButtonActivity.class);
        context.startActivity(intent);
    }
}