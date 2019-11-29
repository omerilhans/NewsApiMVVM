package com.omerilhanli.newsapimvvm.mvvm.model

import com.omerilhanli.newsapimvvm.api.Api
import com.omerilhanli.newsapimvvm.api.constant.ApiConst
import com.omerilhanli.newsapimvvm.asistant.extension.dateFormat
import com.omerilhanli.newsapimvvm.asistant.extension.getBefore10DayFromToday
import com.omerilhanli.newsapimvvm.mvvm.contract.IModelNews
import com.omerilhanli.newsapimvvm.mvvm.model.entity.News
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ModelNews @Inject constructor(val api: Api, val apiKey: String) : IModelNews {

    override var textQuery: String = ""

    override var selectedDate: String = ""
        get() {
            return if (field.isEmpty()) {
                //\\ 10 day ago from today
                getDateFrom10DayAgo()?.dateFormat(fromApi = false, forApi = true)!!
            } else {
                field //\\ OR selected day
            }
        }


    override fun getNews(): Observable<News>? {
        return api
            .getNewsOfSelectedDate(textQuery, selectedDate, ApiConst.VALUE_SORT_BY, apiKey)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    private fun getDateFrom10DayAgo(): Date? {
        val calendar = Calendar.getInstance()
        return calendar.getBefore10DayFromToday()
    }
}