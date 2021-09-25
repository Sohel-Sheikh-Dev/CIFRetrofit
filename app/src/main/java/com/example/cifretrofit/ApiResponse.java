package com.example.cifretrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {

    @SerializedName("results")
    public ArrayList<Model> posts;

    public ApiResponse(ArrayList<Model> posts) {
        this.posts = posts;
    }

    public ArrayList<Model>getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Model> posts) {
        this.posts = posts;
    }
}
