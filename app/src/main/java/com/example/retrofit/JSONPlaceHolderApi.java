package com.example.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/test/{id}.json")
    Call<PostItem> getPostWithID(@Path("id") int id);

    @GET("/test/list.json")
    Call<PostList> getAllList();
}
