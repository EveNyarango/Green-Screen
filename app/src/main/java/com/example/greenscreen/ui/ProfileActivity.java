package com.example.greenscreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greenscreen.R;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    ImageView ivImageurl;
    TextView tvUsername, tvBio, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvEmail = findViewById(R.id.tvEmail);

        ivImageurl = findViewById(R.id.ivImageurl);

        String username = getIntent().getStringExtra("username");
        String bio = getIntent().getStringExtra("bio");
        String email = getIntent().getStringExtra("email");

        tvUsername.setText(username);
        tvBio.setText(bio);
        tvEmail.setText(email);

        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("imageurl")).into(ivImageurl);
    }
}
