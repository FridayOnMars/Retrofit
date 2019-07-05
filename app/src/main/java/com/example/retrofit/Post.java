package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Post {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private List<DataStorage> data = null;

    boolean getStatus() {
        return status;
    }
    List<DataStorage> getData() {
        return data;
    }
}
