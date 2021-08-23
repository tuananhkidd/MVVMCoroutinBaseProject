package com.group.base.di.module

import com.group.base.network.HomeDataSource
import com.group.base.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideHomeDataSource(apiService: ApiService) = HomeDataSource(apiService)
}