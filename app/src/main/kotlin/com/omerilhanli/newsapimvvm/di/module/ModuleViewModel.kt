package com.omerilhanli.newsapimvvm.di.module

import androidx.lifecycle.ViewModel

import com.omerilhanli.newsapimvvm.di.factory.ViewModelKey
import com.omerilhanli.newsapimvvm.mvvm.viewmodel.ViewModelMain
import com.omerilhanli.newsapimvvm.mvvm.viewmodel.ViewModelNews

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ModuleViewModel {

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelMain::class)
    abstract fun bindViewModelMain(viewModelMain: ViewModelMain): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelNews::class)
    abstract fun bindViewModelNews(viewModelNews: ViewModelNews): ViewModel
}
