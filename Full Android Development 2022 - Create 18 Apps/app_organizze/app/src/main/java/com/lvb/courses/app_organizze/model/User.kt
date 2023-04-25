package com.lvb.courses.app_organizze.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Exclude
import com.lvb.courses.app_organizze.config.FirebaseConfiguration
import com.lvb.courses.app_organizze.util.Constants

data class User(
    var name: String,
    var email: String,

    @Exclude
    var password: String,

    @Exclude
    var idUser: String,

    var totalRevenue: Double,
    var totalExpense: Double,
) {
    constructor() : this("", "", "", "", 0.00,0.00)


    fun save() {
        val firebase : DatabaseReference = FirebaseConfiguration.getFirebaseDB()
        firebase.child(Constants.FIREBASE_DB_USERS_NAME)
            .child(this.idUser)
            .setValue(this)
    }
}
