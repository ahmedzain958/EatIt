package com.zainco.eat_it;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.zainco.eat_it.Interface.ItemClickListener;
import com.zainco.eat_it.adapter.CategoryAdapter;
import com.zainco.eat_it.common.Common;
import com.zainco.eat_it.model.Category;
import com.zainco.eat_it.view_holder.MenuViewHolder;

import java.util.ArrayList;
import java.util.Map;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerView recyclerViewMenu;
    LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Category");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        TextView txtFullName = headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentUser.getName());

        //load menu
        recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        recyclerViewMenu.setHasFixedSize(true);//RV size will never change even if any optimizations (item remove or item add)took place in it
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewMenu.setLayoutManager(linearLayoutManager);
        loadMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void loadMenu() {
     /*   databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() != 0) {
                    collectCategories((Map<String, Object>) dataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAGGGG", "onCancelled", databaseError.toException());
            }
        });
*/
        adapter =
                new FirebaseRecyclerAdapter<Category, MenuViewHolder>(
                        Category.class, R.layout.menu_item, MenuViewHolder.class, databaseReference) {
                    @Override
                    protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                        viewHolder.menu_name.setText(model.getName());
                        Picasso.with(HomeActivity.this).load(model.getImage()).into(viewHolder.menu_image);
                        final Category s = model;
                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onCLick(View view, int position, boolean isLongClick) {
                                startActivity(new Intent(HomeActivity.this, FoodListActivity.class)
                                        .putExtra("CategoryId", adapter.getRef(position).getKey()));
                            }
                        });
                    }
                };
        recyclerViewMenu.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void collectCategories(Map<String, Object> categories) {

       /* ArrayList<Category> categories1 = new ArrayList<>();
        for (Map.Entry<String, Object> entry : categories.entrySet()) {
            Map singleUser = (Map) entry.getValue();
            categories1.add(new Category((String) singleUser.get("Image"), (String) singleUser.get("Name"), entry.getKey().toString()));
        }
        if (categories1 != null && categories.size() > 0) {
            CategoryAdapter categoryAdapter = new CategoryAdapter(HomeActivity.this, categories1);
            recyclerViewMenu.setAdapter(categoryAdapter);
        }*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            // Handle the camera action
            //
            //
        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_log_out) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
