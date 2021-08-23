package com.group.base.di.module

import android.content.Context
import com.group.base.di.SharedPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {
    @Singleton
    @Provides
    fun provideSharePreference(context: Context) = SharedPreference.getInstance(context)
}