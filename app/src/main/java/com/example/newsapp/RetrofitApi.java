package com.example.newsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitApi {

    @GET
    Call<NewsModel> allNews(@Url String url);

    @GET
    Call<NewsModel> categoryNews(@Url String url);

}
