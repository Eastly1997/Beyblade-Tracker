package com.lkby.tracker.di

import com.lkby.tracker.presentation.auth.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
}