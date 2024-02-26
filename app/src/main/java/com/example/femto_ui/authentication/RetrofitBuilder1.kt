package com.example.femto_ui.authentication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitBuilder1 {

    companion object{
        var retrofitService:RetrofitService? = null
        fun getInstance():RetrofitService{
            if(retrofitService==null){
                retrofitService = Retrofit.Builder()
                    .baseUrl("https://6hvjwmwdp4wfznzh4evxqntkmq0ilmlh.lambda-url.ap-south-1.on.aws/femto/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create()
            }
            return retrofitService!!
        }
    }
}