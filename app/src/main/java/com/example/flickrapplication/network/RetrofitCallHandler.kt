package com.example.flickrapplication.network


import android.util.Log
import com.example.flickrapplication.model.ServiceResult
import retrofit2.Response

object RetrofitCallHandler {

    suspend fun <T> processCall(
        call: suspend () -> Response<T>
    ) : ServiceResult<out T & Any> {

        return try {
            val serviceCallback = call()
            val body = serviceCallback.body()
            if (serviceCallback.isSuccessful && body != null) {
                ServiceResult.Success(body)
            } else {
                Log.d(RetrofitCallHandler.javaClass.name, "${serviceCallback.code()} ${serviceCallback.errorBody().toString()}" )
                return ServiceResult.Error(Exception())
            }

        } catch (exception: Exception) {
            Log.d( RetrofitCallHandler.javaClass.name, exception.message.orEmpty())
            return ServiceResult.Error(exception)
        }
    }
}
