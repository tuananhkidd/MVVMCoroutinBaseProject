package com.group.base

import com.group.base.di.inject.AppInjector
import com.group.core.base.BaseApplication

class App : BaseApplication() {
    override fun initDagger() {
        AppInjector.init(this)
    }
}