package com.example.newsapp.model

import android.os.Parcelable
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Article(
    var source: Source? =null,
    var author: String? ="",
    var title: String? ="",
    var description: String? ="",
    var url: String? ="",
    var urlToImage: String? ="",
    var publishedAt: String? ="",
    var content: String? =""
) : Parcelable, RealmObject()
{

}