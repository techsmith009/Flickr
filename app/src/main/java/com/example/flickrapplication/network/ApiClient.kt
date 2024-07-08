package com.example.flickrapplication.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://www.flickr.com/services/feeds/photos_public.gne/"

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}

object FlickrApiClient {
    val apiService: FlickrApiService by lazy {
        RetrofitClient.retrofit.create(FlickrApiService::class.java)
    }
}