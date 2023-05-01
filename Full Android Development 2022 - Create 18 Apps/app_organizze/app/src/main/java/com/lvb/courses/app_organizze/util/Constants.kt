package com.lvb.courses.app_organizze.util

class Constants {

    companion object {
        const val FIREBASE_DB_USERS_NAME = "users"

        // Movements Table
        const val FIREBASE_DB_MOVEMENT_NAME = "movements"

        const val FIREBASE_DB_MOVEMENT_TOTAL_REVENUE = "totalRevenue"
        const val FIREBASE_DB_MOVEMENT_TOTAL_EXPENSE = "totalExpense"

        val MONTHS = arrayOf<CharSequence>("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    }
}