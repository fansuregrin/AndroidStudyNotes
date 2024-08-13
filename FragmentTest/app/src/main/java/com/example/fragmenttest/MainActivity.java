package com.example.fragmenttest;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFirstLook = findViewById(R.id.btn_first_look);
        btnFirstLook.setOnClickListener(v -> {
            FirstLookActivity.actionStart(this);
        });
    }
}