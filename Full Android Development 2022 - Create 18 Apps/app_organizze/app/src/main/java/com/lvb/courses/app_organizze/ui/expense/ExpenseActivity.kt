package com.lvb.courses.app_organizze.ui.expense

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lvb.courses.app_organizze.databinding.ActivityExpenseBinding
import com.lvb.courses.app_organizze.model.Movement
import com.lvb.courses.app_organizze.util.DateUtil

class ExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpenseBinding
    private var movement: Movement = Movement()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editDate.setText(DateUtil.actualDate())
    }

    fun saveExpense(view: View) = with(binding) {
        movement = Movement()

        val date = editDate.text.toString()

        movement.value = editValue.text.toString().toDouble()
        movement.category = editCategory.text.toString()
        movement.description = editDescription.text.toString()
        movement.date = date
        movement.type = "d"

        movement.save(date)
    }
}