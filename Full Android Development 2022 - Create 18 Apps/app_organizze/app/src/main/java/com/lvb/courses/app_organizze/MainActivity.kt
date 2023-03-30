package com.lvb.courses.app_organizze

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide
import com.lvb.courses.app_organizze.ui.login.LoginActivity
import com.lvb.courses.app_organizze.ui.register.RegisterActivity

class MainActivity : IntroActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        isButtonBackVisible = false
        isButtonNextVisible = false

        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_1)
            .canGoBackward(false)
            .build())

        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_2)
            .build())

        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_3)
            .build())

        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_4)
            .build())


        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_cadastro)
            .canGoForward(false)
            .build())
    }

    fun btnLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun btnRegister(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}