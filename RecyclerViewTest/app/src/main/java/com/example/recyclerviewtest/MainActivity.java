package com.example.recyclerviewtest;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRecyclerViewVertical = findViewById(R.id.btn_recycler_view_vertical);
        btnRecyclerViewVertical.setOnClickListener(v -> {
            RecyclerViewVerticalActivity.actionStart(this);
        });
    }
}