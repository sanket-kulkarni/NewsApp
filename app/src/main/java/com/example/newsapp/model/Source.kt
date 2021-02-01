package com.example.newsapp.model

import android.os.Parcelable
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Source (

	var id : String? ="",
	var name : String? =""
) : Parcelable, RealmObject()