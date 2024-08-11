package com.example.uilayouttest;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
    private final TextView mTitleText;
    private final Button mTitleBack;
    private final Button mTitleEdit;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        String title = a.getString(R.styleable.TitleLayout_title);
        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.title, this);

        mTitleText = findViewById(R.id.title_text);
        mTitleText.setText(title);

        mTitleBack = findViewById(R.id.title_back);
        mTitleEdit = findViewById(R.id.title_edit);
        mTitleBack.setOnClickListener(v -> ((Activity) getContext()).finish());
        mTitleEdit.setOnClickListener(v -> Toast.makeText(
            getContext(), R.string.click_edit_btn, Toast.LENGTH_SHORT).show());
    }

    public CharSequence getTitle() {
        return mTitleText.getText();
    }

    public void setTitle(CharSequence title) {
        mTitleText.setText(title);
    }

    public void setTitle(int resid) {
        mTitleText.setText(resid);
    }

    public void setTitleEditOnClickListener(View.OnClickListener listener) {
        mTitleEdit.setOnClickListener(listener);
    }

    public CharSequence getTitleEditText() {
        return mTitleEdit.getText();
    }

    public void setTitleEditText(CharSequence text) {
        mTitleEdit.setText(text);
    }

    public void setTitleEditText(int resid) {
        mTitleEdit.setText(resid);
    }
}
