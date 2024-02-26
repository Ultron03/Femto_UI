package com.example.femto_ui.startfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager

import com.example.femto_ui.R



class AndroidLarge1() : Fragment() {
    private lateinit var nextButton:Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_android_large1,container,false)
        nextButton = view.findViewById(R.id.btn_next)
        nextButton.setOnClickListener {
//            supportFragmentManager.beginTransaction().replace(R.id.,AndroidLarge2()).commit()

//            val a:FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//            a.replace(R.id.viewPager,AndroidLarge2()).commit()


        }

        return view
    }


}