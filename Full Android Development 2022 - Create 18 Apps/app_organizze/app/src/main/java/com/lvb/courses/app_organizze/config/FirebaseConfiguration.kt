package com.lvb.courses.app_organizze.config

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseConfiguration {

    companion object {
        private var firebaseAuth: FirebaseAuth? = null
        private var firebaseDB: DatabaseReference? = null

        fun getFirebaseAuth() : FirebaseAuth {

            if (firebaseAuth == null) {
                firebaseAuth = FirebaseAuth.getInstance()
            }
            return firebaseAuth!!
        }

        fun getFirebaseDB() : DatabaseReference {

            if (firebaseDB == null) {
                firebaseDB = FirebaseDatabase.getInstance().reference
            }
            return firebaseDB!!
        }
    }
}