package com.zainco.eat_it;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.zainco.eat_it.model.Food;

public class FoodDetailActivity extends AppCompatActivity {
    TextView food_name, food_price, food_description;
    ImageView img_food;

    FirebaseDatabase database;
    DatabaseReference reference;
    private ElegantNumberButton numberButton;
    private FloatingActionButton btnCart;
    private CollapsingToolbarLayout collapsing;
    private String foodId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Foods");

        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);
        food_description = findViewById(R.id.food_description);
        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        img_food = findViewById(R.id.img_food);
        collapsing = findViewById(R.id.collapsing);

        collapsing.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if (getIntent() != null) {
            foodId = getIntent().getStringExtra("FoodId");
            if (foodId != null && !foodId.isEmpty()) {
                getFoodDetail(foodId);
            }
        }


    }

    private void getFoodDetail(final String foodId) {
        reference.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);
                food_description.setText(food.getDescription());
                food_name.setText(food.getName());
                food_price.setText(food.getPrice());
                Picasso.with(FoodDetailActivity.this).load(food.getImage()).into(img_food);
                collapsing.setTitle(food.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
