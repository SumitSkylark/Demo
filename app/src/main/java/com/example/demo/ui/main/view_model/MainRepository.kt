package com.example.demo.ui.main.view_model

import android.content.Context
import com.example.demo.retrofit.RetrofitInterface
import com.example.demo.retrofit.RetrofitUtils.callRetrofit
import com.example.demo.ui.main.model.ResponseData
import retrofit2.Response

class MainRepository(private val retrofitInterface: RetrofitInterface) {

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance
                    ?: MainRepository(callRetrofit(context)).also {
                        instance = it
                    }
            }
    }

    suspend fun getList(): Response<ArrayList<ResponseData>?>? {
        return retrofitInterface.getList()
    }
}