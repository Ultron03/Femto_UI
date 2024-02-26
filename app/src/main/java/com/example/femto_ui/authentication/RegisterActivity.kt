package com.example.femto_ui.authentication


import android.content.Intent
import android.graphics.Color
import android.icu.text.CaseMap.Upper
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.femto_ui.HomePageActivity
import com.example.femto_ui.R


class RegisterActivity : AppCompatActivity() {
    private lateinit var edtpassword:EditText
    private lateinit var edtConfimPassword:EditText
    private lateinit var btnSignUp:Button
    private lateinit var imgBacktoOtpverify:ImageView

    private lateinit var repo:Repo
    private lateinit var repo1: Repo1
    private lateinit var authviewModel: AuthViewModel
    private lateinit var authViewModelFactory: AuthViewModelFactory

    private lateinit var frameOne:ImageView
    private lateinit var frameTwo:ImageView
    private lateinit var frameThree:ImageView
    private lateinit var frameFour:ImageView

    private var hasUpper:Boolean = false
    private var hasSpecial:Boolean = false
    private var hasNumber:Boolean = false
    private var inRange:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()

        edtpassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registrationDataCheck()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        clickedSignUp()
        clickedBack()

    }

    private fun clickedSignUp() {
        btnSignUp.setOnClickListener {
            if(TextUtils.isEmpty(edtpassword.text.toString())){
                edtpassword.error = "*Required"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(edtConfimPassword.text.toString())){
                edtConfimPassword.error = "*Required"
                return@setOnClickListener
            }

            if(!inRange){
                edtpassword.error = "Password length should be between 8-12 character"
                frameOne.setImageResource(R.color.red)
                return@setOnClickListener
            }

            if(!hasUpper){
                edtpassword.error = "*Required Atleast 1 uppercase character"
                frameTwo.setImageResource(R.color.red)
                return@setOnClickListener
            }

            if(!hasNumber){
                edtpassword.error = "Password should have Number"
                frameThree.setImageResource(R.color.red)
                return@setOnClickListener
            }

            if(!hasSpecial){
                edtpassword.error = "*Required Atleast 1 Special character"
                frameFour.setImageResource(R.color.red)
                return@setOnClickListener
            }




            if(edtpassword.text.toString()!=edtConfimPassword.text.toString()){
                edtConfimPassword.error = "Does not Match with Password"
                return@setOnClickListener
            }

            val number = intent.getStringExtra("phoneNumber")
            val password = edtpassword.text.toString()
            val confirmPassword = edtConfimPassword.text.toString()
            authviewModel.register(number!!,password,confirmPassword)
            authviewModel.checkingRegister.observe(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Signup Successfull", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,HomePageActivity::class.java))
                }
                else{
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
//            startActivity(Intent(this,HomePageActivity::class.java))
        }
    }

    private fun clickedBack() {
        imgBacktoOtpverify.setOnClickListener {

            val countryCode = intent.getStringExtra("countryCode")
            val number = intent.getStringExtra("phoneNumber")
            val otp = intent.getStringExtra("otp")
            val intent = Intent(this,Otp_Verification::class.java)
            intent.putExtra("phoneNumber",number)
            intent.putExtra("countryCode",countryCode)
            intent.putExtra("otp",otp.toString())
            startActivity(intent)
        }
    }

    private fun init(){
        edtpassword = findViewById(R.id.edt_pass)
        edtConfimPassword =findViewById(R.id.edt_confirm_pass)
        btnSignUp = findViewById(R.id.btn_signup)
        imgBacktoOtpverify = findViewById(R.id.back_to_otp_verify)

        frameOne = findViewById(R.id.valdiate1)
        frameTwo = findViewById(R.id.valdiate2)
        frameThree = findViewById(R.id.valdiate3)
        frameFour = findViewById(R.id.valdiate4)

        repo = Repo(RetrofitBuilder.getInstance())
        repo1 = Repo1(RetrofitBuilder1.getInstance())
        authViewModelFactory = AuthViewModelFactory(repo,repo1)
        authviewModel = ViewModelProvider(this,authViewModelFactory).get(AuthViewModel::class.java)


    }


    private fun registrationDataCheck() {
        val password: String = edtpassword.text.toString()


        if (password.length in 8..20) {
            inRange = true
            frameOne.setImageResource(R.color.green)
        } else {
            inRange = false
            frameOne.setImageResource(R.color.grey)
        }
        if (password.matches("(.*[A-Z].*)".toRegex())) {
            hasUpper = true
            frameTwo.setImageResource(R.color.green)
        } else {
            hasUpper = false
            frameTwo.setImageResource(R.color.grey)
        }
        if (password.matches("(.*[0-9].*)".toRegex())) {
            hasNumber = true
            frameThree.setImageResource(R.color.green)
        } else {
            hasNumber = false
            frameThree.setImageResource(R.color.grey)
        }
        if (password.matches("^(?=.*[_.()\$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-]).*$".toRegex())) {
            hasSpecial = true
            frameFour.setImageResource(R.color.green)
        } else {
            hasSpecial = false
            frameFour.setImageResource(R.color.grey)
        }

    }
}