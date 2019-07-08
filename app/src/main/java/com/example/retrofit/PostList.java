package com.example.retrofit;

import java.util.List;

class PostList {
    private boolean status;
    private List<PostListItem> data = null;

    boolean getStatus() {
        return status;
    }
    List<PostListItem> getData() {
        return data;
    }
}
