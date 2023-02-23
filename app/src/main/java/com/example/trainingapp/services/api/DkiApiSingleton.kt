package com.example.trainingapp.services.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DkiApiSingleton {
    private const val baseUrl = "https://apis-dev.danakini.co.id/mobile/v1/";
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
    fun resCall(): IDkiApiService {
        return DkiApiSingleton.getInstance().create(IDkiApiService::class.java)
    }
}