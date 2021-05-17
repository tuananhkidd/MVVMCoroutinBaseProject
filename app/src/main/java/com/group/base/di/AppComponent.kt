package com.group.base.di

import android.app.Application
import com.group.base.App
import com.group.base.di.module.ActivityModule
import com.group.base.di.module.AppModule
import com.group.core.di.module.SharedPreferenceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        SharedPreferenceModule::class]
)
interface AppComponent {
    fun inject(baseApplication: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}