package com.sav.news.utils.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val dateFormatSource = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
val dateFormatDestination = SimpleDateFormat("MMM dd, HH:mm", Locale.US)

fun String.formatDateTime(): String {
    try {
        return dateFormatDestination.format(dateFormatSource.parse(this)!!)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return this
}