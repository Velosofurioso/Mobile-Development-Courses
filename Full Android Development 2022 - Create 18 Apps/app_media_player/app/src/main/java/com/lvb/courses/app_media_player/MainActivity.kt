package com.lvb.courses.app_media_player

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.lvb.courses.app_media_player.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var binding: ActivityMainBinding
    private lateinit var audioManager: AudioManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.mediaPlayer = MediaPlayer.create(applicationContext, R.raw.teste)

        initSeekBar()
    }

    fun executeSounds(view: View) {
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.start()
            this.mediaPlayer = MediaPlayer.create(applicationContext, R.raw.teste)
        }
    }

    fun pauseSounds(view: View) {
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            this.mediaPlayer = MediaPlayer.create(applicationContext, R.raw.teste)
        }
    }

    fun stopSounds(view: View) {
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            this.mediaPlayer = MediaPlayer.create(applicationContext, R.raw.teste)
        }
    }

    private fun initSeekBar() = with(binding) {

        // configure audio manager
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        //Recover values of maximum volume and actual volume
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val actualVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        //Configure the maximum value for seekbar
        seekVolume.max = maxVolume

        //Configure the actual value for seekbar
        seekVolume.progress = actualVolume

        seekVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, p1, 0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

    }

    override fun onStop() {
        super.onStop()
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release() // Release media resources that the media player is using
        }
    }

}