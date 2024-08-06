package com.example.uiexploring;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTextView = findViewById(R.id.btn_text_view);
        btnTextView.setOnClickListener( v -> {
            TextViewActivity.actionStart(this);
        });

        Button btnEditText = findViewById(R.id.btn_edit_text);
        btnEditText.setOnClickListener(v -> {
            EditTextActivity.actionStart(this);
        });

        Button btnImageView = findViewById(R.id.btn_image_view);
        btnImageView.setOnClickListener(v -> {
            ImageViewActivity.actionStart(this);
        });

        Button btnProgressBar = findViewById(R.id.btn_progress_bar);
        btnProgressBar.setOnClickListener(v -> {
            ProgressBarActivity.actionStart(this);
        });

        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(v -> {
            if (v.getId() == R.id.button_6) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle(R.string.alert_title);
                alertDialog.setMessage(R.string.alert_msg);
                alertDialog.setPositiveButton(R.string.ok, (dialog, which) -> {

                });
                alertDialog.setNegativeButton(R.string.cancel, (dialog, which) -> {

                });
                alertDialog.show();
            }
        });
    }
}