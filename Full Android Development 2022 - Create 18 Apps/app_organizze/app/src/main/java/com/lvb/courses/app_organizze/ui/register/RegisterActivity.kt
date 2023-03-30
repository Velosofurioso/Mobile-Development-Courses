package com.lvb.courses.app_organizze.ui.register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.lvb.courses.app_organizze.config.FirebaseConfiguration
import com.lvb.courses.app_organizze.databinding.ActivityRegisterBinding
import com.lvb.courses.app_organizze.model.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var user: User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {

            val textName = binding.editName.text.toString()
            val textEmail = binding.editEmail.text.toString()
            val textPassword = binding.editPassword.text.toString()

            user.name = textName
            user.email = textEmail
            user.password = textPassword

            // Validate if fields are filled
            if (isEditFilled(textName, "Name") && isEditFilled(textEmail, "Email") && isEditFilled(
                    textPassword,
                    "Password"
                )
            ) {
                registerUser(user)
            }
        }
    }


    private fun isEditFilled(value: String, name: String): Boolean {
        if (value.isEmpty()) {
            Toast.makeText(applicationContext, "Fill the $name", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun registerUser(user: User) {
        firebaseAuth = FirebaseConfiguration.getFirebaseAuth()
        firebaseAuth.createUserWithEmailAndPassword(
            user.email, user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    applicationContext,
                    "Success in registering user",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(applicationContext, "Error registering user", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}
