package com.lvb.projects.app_notes

import android.app.Application
import com.lvb.projects.app_notes.di.ModulesDependency
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(ModulesDependency.appModule)
            )
        }
    }
}