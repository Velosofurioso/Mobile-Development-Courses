package com.lvb.courses.app_organizze.ui.expense

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.lvb.courses.app_organizze.config.FirebaseConfiguration
import com.lvb.courses.app_organizze.databinding.ActivityExpenseBinding
import com.lvb.courses.app_organizze.model.Movement
import com.lvb.courses.app_organizze.model.User
import com.lvb.courses.app_organizze.util.Base64Util
import com.lvb.courses.app_organizze.util.Constants
import com.lvb.courses.app_organizze.util.DateUtil
import com.lvb.courses.app_organizze.util.Validator

class ExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpenseBinding
    private var movement: Movement = Movement()
    private var totalExpense: Double = 0.00
    private var updatedExpense: Double? = 0.00

    private val databaseRef = FirebaseConfiguration.getFirebaseDB()
    private val firebaseRef = FirebaseConfiguration.getFirebaseAuth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editDate.setText(DateUtil.actualDate())
        recoverTotalExpense()
    }

    fun saveExpense(ignored: View) = with(binding) {

        if (validateExpenseFields()) {
            movement = Movement()

            val date = editDate.text.toString()
            val value = editValue.text.toString().toDouble()

            movement.value = value
            movement.category = editCategory.text.toString()
            movement.description = editDescription.text.toString()
            movement.date = date
            movement.type = "d"

            updatedExpense = totalExpense + value
            updateExpense()

            movement.save(date)
        }
    }
    private fun validateExpenseFields() : Boolean = with(binding) {

        val textValue = editValue.text.toString()
        val textDate = editDate.text.toString()
        val textCategory = editCategory.text.toString()
        val textDescription = editDescription.text.toString()

        if (textValue.isEmpty()) {
            Validator.isEditFilled(textValue, "Value", applicationContext)
            return false
        }

        else if (textDate.isEmpty()) {
            Validator.isEditFilled(textDate, "Date", applicationContext)
            return false
        }

        else if (textCategory.isEmpty()) {
            Validator.isEditFilled(textCategory, "Category", applicationContext)
            return false
        }

        else if (textDescription.isEmpty()) {
            Validator.isEditFilled(textDescription, "Description", applicationContext)
            return false
        }

        return true
    }

    private fun recoverTotalExpense() {
        val userId = Base64Util.encodeBase64(firebaseRef.currentUser?.email)

        val userRef = databaseRef.child(Constants.FIREBASE_DB_USERS_NAME).child(userId)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue( User::class.java )
                totalExpense = user?.totalExpense ?: 0.0
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun updateExpense() {
        val userId = Base64Util.encodeBase64(firebaseRef.currentUser?.email)

        val userRef = databaseRef.child(Constants.FIREBASE_DB_USERS_NAME).child(userId)

        userRef.child("totalExpense").setValue(updatedExpense)

    }
}