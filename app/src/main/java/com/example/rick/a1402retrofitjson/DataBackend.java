package com.example.rick.a1402retrofitjson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataBackend {
    @GET("/posts")
    Call<List<Post>> listPosts();

    @GET("/users")
    Call<List<User>> listUsers();
}
