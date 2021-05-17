package com.group.core.api

import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>) : BaseResult<T> {
        try {
            val response = call()
            if(response.isSuccessful){
                val body = response.body()
                return BaseResult.success(body)
            }
            return error(response.message())

        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): BaseResult<T> {
        return BaseResult.error(message)
    }
}