package com.example.skywish.pokegodex.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skywish.pokegodex.R;
import com.example.skywish.pokegodex.models.Pokemon;
import com.example.skywish.pokegodex.utilities.PokemonUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Pokemon> mDataset;
    private List<Pokemon> originalData;
    private static String TAG = "Adapter";
    private Context context;
    private Map<String, Integer> colorMap;

    // first time is true
    private boolean numClick = false;
    private boolean nameClick = false;
    private boolean cpClick = false;
    private boolean attClick = false;
    private boolean defClick = false;
    private boolean staClick = false;

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvNumber;
        public ImageView mIvImg;
        public TextView mTvName;
        public TextView mTvType1;
        public TextView mTvType2;
        public TextView mTvMaxCP;
        public TextView mTvAttack;
        public TextView mTvDefense;
        public TextView mTvStamina;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "PokemonViewHolder: " + itemView);
            mTvNumber = itemView.findViewById(R.id.item_number);
            mIvImg = itemView.findViewById(R.id.item_poke_img);
            mTvName = itemView.findViewById(R.id.item_name);
            mTvType1 = itemView.findViewById(R.id.item_type1);
            mTvType2 = itemView.findViewById(R.id.item_type2);
            mTvMaxCP = itemView.findViewById(R.id.item_maxcp);
            mTvAttack = itemView.findViewById(R.id.item_attack);
            mTvDefense = itemView.findViewById(R.id.item_defense);
            mTvStamina = itemView.findViewById(R.id.item_stamina);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView mTvNumber;
        TextView mTvName;
        TextView mTvMaxCP;
        TextView mTvAttack;
        TextView mTvDefense;
        TextView mTvStamina;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mTvNumber = itemView.findViewById(R.id.item_head_number);
            mTvName = itemView.findViewById(R.id.item_head_name);
            mTvMaxCP = itemView.findViewById(R.id.item_head_maxcp);
            mTvAttack = itemView.findViewById(R.id.item_head_attack);
            mTvDefense = itemView.findViewById(R.id.item_head_defense);
            mTvStamina = itemView.findViewById(R.id.item_head_stamina);
        }
    }

    public PokemonAdapter(Context context, List<Pokemon> mDataset) {
        this.context = context;
        this.mDataset = mDataset;

        colorMap = new HashMap<>();
        String[] strings = {"Bug", "Dark", "Dragon", "Electric", "Fairy", "Fighting", "Fire", "Flying",
                "Ghost", "Grass", "Ground", "Ice", "Normal", "Poison", "Psychic", "Rock", "Steel", "Water"};
        int[] images = {R.color.type_bug, R.color.type_dark, R.color.type_dragon, R.color.type_electric,
                R.color.type_fairy, R.color.type_fighting, R.color.type_fire, R.color.type_flying,
                R.color.type_ghost, R.color.type_grass, R.color.type_ground, R.color.type_ice,
                R.color.type_normal, R.color.type_poison, R.color.type_psychic, R.color.type_rock,
                R.color.type_steel, R.color.type_water};
        for (int i = 0; i < strings.length; i++) {
            colorMap.put(strings[i], images[i]);
        }


    }

    // Create new views
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
            return new PokemonViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
            return new HeaderViewHolder(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PokemonViewHolder) {
            // position 0 is header
            Pokemon pokemon = mDataset.get(position - 1);
            PokemonViewHolder pokemonViewHolder = (PokemonViewHolder) holder;
            pokemonViewHolder.mTvNumber.setText(String.valueOf(pokemon.getNumber()));
            pokemonViewHolder.mTvName.setText(pokemon.getPokemonId());
            pokemonViewHolder.mTvType1.setText(pokemon.getType());
            setBackgroundColor(pokemonViewHolder.mTvType1, pokemon.getType());
            if (pokemon.getType2().isEmpty()) {
                pokemonViewHolder.mTvType2.setVisibility(View.GONE);
            } else {
                pokemonViewHolder.mTvType2.setVisibility(View.VISIBLE);
                pokemonViewHolder.mTvType2.setText(pokemon.getType2());
                setBackgroundColor(pokemonViewHolder.mTvType2, pokemon.getType2());
            }
            int att = pokemon.getBaseAttack();
            int def = pokemon.getBaseDefense();
            int sta = pokemon.getBaseStamina();

            pokemonViewHolder.mTvAttack.setText(String.valueOf(pokemon.getBaseAttack()));
            pokemonViewHolder.mTvDefense.setText(String.valueOf(pokemon.getBaseDefense()));
            pokemonViewHolder.mTvStamina.setText(String.valueOf(pokemon.getBaseStamina()));

            int maxCP = PokemonUtil.calculateMaxCP(att, def, sta);
            pokemon.setMaxCP(maxCP);
            pokemonViewHolder.mTvMaxCP.setText(String.valueOf(maxCP));

            // 获取图像
            TypedArray typedArray = context.getResources().obtainTypedArray(R.array.pokemons);
            pokemonViewHolder.mIvImg.setImageDrawable(typedArray.getDrawable(pokemon.getNumber() - 1));
            typedArray.recycle();

        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.mTvNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.sort(mDataset, new Comparator<Pokemon>() {
                        @Override
                        public int compare(Pokemon p1, Pokemon p2) {
                            numClick = !numClick;
                            if (numClick) {
                                return Integer.compare(p2.getNumber(), p1.getNumber());
                            } else {
                                return Integer.compare(p1.getNumber(), p2.getNumber());
                            }
                        }
                    });
                    notifyDataSetChanged();
                }
            });
            headerViewHolder.mTvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.sort(mDataset, new Comparator<Pokemon>() {
                        @Override
                        public int compare(Pokemon p1, Pokemon p2) {
                            nameClick = !nameClick;
                            if (nameClick) {
                                return p1.getPokemonId().compareTo(p2.getPokemonId());
                            } else {
                                return p2.getPokemonId().compareTo(p1.getPokemonId());
                            }
                        }
                    });
                    notifyDataSetChanged();
                }
            });
            headerViewHolder.mTvMaxCP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.sort(mDataset, new Comparator<Pokemon>() {
                        @Override
                        public int compare(Pokemon p1, Pokemon p2) {
                            cpClick = !cpClick;
                            if (cpClick) {
                                return Integer.compare(p2.getMaxCP(), p1.getMaxCP());
                            } else {
                                return Integer.compare(p1.getMaxCP(), p2.getMaxCP());
                            }
                        }
                    });
                    notifyDataSetChanged();
                }
            });
            headerViewHolder.mTvAttack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.sort(mDataset, new Comparator<Pokemon>() {
                        @Override
                        public int compare(Pokemon p1, Pokemon p2) {
                            attClick = !attClick;
                            if (attClick) {
                                return Integer.compare(p2.getBaseAttack(), p1.getBaseAttack());
                            } else {
                                return Integer.compare(p1.getBaseAttack(), p2.getBaseAttack());
                            }
                        }
                    });
                    notifyDataSetChanged();
                }
            });
            headerViewHolder.mTvDefense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.sort(mDataset, new Comparator<Pokemon>() {
                        @Override
                        public int compare(Pokemon p1, Pokemon p2) {
                            defClick = !defClick;
                            if (defClick) {
                                return Integer.compare(p2.getBaseDefense(), p1.getBaseDefense());
                            } else {
                                return Integer.compare(p1.getBaseDefense(), p2.getBaseDefense());
                            }
                        }
                    });
                    notifyDataSetChanged();
                }
            });
            headerViewHolder.mTvStamina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.sort(mDataset, new Comparator<Pokemon>() {
                        @Override
                        public int compare(Pokemon p1, Pokemon p2) {
                            staClick = !staClick;
                            if (staClick) {
                                return Integer.compare(p2.getBaseStamina(), p1.getBaseStamina());
                            } else {
                                return Integer.compare(p1.getBaseStamina(), p2.getBaseStamina());
                            }
                        }
                    });
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private void setBackgroundColor(TextView textView, String type) {
        // 改变type颜色
        GradientDrawable gd = (GradientDrawable) textView.getBackground();
        gd.setColor(ContextCompat.getColor(context, colorMap.get(type)));
    }
}
