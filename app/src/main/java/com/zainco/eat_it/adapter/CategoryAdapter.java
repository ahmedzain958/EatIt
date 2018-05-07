package com.zainco.eat_it.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zainco.eat_it.FoodListActivity;
import com.zainco.eat_it.Interface.ItemClickListener;
import com.zainco.eat_it.R;
import com.zainco.eat_it.model.Category;
import com.zainco.eat_it.view_holder.MenuViewHolder;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<MenuViewHolder> {
    ArrayList<Category> categories;
    Context context;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(v);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        final Category category = categories.get(position);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onCLick(View view, int position, boolean isLongClick) {
                Log.d("TAGG", category.getId());
                context.startActivity(new Intent(context, FoodListActivity.class).putExtra("CategoryId", category.getId()));
            }
        });
        holder.menu_name.setText(categories.get(position).getName());
        Picasso.with(context).load(categories.get(position).getImage()).into(holder.menu_image);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}
