package com.omerilhanli.newsapimvvm.mvvm.model

import com.omerilhanli.newsapimvvm.asistant.extension.date
import com.omerilhanli.newsapimvvm.mvvm.contract.IModelMain
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ModelMain @Inject constructor() : IModelMain {

    override fun setSelectedDateThenGet(year: Int, monthOfYear: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        return calendar.date()
    }
}