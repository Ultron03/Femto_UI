package com.example.femto_ui.authentication

import android.content.Intent
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
import com.example.femto_ui.HomePageActivity
import com.example.femto_ui.R
import kotlin.random.Random

class OtpForgetPasswordActivity : AppCompatActivity() {
    private lateinit var inputOTP1: EditText
    private lateinit var inputOTP2: EditText
    private lateinit var inputOTP3: EditText
    private lateinit var inputOTP4: EditText
    private lateinit var inputOTP5: EditText

    private lateinit var txtShowNumber: TextView
    private lateinit var btnContinue: Button
    private lateinit var txtResendOtp: TextView
    private lateinit var imgBacktoLogin: ImageView

    private lateinit var repo:Repo
    private lateinit var repo1: Repo1
    private lateinit var authviewModel: AuthViewModel
    private lateinit var authViewModelFactory: AuthViewModelFactory

    private lateinit var  number:String
    private lateinit var countryCode:String
    private lateinit var otp:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_forget_password)

        val intent = getIntent()
        number = intent.getStringExtra("phoneNumber")!!
        countryCode = intent.getStringExtra("countryCode")!!
        otp = intent.getStringExtra("otp")!!
        init()
        addTextChangeListener()
        clickedContiue()
        clickedResendOtp()
        clickedBack()
    }

    private fun clickedBack() {
        imgBacktoLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("phoneNumber",number)
            intent.putExtra("countryCode",countryCode)
            intent.putExtra("otp",otp)
            startActivity(intent)
        }
    }

    private fun clickedResendOtp() {
        txtResendOtp.setOnClickListener {
            otp = Random.nextInt(99999).toString()
            val message = "Your otp from Femto is ${otp}"
            authviewModel.sendOtp(countryCode, number,message)
            authviewModel.otpsendSuccess.observe(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"OTP sended Successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun clickedContiue() {
        btnContinue.setOnClickListener {

            val entered_otp = (inputOTP1.text.toString()+inputOTP2.text.toString()+inputOTP3.text.toString()+inputOTP4.text.toString()+inputOTP5.text.toString())

            if(otp==entered_otp){
                Toast.makeText(this,"Verified Successfully",Toast.LENGTH_SHORT).show()

                val intent = Intent(this,HomePageActivity::class.java)
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

    private fun init() {
        inputOTP1 = findViewById(R.id.otpEditText1)
        inputOTP2 = findViewById(R.id.otpEditText2)
        inputOTP3 = findViewById(R.id.otpEditText3)
        inputOTP4 = findViewById(R.id.otpEditText4)
        inputOTP5 = findViewById(R.id.otpEditText5)

        btnContinue = findViewById(R.id.btn_continue_forgetpass)
        txtResendOtp = findViewById(R.id.txt_resend_otp_forgetpass)
        txtShowNumber = findViewById(R.id.txt_shownumber_forgetpass)
        imgBacktoLogin = findViewById(R.id.back_to_login)

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