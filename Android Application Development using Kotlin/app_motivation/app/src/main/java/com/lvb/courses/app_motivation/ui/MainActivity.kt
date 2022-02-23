package com.lvb.courses.app_motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.lvb.courses.app_motivation.R
import com.lvb.courses.app_motivation.repository.Mock
import com.lvb.courses.app_motivation.utils.Constants
import com.lvb.courses.app_motivation.utils.SharedPreferences

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSharedPrefs: SharedPreferences
    private var mPhraseFilter : Int = Constants.PHRASEFILTER.ALL

    private lateinit var txtname: TextView
    private lateinit var txtPhase: TextView
    private lateinit var btnNewPhrase: Button
    private  lateinit var imgInfinite: ImageView
    private  lateinit var imgSmile: ImageView
    private  lateinit var imgSun: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mSharedPrefs = SharedPreferences(this)
        txtname = findViewById(R.id.txtName)
        txtPhase = findViewById(R.id.txtPhase)
        btnNewPhrase = findViewById(R.id.btnNewPhrase)

        imgInfinite = findViewById(R.id.imgInfinite)
        imgSmile = findViewById(R.id.imgSmile)
        imgSun = findViewById(R.id.imgSun)

        imgInfinite.setColorFilter(resources.getColor(androidx.appcompat.R.color.accent_material_dark))
        txtname.text = "Hi, " + mSharedPrefs.getString(Constants.KEY.PERSON_NAME)

        btnNewPhrase.setOnClickListener(this)
        imgInfinite.setOnClickListener(this)
        imgSmile.setOnClickListener(this)
        imgSun.setOnClickListener(this)

        handleNewPhrase()

    }

    override fun onClick(view: View?) {
        val id = view?.id
        val listFilter = listOf(R.id.imgInfinite, R.id.imgSmile, R.id.imgSun)

        if (id == R.id.btnNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }

    }

    private fun handleFilter(id: Int?) {
        imgInfinite.setColorFilter(resources.getColor(R.color.white))
        imgSmile.setColorFilter(resources.getColor(R.color.white))
        imgSun.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imgInfinite -> {
                imgInfinite.setColorFilter(resources.getColor(androidx.appcompat.R.color.accent_material_dark))
                mPhraseFilter = Constants.PHRASEFILTER.ALL
            }

            R.id.imgSmile -> {
                imgSmile.setColorFilter(resources.getColor(androidx.appcompat.R.color.accent_material_dark))
                mPhraseFilter = Constants.PHRASEFILTER.HAPPY
            }
            R.id.imgSun -> {
                imgSun.setColorFilter(resources.getColor(androidx.appcompat.R.color.accent_material_dark))
                mPhraseFilter = Constants.PHRASEFILTER.MORNING
            }
        }
    }

    private fun handleNewPhrase() {
        txtPhase.text = Mock().getPhrase(mPhraseFilter)
    }
}