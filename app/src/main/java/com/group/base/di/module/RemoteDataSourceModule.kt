package com.group.base.di.module

import com.group.base.network.HomeDataSource
import com.group.core.api.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideHomeDataSource(apiService: ApiService) = HomeDataSource(apiService)
}