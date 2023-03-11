package com.lvb.courses.app_video_player

import android.os.Build.VERSION
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.lvb.courses.app_video_player.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide Actionbar
        supportActionBar?.hide()

        //Hide StatusBar and Navigation Bar
        if (VERSION.SDK_INT >= 30) {
            val decorView = window.decorView

            val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN

            decorView.systemUiVisibility = uiOptions

        }

        else {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, window.decorView).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }






        //Execute the video
        binding.videoView.setMediaController(MediaController(this))
        binding.videoView.setVideoPath("android.resource://$packageName/" + R.raw.video)
        binding.videoView.start()


    }
}