package com.dsd.tmsgraduationproject.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsd.tmsgraduationproject.retrofit.Movie
import com.dsd.tmsgraduationproject.retrofit.NetworkInterface
import kotlinx.coroutines.launch

class ExchangeViewModel : ViewModel() {

    val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getAllMovie(){
        viewModelScope.launch{
            val retrofit = NetworkInterface.getInstance()
            val responce = retrofit.getAllMovies()
            if (responce.isSuccessful) {
                moviesLiveData.postValue(responce.body())
            }
        }
    }
}