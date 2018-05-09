package com.zainco.eat_it.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.zainco.eat_it.FoodDetailActivity;
import com.zainco.eat_it.FoodListActivity;
import com.zainco.eat_it.Interface.ItemClickListener;
import com.zainco.eat_it.R;
import com.zainco.eat_it.model.Food;
import com.zainco.eat_it.model.Food;
import com.zainco.eat_it.view_holder.FoodViewHolder;
import com.zainco.eat_it.view_holder.MenuViewHolder;

import java.util.ArrayList;

public class FoodsAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    ArrayList<Food> foods;
    Context context;

    public FoodsAdapter(Context context, ArrayList<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        FoodViewHolder foodViewHolder = new FoodViewHolder(v);
        return foodViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final Food Food = foods.get(position);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onCLick(View view, int position, boolean isLongClick) {
                context.startActivity(new Intent(context, FoodDetailActivity.class));
            }
        });
        holder.food_name.setText(foods.get(position).getName());
        Picasso.with(context).load(foods.get(position).getImage()).into(holder.food_image);

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


}
