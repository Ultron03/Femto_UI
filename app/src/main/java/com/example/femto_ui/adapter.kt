package com.example.femto_ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class adapter(activity: FragmentActivity):FragmentStateAdapter(activity) {
    private val fragmentlist = mutableListOf<Fragment>()
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentlist[position]
    }
    fun addFragment(frag:Fragment){
        fragmentlist.add(frag)
    }
}