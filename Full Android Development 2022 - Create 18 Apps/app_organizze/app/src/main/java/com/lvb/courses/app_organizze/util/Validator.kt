package com.lvb.courses.app_organizze.util

import android.content.Context
import android.widget.Toast

class Validator {

    companion object {
        fun isEditFilled(value: String, name: String, context: Context): Boolean {
            if (value.isEmpty()) {
                Toast.makeText(context, "Fill the $name", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }
    }
}