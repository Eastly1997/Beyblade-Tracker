package com.lkby.feature.auth.di

import com.lkby.feature.auth.domain.usecase.GetCurrentUserUseCase
import com.lkby.feature.auth.domain.usecase.LogoutUseCase
import com.lkby.feature.auth.domain.usecase.SignInWithGoogleUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SignInWithGoogleUseCase(get()) }
    factory { GetCurrentUserUseCase(get()) }
    factory { LogoutUseCase(get()) }
}