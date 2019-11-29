package com.omerilhanli.newsapimvvm.mvvm.contract

import java.util.*

interface IModelMain {

    fun setSelectedDateThenGet(year: Int, monthOfYear: Int, dayOfMonth: Int): Date
}