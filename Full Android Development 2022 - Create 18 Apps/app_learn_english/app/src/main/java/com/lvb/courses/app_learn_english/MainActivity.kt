package com.lvb.courses.app_learn_english

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lvb.courses.app_learn_english.databinding.ActivityMainBinding
import com.lvb.courses.app_learn_english.ui.animal.AnimalFragment
import com.lvb.courses.app_learn_english.ui.numbers.NumberFragment
import com.lvb.courses.app_learn_english.ui.vowels.VowelsFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0F

        val adapter = FragmentPagerItemAdapter(
            supportFragmentManager, FragmentPagerItems.with(this)
                .add(R.string.animal_fragment_title, AnimalFragment::class.java)
                .add(R.string.number_fragment_title, NumberFragment::class.java)
                .add(R.string.vowels_fragment_title, VowelsFragment::class.java)
                .create()
        )

        this.binding.viewPager.adapter = adapter
        this.binding.viewPagerTab.setViewPager(this.binding.viewPager)
    }
}