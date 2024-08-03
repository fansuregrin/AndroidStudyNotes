package com.example.uiexploring;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(v -> {
            if (v.getId() == R.id.button_1) {
                String inputText = editText.getText().toString();
                Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(v -> {
            if (v.getId() == R.id.button_2) {
                imageView.setImageResource(R.drawable.img_2);
            }
        });

        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(v -> {
            if (v.getId() == R.id.button_3) {
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                    button3.setText(R.string.hide);
                } else {
                    progressBar.setVisibility(View.GONE);
                    button3.setText(R.string.show);
                }
            }
        });

        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(v -> {
            if (v.getId() == R.id.button_4) {
                int progress = progressBar.getProgress();
                progressBar.setProgress(Math.min(progressBar.getMax(), progress + 10));
            }
        });

        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(v -> {
            if (v.getId() == R.id.button_5) {
                int progress = progressBar.getProgress();
                progressBar.setProgress(Math.max(progressBar.getMin(), progress - 10));
            }
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