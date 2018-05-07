package com.zainco.eat_it;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.zainco.eat_it.Interface.IFoods;
import com.zainco.eat_it.adapter.CategoryAdapter;
import com.zainco.eat_it.adapter.FoodsAdapter;
import com.zainco.eat_it.model.Category;
import com.zainco.eat_it.model.Food;
import com.zainco.eat_it.view_holder.FoodViewHolder;

import java.util.ArrayList;
import java.util.Map;

public class FoodListActivity extends AppCompatActivity implements IFoods {

    private String categoryId;

    RecyclerView recycler_food;
    RecyclerView.LayoutManager linearLayoutManager;
    static ArrayList<Food> foods;

    FirebaseDatabase database;
    DatabaseReference reference;
    IFoods iFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        recycler_food = findViewById(R.id.recycler_food);
        recycler_food.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recycler_food.setLayoutManager(linearLayoutManager);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Foods");
        if (getIntent() != null) {
            categoryId = getIntent().getStringExtra("CategoryId");
        }
        if (categoryId != null && !categoryId.isEmpty()) {
            loadListFood(categoryId);
        }
        iFoods = this;
    }

    private void loadListFood(final String categoryId) {
        Query query = reference.orderByChild("MenuId").equalTo(categoryId);
        foods = new ArrayList<>();

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Food food = dataSnapshot.getValue(Food.class);
                foods.add(food);
                iFoods.getFoods(foods);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void getFoods(ArrayList<Food> foods) {
        if (foods != null && foods.size() > 0) {
            FoodsAdapter categoryAdapter = new FoodsAdapter(FoodListActivity.this, foods);
            recycler_food.setAdapter(categoryAdapter);
        }
    }
}
