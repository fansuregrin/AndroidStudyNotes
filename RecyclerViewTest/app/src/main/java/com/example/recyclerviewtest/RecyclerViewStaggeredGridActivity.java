package com.example.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewStaggeredGridActivity extends AppCompatActivity {
    private final List<Pokemon> pokemonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_staggered_grid);
        initPokemonList();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_staggered_grid);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
            StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        PokemonAdapter adapter = new PokemonAdapter(pokemonList, R.layout.pokemon_item);
        recyclerView.setAdapter(adapter);
    }

    private void initPokemonList() {
        pokemonList.add(new Pokemon(R.string.bulbasaur, R.drawable.bulbasaur, R.string.bulbasaur_desc));
        pokemonList.add(new Pokemon(R.string.meowth, R.drawable.meowth, R.string.meowth_desc));
        pokemonList.add(new Pokemon(R.string.pikachu, R.drawable.pikachu, R.string.pkiachu_desc));
        pokemonList.add(new Pokemon(R.string.psyduck, R.drawable.psyduck, R.string.psyduck_desc));
        pokemonList.add(new Pokemon(R.string.squirtle, R.drawable.squirtle, R.string.squirtle_desc));
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecyclerViewStaggeredGridActivity.class);
        context.startActivity(intent);
    }
}