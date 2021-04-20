package com.gno.taskthepictures.retrofit

import retrofit2.http.GET

interface RetrofitServices {

    @GET("/task-m-001/list.php")
    suspend fun getDataList(): ArrayList<String>

}
