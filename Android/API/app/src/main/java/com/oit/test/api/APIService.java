package com.oit.test.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("/addUser")
    Call<Post> savePost(@Body Post model);

    @GET("/users")
    Call<List<Post>> getMyJSON();
}