package ru.maxmorev.android.fixedtabs.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataBackend {
    @GET("/posts")
    Call<List<Post>> listPosts();

    @GET("/users")
    Call<List<User>> listUsers();
}
