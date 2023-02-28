package com.example.trainingapp.services.api

import com.example.trainingapp.model.dki.login.DkiLoginResponse
import com.example.trainingapp.model.dki.login.DkiStartResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface IDkiApiService {
    @FormUrlEncoded
    @POST("login/")
    suspend fun login(
        @Header("Authorization") auth: String,
        @Field("cst_email") email: String,
        @Field("pass") password: String,
        @Field("IMEI") imei: String = "null"
    ): Response<DkiLoginResponse>

    @FormUrlEncoded
    @POST("start/api")
    suspend fun startApi(
        @Header("Authorization") auth: String,
        @Field("cif") cif: String?,
        @Field("source") source: String,
        @Field("regID") regId: String = "null",
        @Field("OS") os: String = "null",
        @Field("IMEI") imei: String = "null",
        @Field("tokenlogin") tokenLogin: String? = "null",
        @Field("version") version: String = "null",
        @Field("appsflyer_id") appsflyerId: String = "null",
        ): Response<DkiStartResponse>
}