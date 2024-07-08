package com.example.flickrapplication.repository

import com.example.flickrapplication.model.FlickrContents
import com.example.flickrapplication.model.ServiceResult
import com.example.flickrapplication.network.FlickrApiClient
import com.example.flickrapplication.network.RetrofitCallHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrRepositoryImpl : FlickrRepository {
    override suspend fun getFlickrContent(searchData : String): FlickrContents? {
        val result = withContext(Dispatchers.IO) {
            RetrofitCallHandler.processCall {
                FlickrApiClient.apiService.getFlickrContents(searchData)
            }
        }

        return when (result) {
            is ServiceResult.Success -> result.data
            else -> null
        }
    }
}