package com.example.newsapp

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.model.WeatherBaseData
import com.example.newsapp.network.NetworkRepository
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel()
{
    var pageTitle =
        arrayOf("MOVIES", "FASHION", "MODELS")
    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState>
        get() = _loading

    private val _data = MutableLiveData<WeatherBaseData>()
    val data: LiveData<WeatherBaseData>
        get() = _data

    init {
        fetchData()
    }

    fun clearBookMarks(){
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            realm.where(Article::class.java).findAll().deleteAllFromRealm()
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(LoadingState.LOADING)
                val response = NetworkRepository.instance.getWeatherData("https://api.openweathermap.org/data/2.5/weather?q=Bengaluru,india&units=metric&APPID=f14c0b2c33590b7f60dc98ed0b8e76e0")
                if (response.isSuccessful) {
                    if(response.body()!=null) {
                        _data.postValue(response.body()!!)
                        _loading.postValue(LoadingState.LOADED)
                    }
                    else
                    {
                        _loading.postValue(LoadingState.error("No Data Found!!"))
                    }
                } else {
                    _loading.postValue(LoadingState.error(response.message()))
                }

            } catch (e: Exception) {
                _loading.postValue(LoadingState.error(e.message))
            }
        }
    }
}