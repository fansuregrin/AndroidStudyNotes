package com.example.uilayouttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class IncludeLayoutActivity extends AppCompatActivity {
    private EditText editText;
    private Button titleEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include_layout);

        TextView titleText = findViewById(R.id.title_text);
        titleText.setText(R.string.include_layout);

        editText = findViewById(R.id.edit_text);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Button titleBack = findViewById(R.id.title_back);
        titleBack.setOnClickListener(v -> {
            finish();
        });

        titleEdit = findViewById(R.id.title_edit);
        titleEdit.setOnClickListener(v -> {
            if (titleEdit.getText() == getString(R.string.edit)) {
                editText.setEnabled(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                titleEdit.setText(R.string.ok);
            } else {
                editText.setEnabled(false);
                titleEdit.setText(R.string.edit);
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, IncludeLayoutActivity.class);
        context.startActivity(intent);
    }
}