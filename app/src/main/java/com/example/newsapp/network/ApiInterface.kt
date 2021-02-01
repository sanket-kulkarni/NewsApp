package com.example.newsapp.network

import com.example.newsapp.model.BaseResponse
import com.example.newsapp.model.WeatherBaseData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiInterface {

    @GET("top-headlines?country=us&apiKey=f5561ca331204da3af790fb44dd43d39")
    fun getList(): Call<BaseResponse>

    @GET
    fun getWeatherData(@Url url: String?): Call<WeatherBaseData>

}