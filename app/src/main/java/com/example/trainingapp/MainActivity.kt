package com.example.trainingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.trainingapp.adapter.AnimeAdapter
import com.example.trainingapp.model.Anime
import com.example.trainingapp.model.AnimesResponse
import com.example.trainingapp.services.api.ApiSingleton
import com.example.trainingapp.services.api.IApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        responseCall().enqueue(object : Callback<AnimesResponse> {
            override fun onResponse(
                call: Call<AnimesResponse>,
                response: Response<AnimesResponse>
            ) {

                anime_list_rv.adapter = AnimeAdapter(response?.body()?.data as List<Anime>)
            }

            override fun onFailure(call: Call<AnimesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

    fun responseCall(): Call<AnimesResponse> {

        return ApiSingleton.getInstance().create<IApiService>(IApiService::class.java).getAnimes()
    }
}