package com.omerilhanli.newsapimvvm.asistant.extension

import com.omerilhanli.newsapimvvm.asistant.*
import com.omerilhanli.newsapimvvm.mvvm.model.entity.Article
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun String?.dateFormat(fromApi: Boolean, forApi: Boolean): String? {

    val patternActual = if (fromApi) FROM_API_PATTERN else FROM_CALENDAR_PATTERN
    val patternExpected = if (forApi) FOR_API_PATTERN else FOR_VIEW_PATTERN

    var txtFormattedDate: String? = this
    try {
        val formatterActual = SimpleDateFormat(patternActual, Locale.US)
        val formatterExpected = SimpleDateFormat(patternExpected, Locale.US)
        val date = formatterActual.parse(this!!)
        txtFormattedDate = formatterExpected.format(date!!)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return txtFormattedDate
}

fun Calendar.getBefore10DayFromToday(): Date {
    this.add(Calendar.DATE, -10) // * for before 10 days ago
    return this.time
}

fun Date.dateFormat(fromApi: Boolean, forApi: Boolean): String? {
    return this.toString().dateFormat(fromApi = fromApi, forApi = forApi)
}

fun Calendar.date(): Date {
    return this.time
}

fun List<Article>?.sortForDate(): List<Article> {
    val listSortedDate: ArrayList<String> = ArrayList()
    this?.forEach { article ->
        val formattedDateText = article.publishedAt
        listSortedDate.add(formattedDateText!!)
    }

    Collections.sort(listSortedDate, StringDateComparator())

    val listSortedArticle: ArrayList<Article> = ArrayList()

    listSortedDate.forEach { date ->
        this?.forEach { article ->
            val formattedPublishedAt = article.publishedAt
            if (date == formattedPublishedAt) {
                listSortedArticle.add(article)
            }
        }
    }

    return listSortedArticle
}

private class StringDateComparator : Comparator<String> {
    var dateFormat = SimpleDateFormat(FROM_API_PATTERN, Locale.US)
    override fun compare(lhs: String, rhs: String): Int {
        return dateFormat.parse(lhs).compareTo(dateFormat.parse(rhs))
    }
}