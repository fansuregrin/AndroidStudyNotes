package com.example.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImage;
        TextView pokemonName;
        TextView pokemonDesc;

        public ViewHolder(View view) {
            super(view);
            pokemonImage = view.findViewById(R.id.pokemon_image);
            pokemonName = view.findViewById(R.id.pokemon_name);
            pokemonDesc = view.findViewById(R.id.pokemon_desc);
        }
    }

    private final List<Pokemon> mPokemonList;
    private final int mPokemonItemLayout;

    public PokemonAdapter(List<Pokemon> pokemonList, int pokemonItemLayout) {
        mPokemonList = pokemonList;
        mPokemonItemLayout = pokemonItemLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(mPokemonItemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = mPokemonList.get(position);
        holder.pokemonImage.setImageResource(pokemon.getImageId());
        holder.pokemonName.setText(pokemon.getNameId());
        holder.pokemonDesc.setText(pokemon.getDescId());
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }
}
