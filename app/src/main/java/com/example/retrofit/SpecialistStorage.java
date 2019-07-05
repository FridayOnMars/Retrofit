package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialistStorage {
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;

    String getFirstName(){
        return firstName;
    }
    String getLastName(){
        return lastName;
    }
}
