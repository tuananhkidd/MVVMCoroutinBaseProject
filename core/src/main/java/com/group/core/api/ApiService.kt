package com.group.core.api

import com.group.core.models.Test
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getTodo(): Response<List<Test>>

    @GET("/posts4/1")
    suspend fun getDetailTodo(): Response<Test>
}