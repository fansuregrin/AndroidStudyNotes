package com.example.uiexploring;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        progressBar = findViewById(R.id.progress_bar);
        Button btnShowHide = findViewById(R.id.btn_show_hide);
        btnShowHide.setOnClickListener(v -> {
            if (v.getId() == R.id.btn_show_hide) {
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                    btnShowHide.setText(R.string.hide);
                } else {
                    progressBar.setVisibility(View.GONE);
                    btnShowHide.setText(R.string.show);
                }
            }
        });

        Button btnIncrease = findViewById(R.id.btn_increase);
        btnIncrease.setOnClickListener(v -> {
            if (v.getId() == R.id.btn_increase) {
                int progress = progressBar.getProgress();
                progressBar.setProgress(Math.min(progressBar.getMax(), progress + 10));
            }
        });

        Button btnDecrease = findViewById(R.id.btn_decrease);
        btnDecrease.setOnClickListener(v -> {
            if (v.getId() == R.id.btn_decrease) {
                int progress = progressBar.getProgress();
                progressBar.setProgress(Math.max(progressBar.getMin(), progress - 10));
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ProgressBarActivity.class);
        context.startActivity(intent);
    }
}