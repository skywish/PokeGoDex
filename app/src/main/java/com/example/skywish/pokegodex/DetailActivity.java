package com.example.skywish.pokegodex;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.skywish.pokegodex.models.Pokemon;
import com.example.skywish.pokegodex.utilities.PokemonUtil;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static List<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initEvolveMap();

    }

    private void initEvolveMap() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("pokemonId");

        int count = 0;
        if (PokemonUtil.pokemonMap.containsKey(name)) {
            Pokemon pokemon = PokemonUtil.pokemonMap.get(name);
            while (PokemonUtil.pokemonMap.containsKey(pokemon.getParentPokemonId())) {
                pokemon = PokemonUtil.pokemonMap.get(pokemon.getParentPokemonId());
            }
            ImageView ivEvolve1 = findViewById(R.id.img_evolve1);
            // 获取图像
            TypedArray typedArray = getResources().obtainTypedArray(R.array.pokemons);
            ivEvolve1.setImageDrawable(typedArray.getDrawable(pokemon.getNumber() - 1));
            TextView tvEvolve1Maxcp = findViewById(R.id.tv_evolve1_maxcp);
            tvEvolve1Maxcp.setText(String.valueOf(pokemon.getMaxcp()));


            if (PokemonUtil.pokemonMap.containsKey(pokemon.getEvolutionId())) {
                ImageView arrow1 = findViewById(R.id.img_arrow1);
                arrow1.setVisibility(View.VISIBLE);

                pokemon = PokemonUtil.pokemonMap.get(pokemon.getEvolutionId());
                ImageView ivEvolve2 = findViewById(R.id.img_evolve2);
                ivEvolve2.setImageDrawable(typedArray.getDrawable(pokemon.getNumber() - 1));
                LinearLayout lyEvolve2 = findViewById(R.id.ly_evolve2);
                lyEvolve2.setVisibility(View.VISIBLE);

                if (PokemonUtil.pokemonMap.containsKey(pokemon.getEvolutionId())) {
                    ImageView arrow2 = findViewById(R.id.img_arrow2);
                    arrow2.setVisibility(View.VISIBLE);

                    pokemon = PokemonUtil.pokemonMap.get(pokemon.getEvolutionId());
                    ImageView ivEvolve3 = findViewById(R.id.img_evolve3);
                    ivEvolve3.setImageDrawable(typedArray.getDrawable(pokemon.getNumber() - 1));
                    LinearLayout lyEvolve3 = findViewById(R.id.ly_evolve3);
                    lyEvolve3.setVisibility(View.VISIBLE);
                }
            }
            typedArray.recycle();
        } else {
            throw new RuntimeException("there is no pokemon that matches the name " + name);
        }
    }
}
