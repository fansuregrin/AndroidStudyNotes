package com.example.uilayouttest;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLinearLayout = findViewById(R.id.btn_linear_layout);
        btnLinearLayout.setOnClickListener(v -> {
            LinearLayoutActivity.actionStart(this);
        });

        Button btnLayoutWeight = findViewById(R.id.btn_layout_weight);
        btnLayoutWeight.setOnClickListener(v -> {
            LayoutWeightActivity.actionStart(this);
        });

        Button btnRelativeLayout = findViewById(R.id.btn_relative_layout);
        btnRelativeLayout.setOnClickListener(v -> {
            RelativeLayoutActivity.actionStart(this);
        });

        Button btnRelativeLayout2 = findViewById(R.id.btn_relative_layout_2);
        btnRelativeLayout2.setOnClickListener(v -> {
            RelativeLayoutActivity2.actionStart(this);
        });

        Button btnFrameLayout = findViewById(R.id.btn_frame_layout);
        btnFrameLayout.setOnClickListener(v -> {
            FrameLayoutActivity.actionStart(this);
        });

        Button btnConstraintLayout = findViewById(R.id.btn_constraint_layout);
        btnConstraintLayout.setOnClickListener(v -> {
            ConstraintLayoutActivity.actionStart(this);
        });

        Button btnIncludeLayout = findViewById(R.id.btn_include_layout);
        btnIncludeLayout.setOnClickListener(v -> {
            IncludeLayoutActivity.actionStart(this);
        });

        Button btnCustomLayout = findViewById(R.id.btn_custom_layout);
        btnCustomLayout.setOnClickListener(v -> {
            CustomLayoutActivity.actionStart(this);
        });

        Button btnListView = findViewById(R.id.btn_list_view);
        btnListView.setOnClickListener(v -> {
            ListViewActivity.actionStart(this);
        });
    }
}