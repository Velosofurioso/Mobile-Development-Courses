package com.lvb.courses.app_organizze.config

import com.google.firebase.auth.FirebaseAuth

class FirebaseConfiguration {

    companion object {
        private var firebaseAuth: FirebaseAuth? = null

        fun getFirebaseAuth() : FirebaseAuth {

            if (firebaseAuth == null) {
                firebaseAuth = FirebaseAuth.getInstance()
            }
            return firebaseAuth!!
        }
    }
}