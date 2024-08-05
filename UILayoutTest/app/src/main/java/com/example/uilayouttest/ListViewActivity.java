package com.example.uilayouttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private final List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initFruits();
        FruitAdapter adapter = new FruitAdapter(this, R.layout.fruit_item, fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Fruit fruit = fruitList.get(position);
            Toast.makeText(this, fruit.getNameId(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initFruits() {
        fruitList.add(new Fruit(R.string.apple, R.drawable.apple));
        fruitList.add(new Fruit(R.string.banana, R.drawable.banana));
        fruitList.add(new Fruit(R.string.orange, R.drawable.orange));
        fruitList.add(new Fruit(R.string.watermelon, R.drawable.watermelon));
        fruitList.add(new Fruit(R.string.pear, R.drawable.pear));
        fruitList.add(new Fruit(R.string.grape, R.drawable.grape));
        fruitList.add(new Fruit(R.string.pineapple, R.drawable.pineapple));
        fruitList.add(new Fruit(R.string.strawberry, R.drawable.strawberry));
        fruitList.add(new Fruit(R.string.cherry, R.drawable.cherry));
        fruitList.add(new Fruit(R.string.mango, R.drawable.mango));
        fruitList.add(new Fruit(R.string.mandarin, R.drawable.mandarin));
        fruitList.add(new Fruit(R.string.peach, R.drawable.peach));
        fruitList.add(new Fruit(R.string.loquat, R.drawable.loquat));
        fruitList.add(new Fruit(R.string.hawthorn, R.drawable.hawthorn));
        fruitList.add(new Fruit(R.string.kiwi_fruit, R.drawable.kiwi_fruit));
        fruitList.add(new Fruit(R.string.lychee, R.drawable.lychee));
        fruitList.add(new Fruit(R.string.blueberry, R.drawable.blueberry));
        fruitList.add(new Fruit(R.string.pitaya, R.drawable.pitaya));
        fruitList.add(new Fruit(R.string.hami_melon, R.drawable.hami_melon));
        fruitList.add(new Fruit(R.string.longan, R.drawable.longan));
        fruitList.add(new Fruit(R.string.mangosteen, R.drawable.mangosteen));
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ListViewActivity.class);
        context.startActivity(intent);
    }
}