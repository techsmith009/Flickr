package com.example.flickrapplication.network

import com.example.flickrapplication.model.FlickrContents
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {

    @GET(".")
    suspend fun getFlickrContents(@Query("tags") tags: String,
                                  @Query("format") format: String = "json",
                                  @Query("nojsoncallback") noJsonCallBack : Int = 1): Response<FlickrContents>
}