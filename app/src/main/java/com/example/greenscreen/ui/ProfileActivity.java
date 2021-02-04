package com.example.greenscreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greenscreen.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.MessageButton)
    Button mMessageButton;
    ImageView ivImageurl;
    TextView tvUsername, tvBio, tvLocation, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mMessageButton.setOnClickListener(this);

        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvLocation = findViewById(R.id.tvLocation);
        tvEmail = findViewById(R.id.tvEmail);

        ivImageurl = findViewById(R.id.ivImageurl);

        String username = getIntent().getStringExtra("username");
        String bio = getIntent().getStringExtra("bio");
        String location = getIntent().getStringExtra("location");
        String email = getIntent().getStringExtra("email");

        tvUsername.setText(username);
        tvBio.setText(bio);
        tvLocation.setText(location);
        tvEmail.setText(email);

        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("imageurl")).into(ivImageurl);
    }

    @Override
    public void onClick(View v) {
if(v == mMessageButton){
    Intent intent = new Intent(ProfileActivity.this, MessageActivity.class);
    startActivity(intent);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    finish();

}
    }
}
