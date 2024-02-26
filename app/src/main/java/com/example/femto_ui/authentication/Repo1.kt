package com.example.femto_ui.authentication

class Repo1 (
    private val retrofitService: RetrofitService
){
    suspend fun checkUserExist(phoneNumber:String) = retrofitService.checkUserExists(phoneNumber)

    suspend fun login(phoneNumber: String,password:String) = retrofitService.login(phoneNumber,password)

    suspend fun register(phoneNumber: String,password:String,confirmPassword:String) = retrofitService.register(phoneNumber,password,confirmPassword)
}