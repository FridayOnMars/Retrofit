package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostItem {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private DataStorage data = null;
    @SerializedName("error")
    @Expose
    private String error;

    boolean getStatus() {
        return status;
    }
    DataStorage getData() {
        return data;
    }
    String getError(){
        return error;
    }
}
