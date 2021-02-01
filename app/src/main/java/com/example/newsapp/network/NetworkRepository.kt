package com.example.newsapp.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.BaseResponse
import com.example.newsapp.model.WeatherBaseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var networkAPI: ApiInterface

class NetworkRepository {

    constructor() {
        networkAPI = ApiClient.client!!.create(ApiInterface::class.java)
    }

    companion object {

        private var newsRepository: NetworkRepository? = null

        val instance: NetworkRepository
            get() {
                if (newsRepository == null) {
                    newsRepository = NetworkRepository()
                }
                return newsRepository!!
            }
    }

    fun getList(): Response<BaseResponse> {
        var response: Response<BaseResponse> = networkAPI.getList().execute()
        return response
    }
    fun getWeatherData(url:String):Response<WeatherBaseData>{
        var response= networkAPI.getWeatherData(url).execute()
        return response
    }
}