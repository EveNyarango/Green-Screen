package com.example.greenscreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenscreen.R;
import com.example.greenscreen.adapters.UpdatesArrayAdapter;

import butterknife.BindView;

public class UpdatesActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText)
    TextView mNameTextView;
    @BindView(R.id.listview)
    ListView mListView;

    private String[] name = new String[] {"Eve Nyarango", "Food Bike", "Farasi Lane","Zuchinni Gate", "Liz", "RiriCado","Emile", "Tropikal Heat", "Ilara Dairy", "Blooming Africa"};
    private String[] post = new String[] {"Farming is an act. Do it passionately", "Holla for amazingly yummy meals. We do home delivery any time.@222333444", "Are you for the Horse Riding competition. Visit our website and book a ticket.", "Your health should be a priority. Visit our outlet at Westgate mall and get fresh organic produce.", "Don't I love my new puppy. He is Jeremy Jnr", "The avocado variety on season is hass. Inbox if you have them in your farm", "I lost 90% of my chicks this week from a contagious infection. I feel so dressed to start allover again", "Have used our new spicy coriander noodles in your cooking? Buy from your nearest shopping outlet", "Would you rather have yoghurt or skimmed milk? Choose Wisely.", "Show bundles of joy and care to your loved one this valentines with flowers." };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        mListView = (ListView) findViewById(R.id.listview);
        mNameTextView = (TextView) findViewById(R.id.nameTextView);

        UpdatesArrayAdapter adapter = new UpdatesArrayAdapter(this, android.R.layout.simple_list_item_1, name, post);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String country = ((TextView)view).getText().toString();
                Toast.makeText(UpdatesActivity.this, "Like or leave a comment", Toast.LENGTH_LONG).show();
            }
        });

    }
}