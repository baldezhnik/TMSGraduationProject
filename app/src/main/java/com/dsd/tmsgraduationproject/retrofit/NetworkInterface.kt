package com.dsd.tmsgraduationproject.retrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface NetworkInterface {
    @GET("rates?periodicity=0")
    suspend fun getAllMovies() : Response<Rate>
    companion object {
        private var retrofitService: NetworkInterface? = null
        private const val BASE_URL = "https://www.nbrb.by/api/exrates/"
        fun getInstance() : NetworkInterface{
            if (retrofitService==null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create()
            }
            return retrofitService!!
        }
    }
}