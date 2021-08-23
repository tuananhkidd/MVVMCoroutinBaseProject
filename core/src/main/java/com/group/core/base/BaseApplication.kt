package com.group.core.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner

abstract class BaseApplication : Application() ,LifecycleObserver{


    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        initDagger()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }


    abstract fun initDagger()


}
