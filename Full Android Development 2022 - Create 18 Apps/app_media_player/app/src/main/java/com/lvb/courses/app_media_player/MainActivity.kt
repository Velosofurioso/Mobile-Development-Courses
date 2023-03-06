package com.lvb.courses.app_media_player

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mediaPlayer = MediaPlayer.create(applicationContext, R.raw.teste)
    }

    fun executeSounds(view: View) {
        mediaPlayer?.start()
    }

    fun pauseSounds(view: View) {
        mediaPlayer?.let {
            if (it.isPlaying)
                it.pause()
        }
    }

    fun stopSounds(view: View) {
        mediaPlayer?.let {
            it.stop()
            this.mediaPlayer = MediaPlayer.create(applicationContext, R.raw.teste)

        }
    }
}