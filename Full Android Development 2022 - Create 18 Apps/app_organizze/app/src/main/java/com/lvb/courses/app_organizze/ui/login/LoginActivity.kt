package com.lvb.courses.app_organizze.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.lvb.courses.app_organizze.config.FirebaseConfiguration
import com.lvb.courses.app_organizze.databinding.ActivityLoginBinding
import com.lvb.courses.app_organizze.model.User
import com.lvb.courses.app_organizze.ui.home.HomeActivity
import com.lvb.courses.app_organizze.util.Validator

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var user: User = User()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonLogin.setOnClickListener {

            val textEmail = binding.editLoginEmail.text.toString()
            val textPassword = binding.editLoginPassword.text.toString()

            if (Validator.isEditFilled(textEmail, "Email", applicationContext)
                && Validator.isEditFilled(textPassword, "Password", applicationContext)
            ) {
                user.email = textEmail
                user.password = textPassword
                validateLogin()
            }
        }
    }

    private fun validateLogin() {
        firebaseAuth = FirebaseConfiguration.getFirebaseAuth()
        firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {

                    val exception: String
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        exception = "Enter a valid email address"
                    } catch (e: FirebaseAuthInvalidUserException) {
                        exception = "User is not registered"
                    } catch (e: Exception) {
                        exception = "Error on log in: ${e.message}"
                        e.printStackTrace()
                    }

                    Toast.makeText(applicationContext, exception, Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}