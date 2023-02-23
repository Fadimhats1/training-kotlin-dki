package com.example.trainingapp.services.api

import com.example.trainingapp.model.dki.login.DkiLoginResponse
import com.example.trainingapp.model.dki.login.Login
import retrofit2.Call
import retrofit2.http.*

interface IDkiApiService {
    @FormUrlEncoded
    @POST("login/")
    fun login(
        @Header("Authorization") auth:String,
        @Field("cts_email") email: String,
        @Field("pass") pass: String,
        @Field("IMEI") IMEI: String
    ): Call<DkiLoginResponse>
}