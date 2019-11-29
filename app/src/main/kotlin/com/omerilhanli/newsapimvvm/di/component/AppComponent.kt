package com.omerilhanli.newsapimvvm.di.component

import com.omerilhanli.newsapimvvm.mvvm.view.activity.MainActivity
import com.omerilhanli.newsapimvvm.di.module.ModuleApi
import com.omerilhanli.newsapimvvm.di.module.ModuleModel
import com.omerilhanli.newsapimvvm.di.module.ModuleViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ModuleApi::class, ModuleViewModel::class, ModuleModel::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}