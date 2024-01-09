package com.example.demo.retrofit

import com.example.demo.ui.main.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("v2/list?page=2&limit=20")
    suspend fun getList(): Response<ArrayList<ResponseData>?>?
}