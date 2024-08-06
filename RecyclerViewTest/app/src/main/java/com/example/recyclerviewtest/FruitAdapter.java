package com.example.recyclerviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }

    private final List<Fruit> mFruitList;
    private final int mFruitItemLayout;

    public FruitAdapter(List<Fruit> fruitList, int fruitItemLayout) {
        mFruitList = fruitList;
        mFruitItemLayout = fruitItemLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(mFruitItemLayout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Context context = v.getContext();
            String msg = context.getString(R.string.click_view) + context.getString(fruit.getNameId());
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        });
        holder.fruitImage.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Context context = v.getContext();
            String msg = context.getString(R.string.click_image) + context.getString(fruit.getNameId());
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getNameId());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
