package com.example.newsapp.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.LoadingState
import com.example.newsapp.model.Article
import com.example.newsapp.network.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState>
        get() = _loading

    private val _data = MutableLiveData<List<Article>>()
    val data: LiveData<List<Article>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(LoadingState.LOADING)
                val response = NetworkRepository.instance.getList()
                if (response.isSuccessful) {
                    if(response.body()!=null && response.body()!!.articles.isNotEmpty()) {
                        _data.postValue(response.body()!!.articles)
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