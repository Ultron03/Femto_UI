package com.example.femto_ui.authentication

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface RetrofitService {

    @FormUrlEncoded
    @POST("sms/send")
    suspend fun verifyPhoneNumber(@Field("country_code") countryCode:String,@Field("phone")phoneNumber:String,@Field("message")message:String):Response<Any>

    @FormUrlEncoded
    @POST("checkUserExists")
    suspend fun checkUserExists(@Field("phone") phone:String):Response<TwoStringData>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("phone")phone:String,@Field("password")password:String):Response<Any>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(@Field("phone")phone:String,@Field("password")password:String,@Field("confirmPassword")confirmPassword:String):Response<Any>


}