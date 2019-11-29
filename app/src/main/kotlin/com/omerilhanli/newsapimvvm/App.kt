package com.omerilhanli.newsapimvvm

import android.app.Application
import com.omerilhanli.newsapimvvm.di.component.AppComponent
import com.omerilhanli.newsapimvvm.di.component.DaggerAppComponent

class App : Application() {

    var component: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
    }
}