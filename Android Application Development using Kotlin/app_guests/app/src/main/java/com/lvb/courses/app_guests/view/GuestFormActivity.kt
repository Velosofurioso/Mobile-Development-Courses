package com.lvb.courses.app_guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lvb.courses.app_guests.viewmodel.GuestFormViewModel
import com.lvb.courses.app_guests.R
import com.lvb.courses.app_guests.service.constants.GuestConstants

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnSave : Button
    private lateinit var editName : EditText
    private lateinit var radioPresence : RadioButton

    private lateinit var mViewModel : GuestFormViewModel

    private var mGuestId : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        radioPresence = findViewById(R.id.radio_present)
        editName = findViewById(R.id.edit_name)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListener()
        observe()
        loadData()

        radioPresence.isChecked = true
    }

    private fun loadData() {
        val bundle = intent.extras
        if(bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUEST_ID)
            mViewModel.load(mGuestId)
        }
    }

    override fun onClick(view: View?) {
        val id  = view?.id

        if(id == btnSave.id) {

            val name = editName.text.toString()
            val presence = radioPresence.isChecked

            mViewModel.save(mGuestId, name, presence)

        }
    }


    private fun setListener() {
        btnSave = findViewById(R.id.btn_save)
        btnSave.setOnClickListener{onClick(it)}
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this) {
            if(it) {
                Toast.makeText(applicationContext, R.string.sucess, Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, R.string.failed, Toast.LENGTH_SHORT).show()
            }

            finish()
        }

        mViewModel.guest.observe(this) {
            editName.setText(it.name)
            if(it.presence) {
                radioPresence.isChecked = true
            }
            else {
                findViewById<RadioButton>(R.id.radio_absent).isChecked = true
            }

        }
    }

}