package com.example.greenscreen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.greenscreen.R;
import com.example.greenscreen.adapters.ProfileAdapter;
import com.example.greenscreen.models.Green;
import com.example.greenscreen.network.GreenApi;
import com.example.greenscreen.network.GreenService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    private SearchView searchView;
    private RecyclerView recyclerView;
    private ProfileAdapter profileAdapter;
    private List<Green> profileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvUsername);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileAdapter = new ProfileAdapter();
        recyclerView.setAdapter(profileAdapter);

        profileList = new ArrayList<>();

        GreenService greenService = GreenApi.getRetrofitInstance().create(GreenService.class);

        Call<List<Green>> call = greenService.getProfile();
        call.enqueue(new Callback<List<Green>>() {
            @Override
            public void onResponse(Call<List<Green>> call, Response<List<Green>> response) {
                profileList = response.body();

                if (profileList != null) {
                    for(Green green : profileList) {
                        System.out.println("Username: " + green.getUsername() + " Bio: " + green.getBio() + "\n" );

                        profileAdapter.setProfileList(getApplicationContext(), profileList);
                    }
                }


            }

            @Override
            public void onFailure(Call<List<Green>> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });

    }
}