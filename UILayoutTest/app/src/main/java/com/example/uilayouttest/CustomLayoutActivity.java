package com.example.uilayouttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CustomLayoutActivity extends AppCompatActivity {
    private TitleLayout mTitleLayout;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mEditText = findViewById(R.id.edit_text);
        mTitleLayout = findViewById(R.id.title_layout);
        mTitleLayout.setTitleEditOnClickListener(v -> {
            if (mTitleLayout.getTitleEditText() == getString(R.string.edit)) {
                mEditText.setEnabled(true);
                mEditText.setFocusableInTouchMode(true);
                mEditText.requestFocus();
                mTitleLayout.setTitleEditText(R.string.ok);
            } else {
                mEditText.setEnabled(false);
                mTitleLayout.setTitleEditText(R.string.edit);
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CustomLayoutActivity.class);
        context.startActivity(intent);
    }
}