package com.group.base.network

import com.group.core.api.ApiService
import com.group.core.api.BaseDataSource
import javax.inject.Inject

class HomeDataSource  @Inject constructor(private val apiService: ApiService) :  BaseDataSource() {
    suspend fun getTodo() = getResult { apiService.getTodo() }

    suspend fun getDetailTodo() = getResult { apiService.getDetailTodo() }
}