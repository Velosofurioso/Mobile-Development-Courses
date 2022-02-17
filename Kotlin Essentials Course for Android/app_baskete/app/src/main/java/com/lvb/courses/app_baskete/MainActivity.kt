package com.lvb.courses.app_baskete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var teamAPoint : Int = 0;
    var teamBPoint : Int = 0;

    lateinit var txtTeamAPoints: TextView
    lateinit var txtTeamBPoints: TextView

    lateinit var btnThreeA: Button
    lateinit var btnTwoA: Button
    lateinit var btnFreeThrowA: Button

    lateinit var btnThreeB: Button
    lateinit var btnTwoB: Button
    lateinit var btnFreeThrowB: Button

    lateinit var btnRestartGame: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTeamAPoints = findViewById(R.id.txtTeamAPoint)
        txtTeamBPoints = findViewById(R.id.txtTeamBPoint)

        btnRestartGame = findViewById(R.id.btnRestartGame)

        btnThreeA = findViewById(R.id.btnThreeA)
        btnTwoA = findViewById(R.id.btnTwoA)
        btnFreeThrowA = findViewById(R.id.btnFreeThrowA)

        btnThreeB = findViewById(R.id.btnThreeB)
        btnTwoB = findViewById(R.id.btnTwoB)
        btnFreeThrowB = findViewById(R.id.btnFreeThrowB)

        setButtonListeners();
    }

    private fun setButtonListeners() {
        btnRestartGame.setOnClickListener { restartGame() }

        btnThreeA.setOnClickListener {computeThreePoints(it)}
        btnThreeB.setOnClickListener {computeThreePoints(it)}

        btnTwoA.setOnClickListener {computeTwoPoints(it)}
        btnTwoB.setOnClickListener {computeTwoPoints(it)}

        btnFreeThrowA.setOnClickListener {computeFreeThrowPoints(it)}
        btnFreeThrowB.setOnClickListener {computeFreeThrowPoints(it)}
    }

    private fun computeThreePoints(button: View) {

        if(button.id == btnThreeA.id) {
            teamAPoint += 3;
            txtTeamAPoints.text = "$teamAPoint"
        }

        else if(button.id == btnThreeB.id) {
            teamBPoint += 3;
            txtTeamBPoints.text = "$teamBPoint"
        }

    }

    private fun computeTwoPoints(button: View) {

        if(button.id == btnTwoA.id) {
            teamAPoint += 2;
            txtTeamAPoints.text = "$teamAPoint"
        }

        else if(button.id == btnTwoB.id) {
            teamBPoint += 2;
            txtTeamBPoints.text = "$teamBPoint"
        }

    }

    private fun computeFreeThrowPoints(button: View) {

        if(button.id == btnFreeThrowA.id) {
            teamAPoint += 1;
            txtTeamAPoints.text = "$teamAPoint"
        }

        else if(button.id == btnFreeThrowB.id) {
            teamBPoint += 1;
            txtTeamBPoints.text = "$teamBPoint"
        }

    }

    private fun restartGame() {
        teamAPoint = 0;
        txtTeamAPoints.text = "$teamAPoint"

        teamBPoint = 0;
        txtTeamBPoints.text = "$teamBPoint"

    }
}