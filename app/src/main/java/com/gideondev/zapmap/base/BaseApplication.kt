package com.gideondev.zapmap.base

import android.app.Application
import com.gideondev.zapmap.di.*
import com.gideondev.zapmap.utils.BASE_URL
class BaseApplication : Application() {
     lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = this.initDagger()
    }

    protected open fun initDagger(): AppComponent
            = DaggerAppComponent.builder()
        .networkModule(NetworkModule(BASE_URL))
        .repositoryModule(RepositoryModule())
        .rxJavaModule(RxJavaModule())
        .build()
}