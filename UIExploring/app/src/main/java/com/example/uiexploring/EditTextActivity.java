package com.example.uiexploring;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditTextActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        editText = findViewById(R.id.edit_text_multiline);
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(v -> {
            if (v.getId() == R.id.button_1) {
                String inputText = editText.getText().toString();
                Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show();
            }
        });

        ((EditText)findViewById(R.id.edit_text_action_send)).setOnEditorActionListener(
            (v, actionId, event) -> {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    handled = true;
                    String msg = getString(R.string.send_prompt) + v.getText().toString();
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }
                return handled;
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, EditTextActivity.class);
        context.startActivity(intent);
    }
}