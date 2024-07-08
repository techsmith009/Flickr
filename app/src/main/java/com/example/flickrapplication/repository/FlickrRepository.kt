package com.example.flickrapplication.repository

import com.example.flickrapplication.model.FlickrContents

interface FlickrRepository {

    suspend fun getFlickrContent(searchData : String = "") : FlickrContents?

    companion object {
        fun getFlickrRepo() = FlickrRepositoryImpl()
    }
}