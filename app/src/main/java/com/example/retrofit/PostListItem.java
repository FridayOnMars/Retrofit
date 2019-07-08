package com.example.retrofit;


public class PostListItem {
    private int id;
    private String title;
    private long actual_time;
    private String status;
    private String location;
    private String description;
    private PostItemSpecialist specialist;

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
    String getDescription(){
        return description;
    }
    PostItemSpecialist getSpecialist(){
        return specialist;
    }
}
