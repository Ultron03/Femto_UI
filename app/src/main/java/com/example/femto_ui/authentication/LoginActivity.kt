package com.example.femto_ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.femto_ui.HomePageActivity
import com.example.femto_ui.R

class LoginActivity : AppCompatActivity() {
    private lateinit var btnlogin:Button
    private lateinit var imggotoback:ImageView
    private lateinit var txtforgetpass:TextView
    private lateinit var edtpaasword:EditText

    private lateinit var repo:Repo
    private lateinit var repo1: Repo1
    private lateinit var authviewModel: AuthViewModel
    private lateinit var authViewModelFactory: AuthViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        clickedLogin()
        clickedBack()
        clickedForgetPassword()
    }

    private fun clickedForgetPassword() {
        txtforgetpass.setOnClickListener {

            val intent = getIntent()
            val number = intent.getStringExtra("phoneNumber")
            val otp = intent.getStringExtra("otp")
            val countryCode = intent.getStringExtra("countryCode")
            authviewModel.sendOtp(countryCode!!,number!!,otp!!)
            authviewModel.otpsendSuccess.observe(this){
                Toast.makeText(this,"OTP sent",Toast.LENGTH_SHORT).show()
            }
            val sendIntent = Intent(this,OtpForgetPasswordActivity::class.java)
            sendIntent.putExtra("phoneNumber",number)
            sendIntent.putExtra("countryCode",countryCode)
            sendIntent.putExtra("otp",otp)
            startActivity(sendIntent)
        }
    }

    private fun clickedBack() {
        imggotoback.setOnClickListener {
            startActivity(Intent(this,AuthMainActivity::class.java))
        }
    }

    private fun clickedLogin() {
        btnlogin.setOnClickListener {
            val intent = getIntent()
            val number = intent.getStringExtra("phoneNumber")
            val password = edtpaasword.text.toString()
            if(number!=null){
                authviewModel.login(number,password)
            }

            authviewModel.checking.observe(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Login Successfull", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,HomePageActivity::class.java))
                }
                else{
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun init(){
        btnlogin = findViewById(R.id.btn_login)
        imggotoback = findViewById(R.id.back_to_create_acc1)
        txtforgetpass = findViewById(R.id.txt_forget_pass)
        edtpaasword = findViewById(R.id.edt_password)
        repo = Repo(RetrofitBuilder.getInstance())
        repo1 = Repo1(RetrofitBuilder1.getInstance())
        authViewModelFactory = AuthViewModelFactory(repo,repo1)
        authviewModel = ViewModelProvider(this,authViewModelFactory).get(AuthViewModel::class.java)
    }
}