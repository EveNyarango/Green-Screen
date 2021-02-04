package com.example.greenscreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.greenscreen.R;

public class DisplayActivity extends AppCompatActivity {
    TextView tvName, tvPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tvName = findViewById(R.id.tvName);
        tvPost = findViewById(R.id.tvPost);

        String name = getIntent().getStringExtra("name");
        String post = getIntent().getStringExtra("post");

        tvName.setText(name);
        tvPost.setText(post);
    }
}