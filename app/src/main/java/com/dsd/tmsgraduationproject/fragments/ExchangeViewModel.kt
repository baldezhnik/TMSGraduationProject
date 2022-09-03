package com.dsd.tmsgraduationproject.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsd.tmsgraduationproject.retrofit.NetworkInterface
import com.dsd.tmsgraduationproject.retrofit.Rate
import kotlinx.coroutines.launch

class ExchangeViewModel : ViewModel() {

    val ratesLiveData = MutableLiveData<Rate>()

    fun getAllRates(){
        viewModelScope.launch{
            val retrofit = NetworkInterface.getInstance()
            val responce = retrofit.getAllMovies()
            if (responce.isSuccessful) {
                ratesLiveData.postValue(responce.body())
            }
        }
    }
}