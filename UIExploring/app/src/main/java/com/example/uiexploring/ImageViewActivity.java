package com.example.uiexploring;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ImageViewActivity extends AppCompatActivity {
    private ImageView imageView;
    private final List<Integer> imageIdList = new ArrayList<>();
    private int currImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        initImageIdList();
        currImageId = 0;
        imageView = findViewById(R.id.image_view);
        imageView.setImageResource(imageIdList.get(currImageId));
        Button btnChangePic = findViewById(R.id.btn_change_pic);
        btnChangePic.setOnClickListener(v -> {
            if (v.getId() == R.id.btn_change_pic) {
                currImageId = (currImageId + 1) % imageIdList.size();
                imageView.setImageResource(imageIdList.get(currImageId));
            }
        });
    }

    private void initImageIdList() {
        imageIdList.add(R.drawable.img_1);
        imageIdList.add(R.drawable.img_2);
        imageIdList.add(R.drawable.img_3);
        imageIdList.add(R.drawable.img_4);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ImageViewActivity.class);
        context.startActivity(intent);
    }
}