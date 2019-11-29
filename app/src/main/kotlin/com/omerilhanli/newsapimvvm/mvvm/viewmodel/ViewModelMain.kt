package com.omerilhanli.newsapimvvm.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.omerilhanli.newsapimvvm.mvvm.contract.IModelMain
import com.omerilhanli.newsapimvvm.mvvm.model.entity.DeclarativeDate
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelMain @Inject constructor(
    val model: IModelMain
) : ViewModel() {

    fun prepareAndGetSelectedDate(year: Int, monthOfYear: Int, dayOfMonth: Int): Date? {
        return model.setSelectedDateThenGet(year, monthOfYear, dayOfMonth)
    }

    fun getDeclarativeDate(): DeclarativeDate {
        val calendar = Calendar.getInstance()
        return DeclarativeDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

}