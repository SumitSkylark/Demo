package com.example.demo.retrofit

import com.example.demo.ui.main.model.ResponseData
import com.example.demo.utilities.Constants.Companion.URL_LIST_VIEW
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET(URL_LIST_VIEW)
    suspend fun getList(): Response<ArrayList<ResponseData>?>?
}