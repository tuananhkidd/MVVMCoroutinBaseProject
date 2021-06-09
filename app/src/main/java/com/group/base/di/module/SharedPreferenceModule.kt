package com.group.base.di.module

import android.content.Context
import com.group.base.di.SharedPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferenceModule {
    @Singleton
    @Provides
    fun provideSharePreference(context: Context) = SharedPreference.getInstance(context)
}