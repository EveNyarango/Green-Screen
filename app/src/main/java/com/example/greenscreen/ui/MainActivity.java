package com.example.greenscreen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.greenscreen.R;
import com.example.greenscreen.adapters.ProfileAdapter;
import com.example.greenscreen.models.Green;
import com.example.greenscreen.network.GreenApi;
import com.example.greenscreen.network.GreenService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                profileAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                profileAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search);
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}

