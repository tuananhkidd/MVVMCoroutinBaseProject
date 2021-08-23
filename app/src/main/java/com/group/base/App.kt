package com.group.base

import android.app.Application
import com.group.core.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App @Inject constructor(): Application() {

    override fun onCreate() {
        super.onCreate()
    }
}