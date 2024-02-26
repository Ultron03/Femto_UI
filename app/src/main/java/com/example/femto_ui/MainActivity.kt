package com.example.femto_ui

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager

import androidx.viewpager2.widget.ViewPager2
import com.example.femto_ui.startfragment.AndroidLarge1
import com.example.femto_ui.startfragment.AndroidLarge2
import com.example.femto_ui.startfragment.AndroidLarge3
import com.example.femto_ui.startfragment.ChangeFragment
import com.google.android.material.tabs.TabLayout





class MainActivity : AppCompatActivity()  {
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: adapter
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback
    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8,0,8,0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        val slideDotLL = findViewById<LinearLayout>(R.id.slideDotLL)
        val dotsImage = Array(3) { ImageView(this) }

        dotsImage.forEach {
            it.setImageResource(
                R.drawable.inactive_indicator_1
            )
            slideDotLL.addView(it,params)
        }

        // default first dot selected
        dotsImage[0].setImageResource(R.drawable.active_indicator_1)
        pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed { index, imageView ->
                    if (position == index && position==0){
                        imageView.setImageResource(
                            R.drawable.active_indicator_1
                        )
                    }
                    else if(position == index && position==1){
                        imageView.setImageResource(
                            R.drawable.active_indicator_2
                        )
                    }
                    else if(position == index && position==2){
                        imageView.setImageResource(
                            R.drawable.active_indicator_3
                        )
                    }
                    else{
                        imageView.setImageResource(R.drawable.inactive_indicator_1)
                    }
                }
                super.onPageSelected(position)
            }
        }
        viewPager2.registerOnPageChangeCallback(pageChangeListener)

        

    }

    private fun init(){

        adapter = adapter(this)
        adapter.addFragment(AndroidLarge1())
        adapter.addFragment(AndroidLarge2())
        adapter.addFragment(AndroidLarge3())
        viewPager2 = findViewById(R.id.viewPager)
        viewPager2.adapter = adapter

    }


}