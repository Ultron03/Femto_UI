package com.example.femto_ui.authentication

import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(
    private val repo: Repo,
    private val repo1: Repo1
):ViewModel() {
    val otpsendSuccess = MutableLiveData<Response<Any>>()
    fun sendOtp(countryCode:String,number: String,message:String){
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("Hello", number)
            val response = repo.verifyNumber(countryCode,number,message)
            if(response.isSuccessful){
                otpsendSuccess.postValue(response)
            }
            Log.i("Hello", response.toString())
        }

    }
    var result=MutableLiveData<TwoStringData>()
    fun checkUserExist(number: String){
        viewModelScope.launch(Dispatchers.IO){
            val response = repo1.checkUserExist(number)
            if(response.isSuccessful){
                result.postValue(response.body())
                Log.i("Hello", response.body().toString())
            }

        }
    }
    val checking = MutableLiveData<Response<Any>>()
    fun login(number: String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo1.login(number,password)
            if(response.isSuccessful){
                checking.postValue(response)

            }
        }
    }
    val checkingRegister = MutableLiveData<Response<Any>>()
    fun register(number: String,password:String,confirmPassword:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo1.register(number,password,confirmPassword)
            if(response.isSuccessful){
                checking.postValue(response)
                Log.i("Hello", response.body().toString())

            }
        }
    }

}