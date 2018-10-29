package com.example.skywish.pokegodex.widgets;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;

import com.example.skywish.pokegodex.models.Pokemon;
import com.example.skywish.pokegodex.utilities.Constants;

public class PokemonSortedCallback extends SortedListAdapterCallback<Pokemon> {

    public PokemonSortedCallback(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    private Constants.PokemonSortType sortType = Constants.PokemonSortType.NUMBER;


    @Override
    public int compare(Pokemon o1, Pokemon o2) {
        switch (sortType) {
            case NUMBER:
                // 从小到大
                return Integer.compare(o1.getNumber(), o2.getNumber());
            case NAME:
                return o1.getPokemonId().compareTo(o2.getPokemonId());
            case MAXCP:
                return Integer.compare(o1.getMaxcp(), o2.getMaxcp());
            case ATTACK:
                return Integer.compare(o1.getBaseAttack(), o2.getBaseAttack());
            case DEFENSE:
                return Integer.compare(o1.getBaseDefense(), o2.getBaseDefense());
            case STAMINA:
                return Integer.compare(o1.getBaseStamina(), o2.getBaseStamina());
        }
        throw new RuntimeException("there is no type that matches the sorttype " + sortType + " + make sure your using types correctly");
    }

    @Override
    public boolean areContentsTheSame(Pokemon oldItem, Pokemon newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areItemsTheSame(Pokemon item1, Pokemon item2) {
        return item1.getPokemonId().equals(item2.getPokemonId());
    }

    public void setSortType(Constants.PokemonSortType type) {
        this.sortType = type;
    }
}
