package com.example.greenscreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.greenscreen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.SendButton)
    Button mSendButton;
    @BindView(R.id.MessageEditText)
    EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        mSendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
if(v == mSendButton) {
    String message = mMessageEditText.getText().toString();
    Intent intent = new Intent(MessageActivity.this, MainActivity.class);
    intent.putExtra("message", message);
    startActivity(intent);
}
    }
}