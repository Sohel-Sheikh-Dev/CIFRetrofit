package com.example.cifretrofit;

import static com.example.cifretrofit.Constants.BASE_API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("top-headlines?country=de&category=business&apiKey="+BASE_API)
    Call<ApiResponse> getPosts();

}
