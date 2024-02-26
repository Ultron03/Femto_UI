package com.example.femto_ui.authentication

import android.content.Intent
import android.media.MediaPlayer.OnCompletionListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.femto_ui.R
import com.hbb20.CountryCodePicker

import kotlin.random.Random


class AuthMainActivity : AppCompatActivity() {
    private lateinit var btncontiue:Button
    private lateinit var countryCode:CountryCodePicker
    private lateinit var edtphoneNumber:EditText
    private lateinit var txtCountryCode:TextView
    private lateinit var imgCountryCode:ImageView
    private lateinit var authviewModel: AuthViewModel
    private lateinit var authViewModelFactory: AuthViewModelFactory
    private lateinit var repo: Repo
    private lateinit var repo1: Repo1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        init()
        txtCountryCode.text = "+${countryCode.selectedCountryCode}"
        imgCountryCode.setImageResource(countryCode.selectedCountryFlagResourceId)
        countryCode.setOnCountryChangeListener {
            txtCountryCode.text = "+${countryCode.selectedCountryCode}"
            imgCountryCode.setImageResource(countryCode.selectedCountryFlagResourceId)
        }
        continueAction()
    }
    private fun init(){
        btncontiue = findViewById(R.id.btn_continue)
        txtCountryCode = findViewById(R.id.txt_countrycode)
        countryCode = findViewById(R.id.edt_countrycode)
        edtphoneNumber = findViewById(R.id.edt_phone_number)
        imgCountryCode = findViewById(R.id.img_countryimg)
        repo = Repo(RetrofitBuilder.getInstance())
        repo1 = Repo1(RetrofitBuilder1.getInstance())
        authViewModelFactory = AuthViewModelFactory(repo,repo1)
        authviewModel = ViewModelProvider(this,authViewModelFactory).get(AuthViewModel::class.java)

    }

    private fun continueAction(){
        btncontiue.setOnClickListener {
            if(TextUtils.isEmpty(countryCode.selectedCountryCode.toString())){
                edtphoneNumber.error = "*Required"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(edtphoneNumber.text.toString())){
                edtphoneNumber.error = "*Required"
                return@setOnClickListener
            }

            val countryCode = countryCode.selectedCountryCode
            val number = edtphoneNumber.text.toString()
            val otp = Random.nextInt(99999)
            val message = "Your otp from Femto is ${otp}"
            authviewModel.checkUserExist(number)
            authviewModel.result.observe(this){
                if(authviewModel.result.value!!.message  == "user_exists"){
                    val intent = Intent(this,LoginActivity::class.java)
                    intent.putExtra("phoneNumber",number)
                    intent.putExtra("countryCode",countryCode)
                    intent.putExtra("otp",otp.toString())
                    startActivity(intent)
                    Toast.makeText(this,"Sign up SuccessFul",Toast.LENGTH_SHORT).show()
                }
                else{
                    authviewModel.sendOtp(countryCode, number, message)
                    val intent = Intent(this,Otp_Verification::class.java)
                    intent.putExtra("phoneNumber",number)
                    intent.putExtra("countryCode",countryCode)
                    intent.putExtra("otp",otp.toString())
                    startActivity(intent)
                    Toast.makeText(this,"Sign up SuccessFul",Toast.LENGTH_SHORT).show()
                }
            }






        }
    }



}