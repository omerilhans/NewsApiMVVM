package com.omerilhanli.newsapimvvm.di.module

import com.omerilhanli.newsapimvvm.mvvm.contract.IModelMain
import com.omerilhanli.newsapimvvm.mvvm.contract.IModelNews
import com.omerilhanli.newsapimvvm.mvvm.model.ModelMain
import com.omerilhanli.newsapimvvm.mvvm.model.ModelNews
import dagger.Binds
import dagger.Module

@Module
abstract class ModuleModel {

    @Binds
    abstract fun bindModelMain(modelMain: ModelMain): IModelMain

    @Binds
    abstract fun bindModelNews(modelMain: ModelNews): IModelNews
}