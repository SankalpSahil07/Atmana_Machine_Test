package com.example.atmanamachinetest.adapters

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.atmanamachinetest.fragments.LocalFragment
import com.example.atmanamachinetest.fragments.RemoteFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = if(position == 0) RemoteFragment() else LocalFragment()
}