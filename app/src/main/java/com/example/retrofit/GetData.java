package com.example.retrofit;

class GetData {
    private int id;
    private String title, status, location, actualTime;

    GetData(int id, String title, String actualTime, String status, String location){
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
    String getActualTime(){
        return actualTime;
    }
    String getStatus(){
        return status;
    }
    String getLocation(){
        return location;
    }
}
