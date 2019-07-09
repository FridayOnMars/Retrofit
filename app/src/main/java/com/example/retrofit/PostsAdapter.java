package com.example.retrofit;

class PostsAdapter {
    private int id;
    private long actualTime;
    private String title, status, location;

    PostsAdapter(int id, String title, long actualTime, String status, String location){
        this.id = id;
        this.title = title;
        this.actualTime = actualTime;
        this.status = status;
        this.location = location;
    }

    int getId(){
        return id;
    }
    String getTitle(){
        return title;
    }
    long getActualTime(){
        return actualTime;
    }
    String getStatus(){
        return status;
    }
    String getLocation(){
        return location;
    }
}
