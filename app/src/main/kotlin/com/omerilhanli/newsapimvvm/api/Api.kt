package com.omerilhanli.newsapimvvm.api

import com.omerilhanli.newsapimvvm.api.constant.ApiConst
import com.omerilhanli.newsapimvvm.api.constant.ApiEnd
import com.omerilhanli.newsapimvvm.mvvm.model.entity.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(ApiEnd.END_NEWS)
    fun getNewsOfSelectedDate(
        @Query(ApiConst.KEY_QUERY) query: String,
        @Query(ApiConst.KEY_FROM) from: String,
        @Query(ApiConst.KEY_SORT_BY) sortBy: String,
        @Query(ApiConst.KEY_API) apiKey: String
    ): Observable<News>?

}