package com.zainco.eat_it.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zainco.eat_it.Interface.ItemClickListener;
import com.zainco.eat_it.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView menu_name;
    public ImageView menu_image;
    ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
        menu_image = itemView.findViewById(R.id.menu_image);
        menu_name = itemView.findViewById(R.id.menu_name);
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
