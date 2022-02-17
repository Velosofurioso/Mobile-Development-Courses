package com.lvb.courses.app_rock_paper_scissor

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var imgAppChoose: ImageView
    private lateinit var imgRock: ImageView
    private lateinit var imgPaper: ImageView
    private lateinit var imgScissor: ImageView

    private lateinit var txtResultLabel : TextView

    private lateinit var optionSelect: GameOptions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgAppChoose = findViewById(R.id.imgAppChoose)
        imgRock = findViewById(R.id.imgRock)
        imgPaper = findViewById(R.id.imgPaper)
        imgScissor = findViewById(R.id.imgScisor)

        txtResultLabel = findViewById(R.id.txtLabel2)

        imgRock.setOnClickListener { setUserOption(it)}
        imgPaper.setOnClickListener { setUserOption(it)}
        imgScissor.setOnClickListener { setUserOption(it)}
    }

    private fun setUserOption(view: View) {
        imgAppChoose.setImageBitmap(BitmapFactory.decodeResource(applicationContext.resources,R.drawable.padrao))

        when (view.id) {
            imgRock.id -> {
                optionSelect = GameOptions.ROCK
            }
            imgPaper.id -> {
                optionSelect = GameOptions.PAPER
            }
            imgScissor.id -> {
                optionSelect = GameOptions.SCISSOR
            }
        }

        generateAppChoose()
    }

    private fun generateAppChoose() {
        val random : Int = Random.nextInt(3)
        val appOption = GameOptions.fromInt(random)

        val resources: Resources = applicationContext.resources

        val packageName : String = applicationContext.packageName

        val imgId: Int = resources.getIdentifier(
            "$packageName:drawable/${appOption?.name?.lowercase(Locale.getDefault())}",
            null,
            null
        )

        imgAppChoose.setImageBitmap(BitmapFactory.decodeResource(resources,imgId))

        txtResultLabel.text = appOption?.let { verifyWinner(it) }

    }

    private fun verifyWinner(appOption: GameOptions) : String {

        val resultMessage: String = if(appOption == GameOptions.ROCK && optionSelect == GameOptions.PAPER) {
            "You Win!!!"
        } else if(appOption == GameOptions.PAPER && optionSelect == GameOptions.SCISSOR) {
            "You Win!!!"
        } else if(appOption == GameOptions.SCISSOR && optionSelect == GameOptions.ROCK) {
            "You Win!!!"
        } else if(appOption == optionSelect) {
            "It's a Draw!!"
        } else {
            "You Lost!!!"
        }

        return resultMessage
    }


}