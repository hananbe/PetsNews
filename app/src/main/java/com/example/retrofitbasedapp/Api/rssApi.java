package com.example.retrofitbasedapp.api;

import com.example.retrofitbasedapp.Services.Rss.Rss;

import retrofit2.http.GET;

public interface rssApi {
    @GET("rss/search?q=בעלי+חיים&hl=he&gl=IL&ceid=IL:he")
    Rss getRss();
}