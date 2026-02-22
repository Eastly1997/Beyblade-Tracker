package com.lkby.tracker.di

import com.lkby.tracker.presentation.auth.AuthViewModel
import com.lkby.tracker.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { SplashViewModel(get()) }

}