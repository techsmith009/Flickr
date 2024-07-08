package com.example.flickrapplication.model

 /**
 * A sealed class to wrap a response object to return either a success, or error object.
 */
sealed class ServiceResult<R> {
    data class Success<T>(val data: T) : ServiceResult<T>()
    data class Error(val exception: Exception) : ServiceResult<Nothing>()
}
