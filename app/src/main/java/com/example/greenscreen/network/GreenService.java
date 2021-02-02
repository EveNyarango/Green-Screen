package com.example.greenscreen.network;

import com.example.greenscreen.models.Green;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GreenService {

    @GET("profile")
    Call<List<Green>> getProfile();

    @GET("profile/{profile}")
    Call<Green> getProfile(
            @Path("profile") String profile
    );
}
