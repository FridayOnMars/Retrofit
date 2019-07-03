package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Post {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("actual_time")
    @Expose
    private long actual_time;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("data")
    @Expose
    private List<Post> data;

    int getId(){
        return id;
    }
    String getTitle(){
        return title;
    }
    long getActual_time(){
        return actual_time;
    }
    String getStatus(){
        return status;
    }
    String getLocation(){
        return location;
    }
    List<Post> getData(){
        return data;
    }
}
