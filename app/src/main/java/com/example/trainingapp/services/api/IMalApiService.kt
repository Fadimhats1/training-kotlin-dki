package com.example.trainingapp.services.api

import com.example.trainingapp.model.AnimesResponse
import retrofit2.Call
import retrofit2.http.GET

interface IMalApiService {
    @GET("top/anime")
    fun getAnimes(): Call<AnimesResponse>
}