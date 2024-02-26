package com.example.femto_ui.authentication

import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.femto_ui.R
import kotlin.random.Random

class Otp_Verification : AppCompatActivity() {
    private lateinit var inputOTP1:EditText
    private lateinit var inputOTP2:EditText
    private lateinit var inputOTP3:EditText
    private lateinit var inputOTP4:EditText
    private lateinit var inputOTP5:EditText

    private lateinit var txtShowNumber:TextView
    private lateinit var btnContinue: Button
    private lateinit var txtResendOtp:TextView
    private lateinit var imgBacktoCreate:ImageView

    private lateinit var  number:String
    private lateinit var countryCode:String
    private lateinit var otp:String

    private lateinit var repo:Repo
    private lateinit var repo1: Repo1
    private lateinit var authviewModel: AuthViewModel
    private lateinit var authViewModelFactory: AuthViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)
        val intent = getIntent()
        number = intent.getStringExtra("phoneNumber")!!
        countryCode = intent.getStringExtra("countryCode")!!
        otp = intent.getStringExtra("otp")!!
        init()
        txtShowNumber.text = ("We have sent an OTP on your number +${countryCode}-"+number)
        addTextChangeListener()
        clickedContiue()
        clickedResendOtp()
        clickedBack()

    }

    private fun clickedBack() {
        imgBacktoCreate.setOnClickListener {
            startActivity(Intent(this,AuthMainActivity::class.java))
        }
    }

    private fun clickedResendOtp() {
        txtResendOtp.setOnClickListener {
            otp = Random.nextInt(99999).toString()
            val message = "Your otp from Femto is ${otp}"
            authviewModel.sendOtp(countryCode!!,number!!,message)
            authviewModel.otpsendSuccess.observe(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"OTP sended Successfully",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun clickedContiue() {
        btnContinue.setOnClickListener {

            val entered_otp = (inputOTP1.text.toString()+inputOTP2.text.toString()+inputOTP3.text.toString()+inputOTP4.text.toString()+inputOTP5.text.toString())

            if(otp==entered_otp){
                Toast.makeText(this,"Verified Successfully",Toast.LENGTH_SHORT).show()

                val intent = Intent(this,RegisterActivity::class.java)
                intent.putExtra("phoneNumber",number)
                intent.putExtra("countryCode",countryCode)
                intent.putExtra("otp",otp)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Enter Correct OTP",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addTextChangeListener() {
        inputOTP1.addTextChangedListener(EditTextWatcher(inputOTP1))
        inputOTP2.addTextChangedListener(EditTextWatcher(inputOTP2))
        inputOTP3.addTextChangedListener(EditTextWatcher(inputOTP3))
        inputOTP4.addTextChangedListener(EditTextWatcher(inputOTP4))
        inputOTP5.addTextChangedListener(EditTextWatcher(inputOTP5))
    }
    private fun init(){
        inputOTP1 = findViewById(R.id.otpEditText1)
        inputOTP2 = findViewById(R.id.otpEditText2)
        inputOTP3 = findViewById(R.id.otpEditText3)
        inputOTP4 = findViewById(R.id.otpEditText4)
        inputOTP5 = findViewById(R.id.otpEditText5)

        btnContinue = findViewById(R.id.btn_continue)
        txtResendOtp = findViewById(R.id.txt_resend_otp)
        txtShowNumber = findViewById(R.id.txt_shownumber)
        imgBacktoCreate = findViewById(R.id.back_to_create_acc)

        repo = Repo(RetrofitBuilder.getInstance())
        repo1 = Repo1(RetrofitBuilder1.getInstance())
        authViewModelFactory = AuthViewModelFactory(repo,repo1)
        authviewModel = ViewModelProvider(this,authViewModelFactory).get(AuthViewModel::class.java)

    }
    inner class EditTextWatcher(private val view: View) : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {

            val text = p0.toString()
            when (view.id) {
                R.id.otpEditText1 -> if (text.length == 1) inputOTP2.requestFocus()
                R.id.otpEditText2 -> if (text.length == 1) inputOTP3.requestFocus() else if (text.isEmpty()) inputOTP1.requestFocus()
                R.id.otpEditText3 -> if (text.length == 1) inputOTP4.requestFocus() else if (text.isEmpty()) inputOTP2.requestFocus()
                R.id.otpEditText4 -> if (text.length == 1) inputOTP5.requestFocus() else if (text.isEmpty()) inputOTP3.requestFocus()
                R.id.otpEditText5 -> if (text.isEmpty()) inputOTP4.requestFocus()


            }
        }

    }
}