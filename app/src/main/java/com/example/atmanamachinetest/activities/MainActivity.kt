package com.example.atmanamachinetest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.atmanamachinetest.R
import com.example.atmanamachinetest.adapters.ViewPagerAdapter
import com.example.atmanamachinetest.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/***
 *
 * Step 1 :  add View Pager > show remote and local fragment
 *
 *
 * Step 2 :  add retrofit get data in logcat then show in recycler list
 * Step 3 :  apply lazy loading
 * Step 4 : add Room database
 * Step 5 : Add data locally
 * Step 6 : show on local fragment
 * Step 7 : edit data
 * Step 8 : delete data
 * **/


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.idViewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.idTabLayout,binding.idViewPager,true)
        { tab, position -> if (position == 0) tab.text = "Remote" else tab.text = "Local" }.attach()

        setListeners()
    }

    private fun setListeners() {
    }
}