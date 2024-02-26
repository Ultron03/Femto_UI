package com.example.femto_ui.authentication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitBuilder {

    companion object{
        var retrofitService:RetrofitService? = null
        fun getInstance():RetrofitService{
            if(retrofitService==null){
                retrofitService = Retrofit.Builder()
                    .baseUrl("https://ebgx5gvdijs22dyfshp2dfgtdu0lklhs.lambda-url.ap-south-1.on.aws/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create()
            }
            return retrofitService!!
        }
    }
}