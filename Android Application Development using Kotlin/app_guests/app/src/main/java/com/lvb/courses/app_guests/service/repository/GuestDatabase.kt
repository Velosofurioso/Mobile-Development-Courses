package com.lvb.courses.app_guests.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lvb.courses.app_guests.service.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {

    abstract fun guestDAO() : GuestDAO

    companion object {

        private lateinit var INSTANCE : GuestDatabase

        fun getDatabase(context: Context) : GuestDatabase {
            if(!::INSTANCE.isInitialized) {
                // Prevent this code to run in multiple threads at same time,
                // in other words this code only will be executed in one thread per time
                synchronized(GuestDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, GuestDatabase::class.java, "guestDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}