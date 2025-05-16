package com.example.retrofit_tp.network;

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.retrofit_tp.model.User;

import java.util.List;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers();
}
