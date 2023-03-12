package com.lvb.courses.app_tabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lvb.courses.app_tabs.databinding.ActivityMainBinding
import com.lvb.courses.app_tabs.ui.home.HomeFragment
import com.lvb.courses.app_tabs.ui.inHigh.InHighFragment
import com.lvb.courses.app_tabs.ui.registrations.RegistrationsFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0F

        //Configure adapter to tabs
        val adapter = FragmentPagerItemAdapter(
            supportFragmentManager,
            FragmentPagerItems.with(applicationContext)
                .add("Home", HomeFragment::class.java)
                .add("Registrations", RegistrationsFragment::class.java)
                .add("In High", InHighFragment::class.java)
                .create()
        )

        this.binding.viewPager.adapter = adapter
        this.binding.viewPagerTab.setViewPager(this.binding.viewPager)
    }
}