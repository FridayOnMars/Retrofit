package com.example.retrofit;

public class PostItem {
    private boolean status;
    private PostListItem data = null;
    private String error;

    boolean getStatus() {
        return status;
    }
    PostListItem getData() {
        return data;
    }
    String getError(){
        return error;
    }
}
