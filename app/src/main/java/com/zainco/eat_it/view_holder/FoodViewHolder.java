package com.zainco.eat_it.view_holder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zainco.eat_it.Interface.ItemClickListener;
import com.zainco.eat_it.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView food_image;
    public TextView food_name;

    ItemClickListener itemClickListener;

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_image = itemView.findViewById(R.id.food_image);
        food_name = itemView.findViewById(R.id.food_name);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onCLick(v, getAdapterPosition(), false);
    }
}
