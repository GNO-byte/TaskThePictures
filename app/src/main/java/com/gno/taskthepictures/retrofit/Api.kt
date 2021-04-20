package com.gno.taskthepictures.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    val BASE_URL = "http://dev-tasks.alef.im/"

    val retrofitService: RetrofitServices = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitServices::class.java)

}