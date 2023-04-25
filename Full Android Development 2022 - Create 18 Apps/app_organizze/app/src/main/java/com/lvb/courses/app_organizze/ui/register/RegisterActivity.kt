package com.lvb.courses.app_organizze.ui.register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.lvb.courses.app_organizze.config.FirebaseConfiguration
import com.lvb.courses.app_organizze.databinding.ActivityRegisterBinding
import com.lvb.courses.app_organizze.model.User
import com.lvb.courses.app_organizze.util.Validator.Companion.isEditFilled

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
            if (isEditFilled(textName, "Name", applicationContext) && isEditFilled(
                    textEmail,
                    "Email",
                    applicationContext
                )
                && isEditFilled(textPassword, "Password", applicationContext)
            ) {
                registerUser(user)
            }
        }
    }


    private fun registerUser(user: User) {
        firebaseAuth = FirebaseConfiguration.getFirebaseAuth()
        firebaseAuth.createUserWithEmailAndPassword(
            user.email, user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                finish()
            } else {

                var exception: String = ""
                try {
                    throw task.exception!!
                } catch (e: FirebaseAuthWeakPasswordException) {
                    exception = "Enter a stronger password"
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    exception = "Enter a valid email address"
                } catch (e: FirebaseAuthWeakPasswordException) {
                    exception = "This account has already been registered"
                } catch (e: Exception) {
                    exception = "Error registering user: ${e.message}"
                    e.printStackTrace()
                }

                Toast.makeText(applicationContext, exception, Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}
