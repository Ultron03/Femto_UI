package com.example.femto_ui.startfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.femto_ui.authentication.AuthMainActivity
import com.example.femto_ui.R
import com.example.femto_ui.authentication.LoginActivity
import com.example.femto_ui.authentication.OtpForgetPasswordActivity
import com.example.femto_ui.authentication.Otp_Verification
import com.example.femto_ui.authentication.RegisterActivity


class AndroidLarge3() : Fragment() {
    private lateinit var btnNext:Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_android_large3,container,false)
        btnNext = view.findViewById(R.id.btn_next)
        btnNext.setOnClickListener {

            startActivity(Intent(requireContext(), AuthMainActivity::class.java))
        }
        return view
    }
}