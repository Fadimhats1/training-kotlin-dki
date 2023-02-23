package com.example.trainingapp.services.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MalApiSingleton {
    private const val baseUrl = "https://api.jikan.moe/v4/";
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}