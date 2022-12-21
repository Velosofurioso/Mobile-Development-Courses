package com.lvb.projects.app_notes.di

import com.lvb.projects.app_notes.data.Database
import com.lvb.projects.app_notes.data.NotesManager
import com.lvb.projects.app_notes.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ModulesDependency {

    val appModule = module {

        // Creates a singleton instance for Database Class
        single { Database() }

        // Create an instance for a class,
        // and get() fill the instance that is need in constructor
        factory { NotesManager(get()) }

        // Create an instance for a view model,
        // and get() fill the instance that is need in constructor
        viewModel{ NotesViewModel(get())}

    }

}