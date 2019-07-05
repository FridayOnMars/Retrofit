package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/test/{id}.json")
    public Call<PostItem> getPostWithID(@Path("id") int id);

    @GET("/test/list.json")
    Call<Post> getAllList();
}
