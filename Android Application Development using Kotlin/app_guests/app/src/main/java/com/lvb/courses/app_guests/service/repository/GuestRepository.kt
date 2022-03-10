package com.lvb.courses.app_guests.service.repository

import android.content.Context
import com.lvb.courses.app_guests.service.model.GuestModel

class GuestRepository (context : Context) {

    private val mDatabase = GuestDatabase.getDatabase(context).guestDAO()


    fun getAll() : List<GuestModel> {
        return mDatabase.getInvited()
    }

    fun getPresent() : List<GuestModel> {
        return mDatabase.getPresent()
    }

    fun getAbsent() : List<GuestModel> {
        return mDatabase.getAbsents()
    }

    fun get(id: Int) : GuestModel {
        return mDatabase.get(id)
    }

    fun save(guest: GuestModel) : Boolean  {
        return mDatabase.save(guest) > 0
    }

    fun update(guest: GuestModel) : Boolean  {
        return mDatabase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        return mDatabase.delete(guest)
    }
}