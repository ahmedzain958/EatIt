package com.zainco.eat_it;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.signUp)
    Button signUp;
    @BindView(R.id.signIn)
    Button signIn;
    @BindView(R.id.txtSlogan)
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
        FirebaseApp.initializeApp(this);
        txtSlogan.setTypeface(typeface);
        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUp:
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                break;
            case R.id.signIn:
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                break;
        }
    }
}
