package com.example.newsapp.model
data class BaseResponse (

	val status : String,
	val totalResults : Int,
	val articles : List<Article>
)