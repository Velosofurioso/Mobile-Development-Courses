package com.lvb.courses.app_organizze.ui.revenue

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.lvb.courses.app_organizze.config.FirebaseConfiguration
import com.lvb.courses.app_organizze.databinding.ActivityRevenueBinding
import com.lvb.courses.app_organizze.model.Movement
import com.lvb.courses.app_organizze.model.User
import com.lvb.courses.app_organizze.util.Base64Util
import com.lvb.courses.app_organizze.util.Constants
import com.lvb.courses.app_organizze.util.Validator

class RevenueActivity : AppCompatActivity() {

    private var movement: Movement = Movement()
    private lateinit var binding: ActivityRevenueBinding
    private var totalRevenue: Double = 0.00
    private var updatedRevenue: Double? = 0.00

    private val databaseRef = FirebaseConfiguration.getFirebaseDB()
    private val firebaseRef = FirebaseConfiguration.getFirebaseAuth()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recoverTotalRevenue()
    }

    fun saveRevenue(ignored: View) = with(binding) {

        if (validateRevenueFields()) {
            movement = Movement()

            val date = editDate.text.toString()
            val value = editValue.text.toString().toDouble()

            movement.value = value
            movement.category = editCategory.text.toString()
            movement.description = editDescription.text.toString()
            movement.date = date
            movement.type = "r"

            updatedRevenue = totalRevenue + value
            updateRevenue()

            movement.save(date)

            finish()
        }
    }

    private fun validateRevenueFields(): Boolean = with(binding) {

        val textValue = editValue.text.toString()
        val textDate = editDate.text.toString()
        val textCategory = editCategory.text.toString()
        val textDescription = editDescription.text.toString()

        if (textValue.isEmpty()) {
            Validator.isEditFilled(textValue, "Value", applicationContext)
            return false
        } else if (textDate.isEmpty()) {
            Validator.isEditFilled(textDate, "Date", applicationContext)
            return false
        } else if (textCategory.isEmpty()) {
            Validator.isEditFilled(textCategory, "Category", applicationContext)
            return false
        } else if (textDescription.isEmpty()) {
            Validator.isEditFilled(textDescription, "Description", applicationContext)
            return false
        }

        return true
    }


    private fun recoverTotalRevenue() {
        val userId = Base64Util.encodeBase64(firebaseRef.currentUser?.email)

        val userRef = databaseRef.child(Constants.FIREBASE_DB_USERS_NAME).child(userId)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                totalRevenue = user?.totalRevenue ?: 0.0
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun updateRevenue() {
        val userId = Base64Util.encodeBase64(firebaseRef.currentUser?.email)

        val userRef = databaseRef.child(Constants.FIREBASE_DB_USERS_NAME).child(userId)

        userRef.child("totalRevenue").setValue(updatedRevenue)

    }
}