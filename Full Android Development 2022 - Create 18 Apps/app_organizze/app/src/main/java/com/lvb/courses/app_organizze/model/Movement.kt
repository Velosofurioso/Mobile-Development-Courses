package com.lvb.courses.app_organizze.model

import com.lvb.courses.app_organizze.config.FirebaseConfiguration
import com.lvb.courses.app_organizze.util.Base64Util
import com.lvb.courses.app_organizze.util.Constants
import com.lvb.courses.app_organizze.util.DateUtil

data class Movement(
    var date: String,
    var category: String,
    var description: String,
    var type: String,
    var value: Double,
) {
    constructor() : this("", "", "", "", 0.00)

    fun save(chosenDate: String) {

        val auth = FirebaseConfiguration.getFirebaseAuth()

        val idUser = Base64Util.encodeBase64(auth.currentUser?.email)
        val monthYear = DateUtil.monthYearChosen(chosenDate)


        val databaseRef = FirebaseConfiguration.getFirebaseDB()
        databaseRef.child(Constants.FIREBASE_DB_MOVEMENT_NAME)
            .child(idUser)
            .child(monthYear)
            .push()
            .setValue(this)
    }
}
