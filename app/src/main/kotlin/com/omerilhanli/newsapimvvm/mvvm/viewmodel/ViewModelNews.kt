package com.omerilhanli.newsapimvvm.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omerilhanli.newsapimvvm.asistant.*
import com.omerilhanli.newsapimvvm.asistant.extension.dateFormat
import com.omerilhanli.newsapimvvm.mvvm.contract.IModelNews
import com.omerilhanli.newsapimvvm.mvvm.model.entity.News
import com.omerilhanli.newsapimvvm.mvvm.model.entity.observable.ViewVisibility
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelNews @Inject constructor(
    val model: IModelNews,
    _progressVisibility: ViewVisibility,
    _listVisibility: ViewVisibility
) : ViewModel() {

    val visibilityProgress = _progressVisibility
    val visibilityList = _listVisibility

    var liveDataNews: MutableLiveData<News> = MutableLiveData()
    var liveDataError: MutableLiveData<String> = MutableLiveData()
    var isNetworkStatus: (() -> Boolean)? = null

    fun passQueryText(query: String) {
        if (query.length >= 2) {
            model.textQuery = query
            triggerRequest()
        } else {
            prepareErrorCase(TEXT_ERR_VALID_QUERY)
        }
    }

    fun passSelectedDate(selectedDate: Date?) {
        model.selectedDate = selectedDate?.dateFormat(fromApi = false, forApi = true)!!
        triggerRequest()
    }

    private fun fetchNews() {
        prepareVisibility(true, visibilityList, visibilityProgress)

        val _disp_ =
            model.getNews()
                ?.subscribe({ news ->
                    if (news?.articles.isNullOrEmpty()) {
                        prepareErrorCase(TEXT_ERR_NOT_FOUND + model.selectedDate)
                    } else {
                        prepareVisibility(false, visibilityProgress)
                    }

                    liveDataNews.postValue(news)

                }, {
                    prepareErrorCase(TEXT_ERR_NOT_FOUND + model.selectedDate)
                })
    }

    private fun triggerRequest() {
        if (isNetworkStatus?.invoke() == true) {
            fetchNews()
        } else {
            prepareErrorCase(TEXT_ERR_NO_NETWORK)
        }
    }

    private fun prepareErrorCase(msg: String) {
        prepareVisibility(false, visibilityList, visibilityProgress)
        liveDataError.postValue(msg)
    }

    private fun prepareVisibility(visible: Boolean, vararg visibilityArgs: ViewVisibility) {
        visibilityArgs.forEach { visibility ->
            visibility.visible = visible
        }
    }
}