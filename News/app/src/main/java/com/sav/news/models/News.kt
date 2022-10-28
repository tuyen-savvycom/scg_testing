package com.sav.news.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val content: String?,
    val publishedAt: String?,
) : Parcelable