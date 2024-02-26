package com.example.femto_ui.authentication

class Repo(
    private val retrofitService: RetrofitService
) {
    suspend fun verifyNumber(countryCode:String,phoneNumber:String,message:String) = retrofitService.verifyPhoneNumber(countryCode, phoneNumber, message)
}