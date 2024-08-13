package com.example.uibestpractice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button btnSend;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgs();
        inputText = findViewById(R.id.input_text);
        btnSend = findViewById(R.id.btn_send);
        msgRecyclerView = findViewById(R.id.msg_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        btnSend.setOnClickListener(v -> {
            String content = inputText.getText().toString();
            if (!content.isEmpty()) {
                Msg msg = new Msg(content, Msg.TYPE_SENT);
                msgList.add(msg);
                adapter.notifyItemInserted(msgList.size() - 1);
                msgRecyclerView.scrollToPosition(msgList.size() - 1);
                inputText.setText("");
            }
        });
    }

    private void initMsgs() {
        msgList.add(new Msg("Hello guy.", Msg.TYPE_RECEIVED));
        msgList.add(new Msg("Hello, who is that?", Msg.TYPE_SENT));
        msgList.add(new Msg("This is Tom. Nice to talking to you.", Msg.TYPE_RECEIVED));
    }
}