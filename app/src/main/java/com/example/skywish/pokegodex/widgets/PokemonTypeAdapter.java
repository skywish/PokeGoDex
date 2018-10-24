package com.example.skywish.pokegodex.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skywish.pokegodex.R;

public class PokemonTypeAdapter extends BaseAdapter {
    private Context mContext;

    public PokemonTypeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mTypeImage.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        final ViewHolder viewHolder;
        if (view == null) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_type_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = itemView.findViewById(R.id.item_type_img);
            viewHolder.textView = itemView.findViewById(R.id.item_type_text);
            itemView.setTag(viewHolder);
        } else {
            itemView = view;
            viewHolder = (ViewHolder) itemView.getTag();
        }

        viewHolder.imageView.setImageResource(mTypeImage[i]);
        viewHolder.textView.setText(mTypeName[i]);

        return itemView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }


    private Integer[] mTypeImage = {R.drawable.bug, R.drawable.dark, R.drawable.dragon,
            R.drawable.electric, R.drawable.fairy, R.drawable.fighting, R.drawable.fire,
            R.drawable.flying, R.drawable.ghost, R.drawable.grass, R.drawable.ground,
            R.drawable.ice, R.drawable.normal, R.drawable.poison, R.drawable.psychic,
            R.drawable.rock, R.drawable.steel, R.drawable.water};

    private Integer[] mTypeName = {R.string.type_bug, R.string.type_dark, R.string.type_dragon,
            R.string.type_electric, R.string.type_fairy, R.string.type_fighting, R.string.type_fire,
            R.string.type_flying, R.string.type_ghost, R.string.type_grass, R.string.type_ground,
            R.string.type_ice, R.string.type_normal, R.string.type_poison, R.string.type_psychic,
            R.string.type_rock, R.string.type_steel, R.string.type_water};
}
