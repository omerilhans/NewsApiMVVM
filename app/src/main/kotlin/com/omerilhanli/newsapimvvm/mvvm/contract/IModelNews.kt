package com.omerilhanli.newsapimvvm.mvvm.contract

import com.omerilhanli.newsapimvvm.mvvm.model.entity.News
import io.reactivex.Observable

interface IModelNews {

    var textQuery: String
    var selectedDate: String
    fun getNews(): Observable<News>?
}